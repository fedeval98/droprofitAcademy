package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.repositories.ClientsRepository;
import com.opytha.droprofitacademy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    public Client findById(Long id) {
        return clientsRepository.findById(id).orElse(null);
    }
}
