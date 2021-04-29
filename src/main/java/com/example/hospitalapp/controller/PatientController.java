package com.example.hospitalapp.controller;

import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.model.Views;
import com.example.hospitalapp.repositories.DoctorRepository;
import com.example.hospitalapp.repositories.PatientRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PatientRepository patientRepository;

    public PatientController(DoctorRepository doctorRepository, PatientRepository patientRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }
    @JsonView(Views.Vue.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> patientAll(){
        return patientRepository.findAll();
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public List<Doctor> doctorsbyIdPatient(@PathVariable(name = "id") Long id, Model model){
        return doctorRepository.findByPatientsId(id);
    }
    @PostMapping("/{id}")
    public String createPatient(@PathVariable(name = "id") Long id,@RequestBody Patient patient) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        Set<Doctor> res = new HashSet<>();
        doctor.ifPresent(res::add);
        patientRepository.save(new Patient(patient.getFirstName(),patient.getLastName(),patient.getDiagnosis(),patient.getAge(), res));
        return "Ok";
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@RequestBody Patient patient,
                                 @PathVariable(name = "id") Long id) {
        return patientRepository.findById(id).map(
                patients -> {
                    patients.setFirstName(patient.getFirstName());
                    patients.setLastName(patient.getLastName());
                    patients.setDiagnosis(patient.getDiagnosis());
                    patients.setAge(patient.getAge());
                    return patientRepository.save(patient);
                }).orElseGet(() -> {patient.setId(id);
            return patientRepository.save(patient);
        });
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable (name = "id") Long id) {
        patientRepository.deleteById(id);
        return "Ok";
    }
}
