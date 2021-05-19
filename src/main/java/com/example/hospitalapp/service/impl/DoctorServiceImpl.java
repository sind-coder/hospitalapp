package com.example.hospitalapp.service.impl;

import com.example.hospitalapp.dto.DoctorDto;
import com.example.hospitalapp.mapper.DoctorMapper;
import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.repositories.DoctorRepository;
import com.example.hospitalapp.repositories.PatientRepository;
import com.example.hospitalapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    public DoctorRepository doctorRepository;

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public DoctorMapper doctorMapper;

    @Override
    public List<DoctorDto> findAll() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::convertToDto).collect(toList());
    }

    @Override
    public List<DoctorDto> findByPatientsId(Long id) {
        return doctorRepository.findByPatientsId(id).stream()
                .map(doctorMapper::convertToDto)
                .collect(toList());
    }

    @Override
    public DoctorDto findByIdUpdate(Doctor doctor, Long id) {
        return doctorRepository.findById(id).map(
                doctors -> {
                    doctors.setFirstName(doctor.getFirstName());
                    doctors.setLastName(doctor.getLastName());
                    doctors.setPosition(doctor.getPosition());
                    doctors.setTimeStart(doctor.getTimeStart());
                    doctors.setTimeEnd(doctor.getTimeEnd());
                    return doctorMapper.convertToDto(doctorRepository.save(doctor));
                }).orElseGet(() -> {
            doctor.setId(id);
            return doctorMapper.convertToDto(doctorRepository.save(doctor));
        });
    }

    @Override
    public List<DoctorDto> findById(Long id) {
        return doctorRepository.findById(id).stream()
                .map(doctorMapper::convertToDto)
                .collect(toList());
    }

    @Override
    public DoctorDto save(Doctor doctor) {
        return doctorMapper.convertToDto(doctorRepository.save(
                new Doctor(doctor.getFirstName(), doctor.getLastName(),
                        doctor.getPosition(), doctor.getTimeStart(), doctor.getTimeEnd())));
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}
