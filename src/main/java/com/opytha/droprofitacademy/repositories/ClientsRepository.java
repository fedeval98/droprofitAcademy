package com.opytha.droprofitacademy.repositories;

import com.opytha.droprofitacademy.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientsRepository extends JpaRepository<Client,Long> {
    Client findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserID(int uid);

    Client findByUserID(int uid);

}
