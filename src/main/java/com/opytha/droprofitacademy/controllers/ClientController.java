package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.requests.Register;
import com.opytha.droprofitacademy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<ClientDTO> getAllClients(){return clientService.getAllClientsDTO();}

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id){
        ResponseEntity<ClientDTO> response = clientService.getClient(id);
        return response;
    }

    @PostMapping("/clients/register")
    public ResponseEntity<String> createClient(@RequestBody Register newClient){
        ResponseEntity<String> response = clientService.createClient(newClient);
        return response;
    }

}
