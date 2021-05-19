package com.example.hospitalapp.controller;

import com.example.hospitalapp.mapper.DoctorMapper;
import com.example.hospitalapp.mapper.PatientMapper;
import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.model.Views;
import com.example.hospitalapp.service.DoctorService;
import com.example.hospitalapp.service.PatientService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/doctor")
@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    PatientMapper patientMapper;



    @GetMapping
    @JsonView(Views.Vue.class)
    public ResponseEntity<List<Doctor>> doctorAll() {
        List<Doctor> doctorList = doctorService.findAll().stream()
                .map(doctorMapper::convertToEntity).collect(Collectors.toList());
        if (doctorList.toArray().length == 0) {
            return new ResponseEntity<>(doctorList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public ResponseEntity<List<Patient>> patientsByIdDoctor (
            @PathVariable(name = "id") Long id) {
        List<Patient> patientList = patientService.findByDoctorsId(id).stream()
                .map(patientMapper::convertToEntity).collect(Collectors.toList());
        if (patientList.toArray().length == 0) {
            return new ResponseEntity<>(patientList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(patientList, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<Doctor> createDoctor(
            @RequestBody Doctor doctor) {
        Doctor doctorCreate = doctorMapper.convertToEntity(doctorService.save(
                new Doctor(doctor.getFirstName(), doctor.getLastName(), doctor.getPosition(),
                        doctor.getTimeStart(), doctor.getTimeEnd())));
        if (doctorCreate == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(doctorCreate, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @RequestBody Doctor doctor, @PathVariable(name = "id") Long id) {
        Doctor doctorUpdate = doctorMapper.convertToEntity(
                doctorService.findByIdUpdate(doctor,id));
        if (doctorUpdate == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(doctorUpdate, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(
            @PathVariable(name = "id") Long id) {
        doctorService.deleteById(id);
        return new ResponseEntity<>("Delete doctor", HttpStatus.OK);
    }
}