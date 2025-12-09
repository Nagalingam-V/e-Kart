package com.example.eKart.products.domain;

public enum ProductStatus {
    ACTIVE(100),
    INACTIVE(200),
    OUT_OF_STOCK(300);

    private final int code;

    ProductStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ProductStatus fromCode(int code) {
        for (ProductStatus status : ProductStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ProductStatus code: " + code);
    }
}
