package com.yukuii.powerbank.Model.Enum;

public enum OrderStatus {
    IS_COMPLETE("isComplete"),
    IS_GOING("isGoing"),
    UNPAID("unpaid");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
