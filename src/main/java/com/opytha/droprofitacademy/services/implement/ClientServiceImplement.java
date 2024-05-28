package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.requests.Register;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.repositories.ClientsRepository;
import com.opytha.droprofitacademy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public ClientDTO getClientById(Long id) {
        return clientsRepository.findById(id).map(ClientDTO::new).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email){
        return clientsRepository.existsByEmail(email);
    }

    @Override
    public void saveClient(Client client){
        clientsRepository.save(client);
    }

    @Override
    public ResponseEntity<ClientDTO> getClient(Long id){
        ClientDTO clientDTO = getClientById(id);

        if(clientDTO != null){
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> createClient(Register newClient){
        if(newClient.getFirstName().isBlank()){
            return new ResponseEntity<>("Name can't be blank", HttpStatus.FORBIDDEN);
        }
        if(newClient.getLastName().isBlank()){
            return new ResponseEntity<>("Last name can't be blank", HttpStatus.FORBIDDEN);
        }
        if(newClient.getEmail().isBlank()){
            return new ResponseEntity<>("Email can't be blank", HttpStatus.FORBIDDEN);
        }
        if(newClient.getPassword().isBlank()){
            return new ResponseEntity<>("Password can't be blank", HttpStatus.FORBIDDEN);
        }

        if(existsByEmail(newClient.getEmail())){
            return new ResponseEntity<>("Email already on use", HttpStatus.FORBIDDEN);
        }

        Client client = new Client(newClient.getFirstName(),newClient.getLastName(),newClient.getEmail(), passwordEncoder.encode(newClient.getPassword()));

        saveClient(client);

        return new ResponseEntity<>("Client and account created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> findByClientEmailAndId(String email, Long id, Roles roltype) {
        Client client = clientsRepository.findByEmailAndId(email,id);

        if(roltype == Roles.USER){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        if (client == null){
            return new ResponseEntity<>("Client not found", HttpStatus.BAD_REQUEST);
        }

        if(!client.isActive()){
            return new ResponseEntity<>("Account is already inactive", HttpStatus.OK);
        }

        client.setActive(false);

        return new ResponseEntity<>("Account remove successfully", HttpStatus.OK);
    }
}
