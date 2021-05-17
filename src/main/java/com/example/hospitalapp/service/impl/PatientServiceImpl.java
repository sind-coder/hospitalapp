package com.example.hospitalapp.service.impl;


import com.example.hospitalapp.dto.PatientDto;
import com.example.hospitalapp.mapper.PatientMapper;
import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.repositories.DoctorRepository;
import com.example.hospitalapp.repositories.PatientRepository;
import com.example.hospitalapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public PatientMapper patientMappper;

    @Override
    public List<PatientDto> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMappper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<PatientDto> findByDoctorsId(Long id) {
        return patientRepository.findByDoctorsId(id).stream()
                .map(patientMappper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public PatientDto findById_update(Patient patient, Long id) {
        return patientRepository.findById(id).map(
                patients -> {
                    patients.setFirstName(patient.getFirstName());
                    patients.setLastName(patient.getLastName());
                    patients.setDiagnosis(patient.getDiagnosis());
                    patients.setAge(patient.getAge());
                    return patientMappper.convertToDto(patientRepository.save(patient));
                }).orElseGet(() -> {patient.setId(id);
            return patientMappper.convertToDto(patientRepository.save(patient));
        });
    }


    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);
    }
}
