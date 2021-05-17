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
    public ResponseEntity<List<Doctor>> doctorAll(){
        List<Doctor> doctorList = doctorService.findAll().stream().map(doctorMapper::convertToEntity).collect(Collectors.toList());
        if (doctorList.toArray().length == 0){
            return new ResponseEntity<>(doctorList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public ResponseEntity<List<Patient>> patientsbyIdDoctor (@PathVariable(name="id") Long id, Model model){
        List<Patient> patientList = patientService.findByDoctorsId(id).stream().map(patientMapper::convertToEntity).collect(Collectors.toList());
        if (patientList.toArray().length == 0){
            return new ResponseEntity<>(patientList, HttpStatus.NO_CONTENT);
        } else {
        return new ResponseEntity<>(patientList, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor doctorPost = doctorMapper.convertToEntity(doctorService.save(new Doctor(doctor.getFirstName(),doctor.getLastName(),doctor.getPosition(),doctor.getTime_start(),doctor.getTime_end())));
        if (doctorPost == null){
            return new ResponseEntity<>(doctorPost, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(doctorPost, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor,
                                @PathVariable(name = "id") Long id) {
        Doctor doctorPut = doctorMapper.convertToEntity(doctorService.findById_update(doctor,id));
        if (doctorPut == null){
            return new ResponseEntity<>(doctorPut, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(doctorPut, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable(name = "id") Long id) {
        doctorService.deleteById(id);
        return new ResponseEntity<>("Delete doctor", HttpStatus.OK);
    }
}
