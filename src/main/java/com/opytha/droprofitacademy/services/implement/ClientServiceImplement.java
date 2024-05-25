package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.repositories.ClientsRepository;
import com.opytha.droprofitacademy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    public List <Client> getAllClients(){
        return clientsRepository.findAll();
    }

    @Override
    public List<ClientDTO> getAllClientsDTO(){
        return getAllClients().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Override
    public Client findById(Long id) {
        return clientsRepository.findById(id).orElse(null);
    }
}
