package com.opytha.droprofitacademy.services.implement;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.requests.Register;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.Courses;
import com.opytha.droprofitacademy.models.enums.Roles;
import com.opytha.droprofitacademy.repositories.ClientsRepository;
import com.opytha.droprofitacademy.repositories.CoursesRepository;
import com.opytha.droprofitacademy.services.ClientService;
import com.opytha.droprofitacademy.services.CoursesService;
import com.opytha.droprofitacademy.utils.UserID;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.opytha.droprofitacademy.utils.UserID.getAccountNumber;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public Client findByEmail(String email){
        return clientsRepository.findByEmail(email);
    }

    @Override
    public Client getAuthClient(String email) {
        return clientsRepository.findByEmail(email);
    }

    @Override
    public ClientDTO getAuthClientDTO(String email) {
        return new ClientDTO(getAuthClient(email));
    }

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
    public boolean existsByUserID(int uid) {
        return clientsRepository.existsByUserID(uid);
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

        int uid;
        do {
            uid = getAccountNumber(0000000000, 2147483647);
        }while (existsByUserID(uid));

        Client client = new Client(newClient.getFirstName(),newClient.getLastName(),newClient.getEmail(), passwordEncoder.encode(newClient.getPassword()),uid);

        saveClient(client);

        return new ResponseEntity<>("Client created successfully", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> removeClient(String email, Long id) {

        Client auth = clientsRepository.findByEmail(email);

        if(auth.getRol().equals(Roles.USER)){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        Client client = findById(id);

        if (client == null){
            return new ResponseEntity<>("Client not found", HttpStatus.BAD_REQUEST);
        }

        if(!client.isActive()){
            return new ResponseEntity<>("Client is already inactive", HttpStatus.OK);
        }

        clientsRepository.deleteById(id);

        return new ResponseEntity<>("Client remove successfully", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> addCourseToClient (Long userId, Long CourseId, String email){
        Client auth = findByEmail(email);

        if(auth.getRol().equals(Roles.USER)){
            return new ResponseEntity<>("Admin privileges required.", HttpStatus.FORBIDDEN);
        }

        Client user = findById(userId);

        if(user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if(!user.isActive()){
            return new ResponseEntity<>("User is disabled", HttpStatus.FORBIDDEN);
        }

        Courses course = coursesRepository.findById(CourseId).orElse(null);

        if(course == null){
            return new ResponseEntity<>("Course not found",HttpStatus.NOT_FOUND);
        }

        user.addCourses(course);

        return new ResponseEntity<>("Course added successfully", HttpStatus.OK);
    }
}
