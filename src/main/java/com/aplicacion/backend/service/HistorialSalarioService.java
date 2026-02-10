package com.aplicacion.backend.service;

import com.aplicacion.backend.domain.HistorialSalario;
import com.aplicacion.backend.dtos.historial.HistorialSalarioCreateDTO;
import com.aplicacion.backend.dtos.historial.HistorialSalarioDTO;
import com.aplicacion.backend.mapper.HistorialSalarioMapper;
import com.aplicacion.backend.repository.HistorialSalarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialSalarioService {

    private final HistorialSalarioRepository historialSalarioRepository;
    private final HistorialSalarioMapper historialSalarioMapper;

    public List<HistorialSalarioDTO> findAll() {
        return historialSalarioMapper.toDTO(historialSalarioRepository.findAll());
    }

    public HistorialSalarioDTO findById(Integer id) {
        return historialSalarioRepository.findById(id)
                .map(historialSalarioMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Historial de salario not found"));
    }

    public HistorialSalarioDTO create(HistorialSalarioCreateDTO createDTO) {
        HistorialSalario historialSalario = historialSalarioMapper.fromCreateUpdateDTO(createDTO);
        HistorialSalario savedHistorial = historialSalarioRepository.save(historialSalario);
        return historialSalarioMapper.toDTO(savedHistorial);
    }

    public HistorialSalarioDTO update(Integer id, HistorialSalarioCreateDTO createDTO) {
        HistorialSalario historialSalario = historialSalarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial de salario not found"));

        historialSalarioMapper.updateEntityFromDTO(createDTO, historialSalario);
        HistorialSalario updatedHistorial = historialSalarioRepository.save(historialSalario);
        return historialSalarioMapper.toDTO(updatedHistorial);
    }

    public void delete(Integer id) {
        if (!historialSalarioRepository.existsById(id)) {
            throw new RuntimeException("Historial de salario not found");
        }
        historialSalarioRepository.deleteById(id);
    }
}
