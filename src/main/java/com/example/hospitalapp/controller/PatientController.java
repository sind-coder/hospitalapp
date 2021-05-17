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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private PatientMapper patientMapper;

    public PatientController(DoctorService doctorService,
                             PatientService patientService){
        this.doctorService = doctorService;
        this.patientService = patientService;
    }
    @JsonView(Views.Vue.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> patientAll() {
        List<Patient> patientList = patientService.findAll().stream()
                .map(patientMapper::convertToEntity).collect(Collectors.toList());
        if (patientList.toArray().length == 0) {
            return new ResponseEntity<>(patientList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(patientList, HttpStatus.OK);
        }
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public ResponseEntity<List<Doctor>> doctorsByIdPatient (
            @PathVariable(name = "id") Long id) {
        List<Doctor> doctorListByPatient = doctorService.findByPatientsId(id).stream()
                .map(doctorMapper::convertToEntity).collect(Collectors.toList());
        if (doctorListByPatient.toArray().length == 0) {
            return new ResponseEntity<>(doctorListByPatient, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(doctorListByPatient, HttpStatus.OK);
        }
    }
    @PostMapping("/{id}")
    public ResponseEntity<String> createPatient(
            @PathVariable(name = "id") Long id, @RequestBody Patient patient) {
        Set<Doctor> doctor = doctorService.findById(id).stream()
                .map(doctorMapper::convertToEntity).collect(Collectors.toSet());
        patientService.save(
                new Patient(patient.getFirstName(), patient.getLastName(),
                        patient.getDiagnosis(), patient.getAge(), doctor));
        return new ResponseEntity<>("Doctor create", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient,
                                 @PathVariable(name = "id") Long id) {
        Patient patientUpdate = patientMapper.convertToEntity(
                patientService.findById_update(patient, id));
        if (patientUpdate == null) {
            return new ResponseEntity<>(patientUpdate, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(patientUpdate, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(
            @PathVariable (name = "id") Long id) {
        patientService.deleteById(id);
        return new ResponseEntity<>("Delete doctor", HttpStatus.OK);
    }
}
