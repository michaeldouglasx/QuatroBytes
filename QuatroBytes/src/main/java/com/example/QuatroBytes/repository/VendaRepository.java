package com.example.QuatroBytes.repository;

import com.example.QuatroBytes.model.Produto;
import com.example.QuatroBytes.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda,Long> {
}
