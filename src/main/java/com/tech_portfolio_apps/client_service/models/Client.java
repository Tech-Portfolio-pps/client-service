package com.tech_portfolio_apps.client_service.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document("clients")
@Data
public class Client {
    @Id
    private long id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private int phone;
    private String address;
}
