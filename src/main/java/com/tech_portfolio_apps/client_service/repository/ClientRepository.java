package com.tech_portfolio_apps.client_service.repository;

import com.tech_portfolio_apps.client_service.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, Long> {
    Optional<Client> findByEmail(String emailId);
}
