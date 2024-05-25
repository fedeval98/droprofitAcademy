package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.models.Client;

import java.util.List;

public interface ClientService {

    Client findById (Long id);

    List<Client> getAllClients();

    List<ClientDTO> getAllClientsDTO();
}
