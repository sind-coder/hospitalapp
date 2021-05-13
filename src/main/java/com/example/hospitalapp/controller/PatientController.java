package com.example.hospitalapp.controller;

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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    public PatientController(DoctorService doctorService, PatientService patientService){
        this.doctorService = doctorService;
        this.patientService = patientService;
    }
    @JsonView(Views.Vue.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> patientAll(){
        return patientService.findAll();
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public List<Doctor> doctorsbyIdPatient(@PathVariable(name = "id") Long id, Model model){
        return doctorService.findByPatientsId(id);
    }
    @PostMapping("/{id}")
    public String createPatient(@PathVariable(name = "id") Long id,@RequestBody Patient patient) {
        Optional<Doctor> doctor = doctorService.findById(id);
        Set<Doctor> res = new HashSet<>();
        doctor.ifPresent(res::add);
        patientService.save(new Patient(patient.getFirstName(),patient.getLastName(),patient.getDiagnosis(),patient.getAge(), res));
        return "Ok";
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@RequestBody Patient patient,
                                 @PathVariable(name = "id") Long id) {
        return patientService.findById_update(patient, id);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable (name = "id") Long id) {
        patientService.deleteById(id);
        return "Ok";
    }
}
