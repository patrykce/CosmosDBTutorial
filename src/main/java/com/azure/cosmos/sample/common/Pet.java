// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.sample.common;

public class Pet {
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    private String givenName;

    @Override
    public String toString() {
        return "Pet{" +
                "givenName='" + givenName + '\'' +
                '}';
    }
}
