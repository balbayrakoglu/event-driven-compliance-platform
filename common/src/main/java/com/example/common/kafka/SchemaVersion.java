package com.example.common.kafka;

public enum SchemaVersion {

    /**
     * Initial version of the event schema.
     */
    V1("v1");

    private final String value;

    SchemaVersion(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
