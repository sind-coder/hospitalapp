package com.example.hospitalapp.service.impl;


import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.repositories.DoctorRepository;
import com.example.hospitalapp.repositories.PatientRepository;
import com.example.hospitalapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> findByDoctorsId(Long id) {
        return patientRepository.findByDoctorsId(id);
    }

    @Override
    public Patient findById_update(Patient patient, Long id) {
        return patientRepository.findById(id).map(
                patients -> {
                    patients.setFirstName(patient.getFirstName());
                    patients.setLastName(patient.getLastName());
                    patients.setDiagnosis(patient.getDiagnosis());
                    patients.setAge(patient.getAge());
                    return patientRepository.save(patient);
                }).orElseGet(() -> {patient.setId(id);
            return patientRepository.save(patient);
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
