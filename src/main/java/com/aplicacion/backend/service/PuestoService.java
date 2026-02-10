package com.aplicacion.backend.service;

import com.aplicacion.backend.domain.Puesto;
import com.aplicacion.backend.dtos.puesto.PuestoCreateDTO;
import com.aplicacion.backend.dtos.puesto.PuestoDTO;
import com.aplicacion.backend.mapper.PuestoMapper;
import com.aplicacion.backend.repository.PuestoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PuestoService {

    private final PuestoRepository puestoRepository;
    private final PuestoMapper puestoMapper;

    public List<PuestoDTO> findAll() {
        return puestoMapper.toDTO(puestoRepository.findAll());
    }

    public PuestoDTO findById(Integer id) {
        return puestoRepository.findById(id)
                .map(puestoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Puesto not found"));
    }

    public PuestoDTO create(PuestoCreateDTO createDTO) {
        Puesto puesto = puestoMapper.fromCreateUpdateDTO(createDTO);
        Puesto savedPuesto = puestoRepository.save(puesto);
        return puestoMapper.toDTO(savedPuesto);
    }

    public PuestoDTO update(Integer id, PuestoCreateDTO createDTO) {
        Puesto puesto = puestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto not found"));

        puestoMapper.updateEntityFromDTO(createDTO, puesto);
        Puesto updatedPuesto = puestoRepository.save(puesto);
        return puestoMapper.toDTO(updatedPuesto);
    }

    public void delete(Integer id) {
        if (!puestoRepository.existsById(id)) {
            throw new RuntimeException("Puesto not found");
        }
        puestoRepository.deleteById(id);
    }
}
