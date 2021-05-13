package com.example.hospitalapp.service;


import com.example.hospitalapp.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> findAll();
    List<Patient> findByDoctorsId(Long id);
    Patient findById_update(Patient patient, Long id);
    void deleteById(Long id);
    void save(Patient patient);
}
