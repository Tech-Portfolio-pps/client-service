package com.tech_portfolio_apps.client_service.controllers;


import com.tech_portfolio_apps.client_service.dto.ClientDTO;
import com.tech_portfolio_apps.client_service.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping(value="/api/create-client")
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO client) {
        try {
            ClientDTO newClient = clientService.createClient(client);
            return ResponseEntity.status(201).body(newClient);
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                return ResponseEntity.status(500).body(e.getMessage());
            }
        }
    }

    @GetMapping(value="/api/get-all-clients")
    public ResponseEntity<?> getAllClients() {
        try {
            List<ClientDTO> allClients = clientService.getAllClients();
            return ResponseEntity.ok(allClients);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/api/client/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            ClientDTO client = clientService.getClientById(id);
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/api/client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO updatedClient) {
        try {
            ClientDTO client = clientService.updateClient(id, updatedClient);
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                return ResponseEntity.status(500).body(e.getMessage());
            }
        }
    }

    @DeleteMapping("/api/client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            boolean deleted = clientService.deleteClient(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/api/client/search")
    public ResponseEntity<?> searchClientByEmail(@RequestParam String email) {
        try {
            ClientDTO client = clientService.searchClientByEmail(email);
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    
}
