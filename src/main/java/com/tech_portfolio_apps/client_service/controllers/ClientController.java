package com.tech_portfolio_apps.client_service.controllers;


import com.tech_portfolio_apps.client_service.dto.ClientDTO;
import com.tech_portfolio_apps.client_service.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value="/create-client")
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

    @GetMapping(value="/get-all-clients")
    public ResponseEntity<?> getAllClients() {
        try {
            List<ClientDTO> allClients = clientService.getAllClients();
            return ResponseEntity.ok(allClients);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
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

    @GetMapping("/search")
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

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkClientExists(@PathVariable String id) {
        try {
            boolean exists = clientService.checkClientExists(id);
            log.info("Checked existence of client with ID {}: {}", id, exists);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            log.error("Error checking existence for client ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().body(false);
        }
    }
    
}
