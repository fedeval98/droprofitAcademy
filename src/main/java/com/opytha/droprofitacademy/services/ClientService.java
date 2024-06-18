package com.opytha.droprofitacademy.services;

import com.opytha.droprofitacademy.dtos.ClientDTO;
import com.opytha.droprofitacademy.dtos.requests.Register;
import com.opytha.droprofitacademy.models.Client;
import com.opytha.droprofitacademy.models.enums.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {

    Client findById (Long id);

    Client findByEmail(String email);

    List<Client> getAllClients();

    List<ClientDTO> getAllClientsDTO();

    ClientDTO getClientById(Long id);

    ResponseEntity<ClientDTO> getClient(Long id);

    boolean existsByEmail(String email);

    void saveClient(Client client);

    boolean existsByUserID(int uid);

    Client findByUserID(int uid);

    ClientDTO getAuthClientDTO (String email);

    Client getAuthClient(String email);

    ResponseEntity<String> createClient(Register newClient);

    ResponseEntity<String> removeClient(String email, int id);

    ResponseEntity<String> addCourseToClient(String userIdStr, Long CourseId, String email);
}
