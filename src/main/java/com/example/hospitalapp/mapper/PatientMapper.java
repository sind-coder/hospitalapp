package com.example.hospitalapp.mapper;

import com.example.hospitalapp.dto.PatientDto;
import com.example.hospitalapp.model.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {
    @Autowired
    private ModelMapper modelMapper;


    public PatientDto convertToDto(Patient patient) {
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        return patientDto;
    }
    public Patient convertToEntity(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patient;
    }
}
