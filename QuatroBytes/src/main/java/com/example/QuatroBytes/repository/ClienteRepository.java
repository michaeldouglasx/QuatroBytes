package com.example.QuatroBytes.repository;


import com.example.QuatroBytes.model.Cliente;
import com.example.QuatroBytes.repository.dto.ClienteRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public boolean existsByCpf(String cpf );
    public boolean existsByCpfAndIdNot(String cpf, Long id);




}
