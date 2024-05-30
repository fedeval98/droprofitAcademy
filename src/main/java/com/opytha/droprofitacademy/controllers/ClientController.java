package com.opytha.droprofitacademy.controllers;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.requests.Register;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/clients/current")
    public ResponseEntity<Object> getClient(Authentication authentication){
        ClientDTO clientDTO = clientService.getAuthClientDTO(authentication.getName());
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @PostMapping("/clients/register")
    public ResponseEntity<String> createClient(@RequestBody Register newClient){
        return clientService.createClient(newClient);
    }

    @DeleteMapping("/clients/remove")
    public ResponseEntity<String> removeClient ( @RequestParam Long id, Authentication authentication){
        return clientService.removeClient(authentication.getName(), id);
    }

    @PatchMapping("/clients/addCourse")
    public ResponseEntity<String> addCourseToClient (@RequestParam Long userId, @RequestParam Long CourseId, Authentication authentication){
        return clientService.addCourseToClient(userId, CourseId, authentication.getName());
    }

}
