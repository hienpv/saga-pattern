package com.saga.choreography.event;

public enum KafkaTopic {
    CREATED_ORDER("order-created", "Band 8");

    private String code;
    private String name;

    KafkaTopic(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
