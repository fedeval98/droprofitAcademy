package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.requests.Register;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.enums.Roles;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

    Client findById (Long id);

    List<Client> getAllClients();

    List<ClientDTO> getAllClientsDTO();

    ClientDTO getClientById(Long id);

    ResponseEntity<ClientDTO> getClient(Long id);

    boolean existsByEmail(String email);

    void saveClient(Client client);

    ResponseEntity<String> createClient(Register newClient);
    ResponseEntity<String> findByClientEmailAndId(String email, Long id, Roles roltype);
}
