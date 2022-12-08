package org.example;

import java.time.Instant;

public class OrderDto {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public OrderDto(int id, int petId, int quantity, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }
}
