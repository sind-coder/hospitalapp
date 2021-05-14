package com.example.hospitalapp.service;


import com.example.hospitalapp.dto.PatientDto;
import com.example.hospitalapp.model.Patient;

import java.util.List;

public interface PatientService {
    List<PatientDto> findAll();
    List<PatientDto> findByDoctorsId(Long id);
    PatientDto findById_update(Patient patient, Long id);
    void deleteById(Long id);
    void save(Patient patient);
}
