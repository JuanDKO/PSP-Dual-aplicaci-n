package com.aplicacion.backend.repository;

import com.aplicacion.backend.domain.HistorialSalario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialSalarioRepository extends JpaRepository<HistorialSalario, Integer> {
}
