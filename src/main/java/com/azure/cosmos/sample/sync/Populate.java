package com.azure.cosmos.sample.sync;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.CosmosException;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.models.ThroughputProperties;
import com.azure.cosmos.sample.common.AccountSettings;
import com.azure.cosmos.sample.common.Families;
import com.azure.cosmos.sample.common.Family;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Populate {

    private CosmosClient client;

    private final String databaseName = "AzureSampleFamilyDB";
    private final String containerName = "FamilyContainer";

    private CosmosDatabase database;
    private CosmosContainer container;

    protected static Logger logger = LoggerFactory.getLogger(Populate.class.getSimpleName());

    public void close() {
        client.close();
    }

    public static void main(String[] args) {
        Populate p = new Populate();

        try {
            logger.info("Starting SYNC main");
            p.getStartedDemo();
            logger.info("Demo complete, please hold while resources are released");
        } catch (Exception e) {
            logger.error("Cosmos getStarted failed with", e);
        } finally {
            logger.info("Closing the client");
            p.close();
        }
        System.exit(0);
    }

    private void getStartedDemo() throws Exception {
        logger.info("Using Azure Cosmos DB endpoint: {}", AccountSettings.HOST);

        //  Create sync client
        client = new CosmosClientBuilder()
                .endpoint(AccountSettings.HOST)
                .key(AccountSettings.MASTER_KEY)
                //  Setting the preferred location to Cosmos DB Account region
                //  West Germany West Central is just an example. User should set preferred location to the Cosmos DB region closest to the application
                .preferredRegions(Collections.singletonList("Germany West Central"))
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildClient();

        createDatabaseIfNotExists();
        createContainerIfNotExists();

        //  Mock family items to create
        ArrayList<Family> familiesToCreate = new ArrayList<>();
        familiesToCreate.add(Families.getAndersenFamilyItem());
        familiesToCreate.add(Families.getWakefieldFamilyItem());
        familiesToCreate.add(Families.getJohnsonFamilyItem());
        familiesToCreate.add(Families.getSmithFamilyItem());
        familiesToCreate.add(Families.getAnotherSmithFamilyItem());

        createFamilies(familiesToCreate);

        logger.info("Reading items.");
        readItems(familiesToCreate);
    }

    private void createDatabaseIfNotExists() throws Exception {
        logger.info("Create database {} if not exists.", databaseName);

        //  Create database if not exists
        //  <CreateDatabaseIfNotExists>
        CosmosDatabaseResponse cosmosDatabaseResponse = client.createDatabaseIfNotExists(databaseName);
        database = client.getDatabase(cosmosDatabaseResponse.getProperties().getId());
        //  </CreateDatabaseIfNotExists>

        logger.info("Checking database {} completed!\n", database.getId());
    }

    private void createContainerIfNotExists() throws Exception {
        logger.info("Create container {} if not exists.", containerName);

        //  Create container if not exists
        CosmosContainerProperties containerProperties =
                new CosmosContainerProperties(containerName, "/lastName");

        //  Create container with 400 RU/s
        CosmosContainerResponse cosmosContainerResponse =
                database.createContainerIfNotExists(containerProperties, ThroughputProperties.createManualThroughput(400));
        container = database.getContainer(cosmosContainerResponse.getProperties().getId());

        logger.info("Checking container {} completed!\n", container.getId());
    }

    private void createFamilies(List<Family> families) {
        double totalRequestCharge = 0;
        for (Family family : families) {

            //  Create item using container that we created using sync client

            //  Use lastName as partitionKey for cosmos item
            //  Using appropriate partition key improves the performance of database operations
            CosmosItemRequestOptions cosmosItemRequestOptions = new CosmosItemRequestOptions();
            CosmosItemResponse<Family> item = container.createItem(family, new PartitionKey(family.getLastName()), cosmosItemRequestOptions);

            //  Get request charge and other properties like latency, and diagnostics strings, etc.
            logger.info("Created item with request charge of {} within duration {}",
                    item.getRequestCharge(), item.getDuration());
            totalRequestCharge += item.getRequestCharge();
        }
        logger.info("Created {} items with total request charge of {}",
                families.size(),
                totalRequestCharge);
    }

    private void readItems(ArrayList<Family> familiesToCreate) {
        //  Using partition key for point read scenarios.
        //  This will help fast look up of items because of partition key
        familiesToCreate.forEach(family -> {
            //  <ReadItem>
            try {
                CosmosItemResponse<Family> item = container.readItem(family.getId(), new PartitionKey(family.getLastName()), Family.class);
                double requestCharge = item.getRequestCharge();
                Duration requestLatency = item.getDuration();
                logger.info("Item successfully read with id {} with a charge of {} and within duration {}",
                        item.getItem().getId(), requestCharge, requestLatency);
            } catch (CosmosException e) {
                logger.error("Read Item failed with", e);
            }
            //  </ReadItem>
        });
    }
}
