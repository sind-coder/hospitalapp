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
import org.springframework.ui.Model;
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

    public DoctorController(DoctorService doctorService, PatientService patientService){
        this.doctorService = doctorService;
        this.patientService = patientService;
    }


    @GetMapping
    @JsonView(Views.Vue.class)
    public List<Doctor> doctorAll(){
        return doctorService.findAll().stream().map(doctorMapper::convertToEntity).collect(Collectors.toList());
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public List<Patient> patientsbyIdDoctor (@PathVariable(name="id") Long id, Model model){
        return patientService.findByDoctorsId(id).stream().map(patientMapper::convertToEntity).collect(Collectors.toList());
    }

    @PostMapping()
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorMapper.convertToEntity(doctorService.save(new Doctor(doctor.getFirstName(),doctor.getLastName(),doctor.getPosition(),doctor.getTime_start(),doctor.getTime_end())));
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@RequestBody Doctor doctor,
                                @PathVariable(name = "id") Long id) {
        return doctorMapper.convertToEntity(doctorService.findById_update(doctor,id));
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable(name = "id") Long id) {
        doctorService.deleteById(id);
        return "Ok";
    }
}
