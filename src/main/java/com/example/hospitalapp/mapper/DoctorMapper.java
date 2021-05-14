package com.example.hospitalapp.mapper;

import com.example.hospitalapp.dto.DoctorDto;
import com.example.hospitalapp.model.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorMapper {
    @Autowired
    private ModelMapper modelMapper;

    public DoctorDto convertToDto(Doctor doctor) {
        DoctorDto doctorDto = modelMapper.map(doctor, DoctorDto.class);
        return doctorDto;
    }
    public Doctor convertToEntity(DoctorDto doctorDto){
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        return doctor;
    }
}
