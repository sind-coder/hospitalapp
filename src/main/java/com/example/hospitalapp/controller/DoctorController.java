package com.example.hospitalapp.controller;

import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.model.Views;
import com.example.hospitalapp.repositories.DoctorRepository;
import com.example.hospitalapp.repositories.PatientRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/doctor")
@RestController
public class DoctorController {

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PatientRepository patientRepository;
    public DoctorController(DoctorRepository doctorRepository, PatientRepository patientRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }


    @GetMapping
    @JsonView(Views.Vue.class)
    public List<Doctor> doctorAll(){
        return doctorRepository.findAll();
    }

    @JsonView(Views.Vue.class)
    @GetMapping("/{id}")
    public List<Patient> patientsbyIdDoctor (@PathVariable(name="id") Long id, Model model){
        return patientRepository.findByDoctorsId(id);
    }

    @RequestMapping(method= RequestMethod.POST, headers = {"content-type=application/json"})
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(new Doctor(doctor.getFirstName(),doctor.getLastName(),doctor.getPosition(),doctor.getTime_start(),doctor.getTime_end()));
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@RequestBody Doctor doctor,
                                @PathVariable(name = "id") Long id) {
        return doctorRepository.findById(id).map(
                doctors -> {
                    doctors.setFirstName(doctor.getFirstName());
                    doctors.setLastName(doctor.getLastName());
                    doctors.setPosition(doctor.getPosition());
                    doctors.setTime_start(doctor.getTime_start());
                    doctors.setTime_end(doctor.getTime_end());
                    return doctorRepository.save(doctor);
                }).orElseGet(() -> {doctor.setId(id);
            return doctorRepository.save(doctor);
        });
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable(name = "id") Long id) {
        doctorRepository.deleteById(id);
        return "Ok";
    }
}
