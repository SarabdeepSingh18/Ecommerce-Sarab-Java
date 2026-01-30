package com.sarab.SpringEcom.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity
) { }
