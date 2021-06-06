// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.sample.common;

import org.apache.commons.lang3.StringUtils;

public class AccountSettings {
    // MASTER_KEY i HOST zostały zastąpione odpowiednią konfiguracją do stworzonej bazy cosmosDB
    public static String MASTER_KEY =
            System.getProperty("ACCOUNT_KEY", 
                    StringUtils.defaultString(StringUtils.trimToNull(
                            System.getenv().get("ACCOUNT_KEY")),
                            "9SdX0pJWYSYrkNFJYMtMTQPkhcbYGQLoJX0FFqpiG0u3M6a0Tg3tDiqBiwB5SckSeWaxHuuzBpU0p5WM1OOXIg=="));

    public static String HOST =
            System.getProperty("ACCOUNT_HOST",
                    StringUtils.defaultString(StringUtils.trimToNull(
                            System.getenv().get("ACCOUNT_HOST")),
                            "https://exemplaycosmosdb.documents.azure.com:443/"));
}
