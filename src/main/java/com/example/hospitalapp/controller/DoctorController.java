package com.example.hospitalapp.controller;

import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.model.Views;
import com.example.hospitalapp.service.DoctorService;
import com.example.hospitalapp.service.PatientService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/doctor")
@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    public DoctorController(DoctorService doctorService, PatientService patientService){
        this.doctorService = doctorService;
        this.patientService = patientService;
    }


    @GetMapping
    @JsonView(Views.Vue.class)
    public List<Doctor> doctorAll(){
        return doctorService.findAll();
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public List<Patient> patientsbyIdDoctor (@PathVariable(name="id") Long id, Model model){
        return patientService.findByDoctorsId(id);
    }

    @PostMapping()
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.save(new Doctor(doctor.getFirstName(),doctor.getLastName(),doctor.getPosition(),doctor.getTime_start(),doctor.getTime_end()));
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@RequestBody Doctor doctor,
                                @PathVariable(name = "id") Long id) {
        return doctorService.findById_update(doctor,id);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable(name = "id") Long id) {
        doctorService.deleteById(id);
        return "Ok";
    }
}
