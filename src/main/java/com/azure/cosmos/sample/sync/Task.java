package com.azure.cosmos.sample.sync;

import com.azure.cosmos.*;
import com.azure.cosmos.models.*;
import com.azure.cosmos.sample.common.AccountSettings;
import com.azure.cosmos.sample.common.Family;
import com.azure.cosmos.sample.common.UniqueLastName;
import com.azure.cosmos.util.CosmosPagedIterable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Task {

    private CosmosClient client;

    private final String databaseName = "AzureSampleFamilyDB";
    private final String containerName = "FamilyContainer";

    private CosmosDatabase database;
    private CosmosContainer container;

    protected static Logger logger = LoggerFactory.getLogger(Populate.class.getSimpleName());

    public void close() {
        client.close();
    }

    public static void disableWarning() {
        System.err.close();
    }

    public static void main(String[] args) {
        disableWarning();
        Task t = new Task();
        logger.info("Starting SYNC main");
        try {
            t.getStartedDemo();
            logger.info("Demo complete, please hold while resources are released");
        } catch (Exception e) {
            logger.error("Cosmos getStarted failed with", e);
        } finally {
            logger.info("Closing the client");
            t.close();
        }
        System.exit(0);
    }

    private void getStartedDemo() {

        client = new CosmosClientBuilder()
                .endpoint(AccountSettings.HOST)
                .key(AccountSettings.MASTER_KEY)
                .preferredRegions(Collections.singletonList("Germany West Central"))
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildClient();
        CosmosDatabaseResponse cosmosDatabaseResponse = client.createDatabaseIfNotExists(databaseName);
        database = client.getDatabase(cosmosDatabaseResponse.getProperties().getId());
        CosmosContainerProperties containerProperties =
                new CosmosContainerProperties(containerName, "/lastName");
        CosmosContainerResponse cosmosContainerResponse =
                database.createContainerIfNotExists(containerProperties, ThroughputProperties.createManualThroughput(400));
        container = database.getContainer(cosmosContainerResponse.getProperties().getId());
        queryItems();
    }

    private void queryItems() {
        task1();
        task2();
        task3();
        task4();
    }

    private void performQueryReturnFamily(int taskNum, String query) {
        System.out.printf("**===================**   Task number: %d   **===================**%n", taskNum);
        CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        queryOptions.setQueryMetricsEnabled(true);

        CosmosPagedIterable<Family> familiesPagedIterable = container.queryItems(
                query, queryOptions, Family.class);

        familiesPagedIterable.iterableByPage(50).forEach(cosmosItemPropertiesFeedResponse -> {
            System.out.printf("Got a page of query result with %s items(s) and request charge of %s%n",
                    cosmosItemPropertiesFeedResponse.getResults().size(), cosmosItemPropertiesFeedResponse.getRequestCharge());

            System.out.printf("Items size: %d%n", cosmosItemPropertiesFeedResponse.getResults().size());

            System.out.printf("Items %s%n", cosmosItemPropertiesFeedResponse
                    .getResults()
                    .stream()
                    .map(Family::getId)
                    .collect(Collectors.toList()));
        });
        System.out.printf("**==============================****==============================**%n%n%n");

    }

    private void performQueryReturnUniqueLastnNames(int taskNum, String query) {
        System.out.printf("**===================**   Task number: %d   **===================**%n", taskNum);
        CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        queryOptions.setQueryMetricsEnabled(true);

        CosmosPagedIterable<UniqueLastName> familiesPagedIterable = container.queryItems(
                query, queryOptions, UniqueLastName.class);

        familiesPagedIterable.iterableByPage(50).forEach(cosmosItemPropertiesFeedResponse -> {
            System.out.printf("Got a page of query result with %s items(s) and request charge of %s%n",
                    cosmosItemPropertiesFeedResponse.getResults().size(), cosmosItemPropertiesFeedResponse.getRequestCharge());

            System.out.printf("Items size: %d%n", cosmosItemPropertiesFeedResponse.getResults().size());

            System.out.printf("Items %s%n", new ArrayList<>(cosmosItemPropertiesFeedResponse
                    .getResults()));
        });
        System.out.printf("**==============================****==============================**%n%n%n");

    }

    //TODO
    // Zadanie1: wyświetl wszystkie obiekty klasy Family
    private void task1() {
        String query = "TODO...";
        performQueryReturnFamily(1, query);
    }

    //TODO
    // Zadanie2: wyświetl wszystkie obiekty klasy Family o nazwisku Andersen lub Wakefield
    private void task2() {
        String query = "TODO...";
        performQueryReturnFamily(2, query);
    }

    //TODO
    // Zadanie3: wyświetl wszystkie obiekty klasy Family o posortowane po pierwszym Grade z tablicy childrem
    // Jeżeli children nie posiada grade lub Family nie posiada children, nie wyświetlaj dokumentu
    // wskazówka: odwołanie do grade: families.children[0].grade
    // jeżeli dokument nie posiada elementu, po którym jest sortowany, nie zostanie wyświetlony.
    private void task3() {
        String query = "TODO...";
        performQueryReturnFamily(3, query);
    }

    //TODO
    // Zadanie4: wyświetl wszystkie unikalne nazwiska z klasy Family, wyściel je jako uniqueLastName,
    // są to obiekty klasy UniqueLastName.
    private void task4() {
        String query = "SELECT uniqueLastName FROM (TODO...) AS uniqueLastName";
        performQueryReturnUniqueLastnNames(4, query);
    }
}
