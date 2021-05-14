package com.example.hospitalapp.service;

import com.example.hospitalapp.dto.DoctorDto;
import com.example.hospitalapp.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<DoctorDto> findAll();
    List<DoctorDto> findByPatientsId(Long id);
    List<DoctorDto> findById(Long id);
    DoctorDto findById_update(Doctor doctor, Long id);
    DoctorDto save (Doctor doctor);
    void deleteById(Long id);
}
