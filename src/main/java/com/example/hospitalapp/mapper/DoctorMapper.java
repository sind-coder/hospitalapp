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
        return modelMapper.map(doctor, DoctorDto.class);
    }
    public Doctor convertToEntity(DoctorDto doctorDto) {
        return modelMapper.map(doctorDto, Doctor.class);
    }
}
