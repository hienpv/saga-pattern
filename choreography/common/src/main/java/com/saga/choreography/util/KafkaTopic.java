package com.saga.choreography.util;

public enum KafkaTopic {
    ORDER_EVENT("order-event", "Order event"),
    INVENTORY_EVENT("inventory-event", "Inventory event");

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
