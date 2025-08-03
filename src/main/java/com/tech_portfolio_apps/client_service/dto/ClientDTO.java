package com.tech_portfolio_apps.client_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDTO {
    private long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Email is required")
    private String email;

    private int phone;
    private String address;
}
