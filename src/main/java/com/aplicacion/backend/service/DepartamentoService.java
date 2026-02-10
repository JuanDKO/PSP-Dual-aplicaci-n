package com.aplicacion.backend.service;

import com.aplicacion.backend.domain.Departamento;
import com.aplicacion.backend.dtos.departamento.DepartamentoCreateDTO;
import com.aplicacion.backend.dtos.departamento.DepartamentoDTO;
import com.aplicacion.backend.mapper.DepartamentoMapper;
import com.aplicacion.backend.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;

    public List<DepartamentoDTO> findAll() {
        return departamentoMapper.toDTO(departamentoRepository.findAll());
    }

    public DepartamentoDTO findById(Integer id) {
        return departamentoRepository.findById(id)
                .map(departamentoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Departamento not found"));
    }

    public DepartamentoDTO create(DepartamentoCreateDTO createDTO) {
        Departamento departamento = departamentoMapper.fromCreateUpdateDTO(createDTO);
        Departamento savedDepartamento = departamentoRepository.save(departamento);
        return departamentoMapper.toDTO(savedDepartamento);
    }

    public DepartamentoDTO update(Integer id, DepartamentoCreateDTO createDTO) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento not found"));

        departamentoMapper.updateEntityFromDTO(createDTO, departamento);
        Departamento updatedDepartamento = departamentoRepository.save(departamento);
        return departamentoMapper.toDTO(updatedDepartamento);
    }

    public void delete(Integer id) {
        if (!departamentoRepository.existsById(id)) {
            throw new RuntimeException("Departamento not found");
        }
        departamentoRepository.deleteById(id);
    }
}
