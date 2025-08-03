package com.tech_portfolio_apps.client_service.services;

import com.tech_portfolio_apps.client_service.dto.ClientDTO;
import com.tech_portfolio_apps.client_service.models.Client;
import com.tech_portfolio_apps.client_service.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    private final Map<Long, ClientDTO> clients= new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public ClientDTO createClient(ClientDTO dto) {
        Client client = mapToEntity(dto);
        return mapToDTO(clientRepository.save(client));
    }

    public List<ClientDTO> getAllClients() {
        List<Client> allClients = clientRepository.findAll();
        List<ClientDTO> allClientDTOs = new ArrayList<>();
        for(Client client : allClients) {
            allClientDTOs.add(mapToDTO(client));
        }
        return allClientDTOs;
    }

    public ClientDTO getClientById(long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));;
        return mapToDTO(client);
    }

    public ClientDTO updateClient(long id, ClientDTO updatedClientDTO) {
        Client updatedClient = mapToEntity(updatedClientDTO);
        updatedClient.setId(id);
        return mapToDTO(clientRepository.save(updatedClient));
    }

    public boolean deleteClient(long id) {
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public ClientDTO searchClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Client not found"));
        return mapToDTO(client);
    }

    public Client mapToEntity(ClientDTO dto) {
        Client client = new Client();
        long id = idGenerator.incrementAndGet();
        client.setId(id);
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setAddress(dto.getAddress());
        return client;
    }

    public ClientDTO mapToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setAddress(client.getAddress());
        return dto;
    }
}
