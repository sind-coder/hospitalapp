package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.PatientDto;
import com.example.hospitalapp.mapper.DoctorMapper;
import com.example.hospitalapp.mapper.PatientMapper;
import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.model.Views;
import com.example.hospitalapp.service.DoctorService;
import com.example.hospitalapp.service.PatientService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
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

    public PatientController(DoctorService doctorService, PatientService patientService){
        this.doctorService = doctorService;
        this.patientService = patientService;
    }
    @JsonView(Views.Vue.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> patientAll(){
        return patientService.findAll().stream().map(patientMapper::convertToEntity).collect(Collectors.toList());
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public List<Doctor> doctorsbyIdPatient(@PathVariable(name = "id") Long id, Model model){
        return doctorService.findByPatientsId(id).stream().map(doctorMapper::convertToEntity).collect(Collectors.toList());
    }
    @PostMapping("/{id}")
    public String createPatient(@PathVariable(name = "id") Long id,@RequestBody Patient patient) {
        Set<Doctor> doctor = doctorService.findById(id).stream().map(doctorMapper::convertToEntity).collect(Collectors.toSet());
        patientService.save(new Patient(patient.getFirstName(),patient.getLastName(),patient.getDiagnosis(),patient.getAge(), doctor));
        return "Ok";
    }

    @PutMapping("/{id}")
    public PatientDto updatePatient(@RequestBody Patient patient,
                                 @PathVariable(name = "id") Long id) {
        return patientService.findById_update(patient, id);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable (name = "id") Long id) {
        patientService.deleteById(id);
        return "Ok";
    }
}
