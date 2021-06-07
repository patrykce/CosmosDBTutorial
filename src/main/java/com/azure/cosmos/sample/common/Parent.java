// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.sample.common;

public class Parent {

    public Parent() {
    }

    public Parent(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String familyName;
    private String firstName;

    @Override
    public String toString() {
        return "Parent{" +
                "familyName='" + familyName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
