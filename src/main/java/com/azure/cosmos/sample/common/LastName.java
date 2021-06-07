package com.azure.cosmos.sample.common;

import java.util.Arrays;

public class LastName {
    String uniqueLastName;

    public String getUniqueLastName() {
        return uniqueLastName;
    }

    public void setUniqueLastName(String uniqueLastName) {
        this.uniqueLastName = uniqueLastName;
    }

    @Override
    public String toString() {
        return "LastNames{" +
                "uniqueLastName=" + uniqueLastName +
                '}';
    }
}
