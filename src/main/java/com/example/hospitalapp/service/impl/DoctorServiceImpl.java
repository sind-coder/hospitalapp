package com.example.hospitalapp.service.impl;

import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.repositories.DoctorRepository;
import com.example.hospitalapp.repositories.PatientRepository;
import com.example.hospitalapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    public DoctorRepository doctorRepository;
    public PatientRepository patientRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findByPatientsId(Long id) {
        return doctorRepository.findByPatientsId(id);
    }

    @Override
    public Doctor findById_update(Doctor doctor, Long id) {
        return doctorRepository.findById(id).map(
                doctors -> {
                    doctors.setFirstName(doctor.getFirstName());
                    doctors.setLastName(doctor.getLastName());
                    doctors.setPosition(doctor.getPosition());
                    doctors.setTime_start(doctor.getTime_start());
                    doctors.setTime_end(doctor.getTime_end());
                    return doctorRepository.save(doctor);
                }).orElseGet(() -> {
            doctor.setId(id);
            return doctorRepository.save(doctor);
        });
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(new Doctor(doctor.getFirstName(), doctor.getLastName(), doctor.getPosition(), doctor.getTime_start(), doctor.getTime_end()));
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}
