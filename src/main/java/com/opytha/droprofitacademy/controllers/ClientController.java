package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.requests.Register;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
        return clientService.getClient(id);
    }

    @PostMapping("/clients/register")
    public ResponseEntity<String> createClient(@RequestBody Register newClient){
        return clientService.createClient(newClient);
    }

    @PatchMapping("/clients/remove")
    public ResponseEntity<String> removeClient (Authentication authentication, Long id, Roles roltype){
        return clientService.findByClientEmailAndId(authentication.getName(), id, roltype);
    }

}
