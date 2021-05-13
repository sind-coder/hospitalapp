package com.example.hospitalapp.service;

import com.example.hospitalapp.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> findAll();
    List<Doctor> findByPatientsId(Long id);
    Optional<Doctor> findById(Long id);
    Doctor findById_update(Doctor doctor, Long id);
    Doctor save (Doctor doctor);
    void deleteById(Long id);
}
