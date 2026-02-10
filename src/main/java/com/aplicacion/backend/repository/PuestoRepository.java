package com.aplicacion.backend.repository;

import com.aplicacion.backend.domain.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoRepository extends JpaRepository<Puesto, Integer> {
}
