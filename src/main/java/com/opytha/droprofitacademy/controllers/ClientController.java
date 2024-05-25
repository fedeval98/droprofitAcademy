package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<ClientDTO> getAllClients(){return clientService.getAllClientsDTO();}

}
