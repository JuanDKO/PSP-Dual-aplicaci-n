package com.aplicacion.backend.service;

import com.aplicacion.backend.domain.Asistencia;
import com.aplicacion.backend.dtos.asistencia.AsistenciaCreateDTO;
import com.aplicacion.backend.dtos.asistencia.AsistenciaDTO;
import com.aplicacion.backend.mapper.AsistenciaMapper;
import com.aplicacion.backend.repository.AsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final AsistenciaMapper asistenciaMapper;

    public List<AsistenciaDTO> findAll() {
        return asistenciaMapper.toDTO(asistenciaRepository.findAll());
    }

    public AsistenciaDTO findById(Integer id) {
        return asistenciaRepository.findById(id)
                .map(asistenciaMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Asistencia not found"));
    }

    public AsistenciaDTO create(AsistenciaCreateDTO createDTO) {
        Asistencia asistencia = asistenciaMapper.fromCreateUpdateDTO(createDTO);
        Asistencia savedAsistencia = asistenciaRepository.save(asistencia);
        return asistenciaMapper.toDTO(savedAsistencia);
    }

    public AsistenciaDTO update(Integer id, AsistenciaCreateDTO createDTO) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia not found"));

        asistenciaMapper.updateEntityFromDTO(createDTO, asistencia);
        Asistencia updatedAsistencia = asistenciaRepository.save(asistencia);
        return asistenciaMapper.toDTO(updatedAsistencia);
    }

    public void delete(Integer id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new RuntimeException("Asistencia not found");
        }
        asistenciaRepository.deleteById(id);
    }
}
