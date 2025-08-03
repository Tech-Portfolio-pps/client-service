package com.tech_portfolio_apps.client_service.repository;

import com.tech_portfolio_apps.client_service.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, Long> {
    Optional<Client> findByEmail(String emailId);
    Boolean existsById(String clientID);
}
