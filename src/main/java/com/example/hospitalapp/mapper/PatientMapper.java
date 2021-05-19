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
        return modelMapper.map(patient, PatientDto.class);
    }
    public Patient convertToEntity(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }
}
