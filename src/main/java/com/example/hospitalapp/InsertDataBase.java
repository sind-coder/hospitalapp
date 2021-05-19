package com.example.hospitalapp;

import com.example.hospitalapp.mapper.DoctorMapper;
import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.service.DoctorService;
import com.example.hospitalapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class InsertDataBase implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorMapper doctorMapper;

    private static Logger log = Logger.getLogger(InsertDataBase.class.getName());

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        List<Doctor> verify_enty = doctorService.findAll().stream().map(doctorMapper::convertToEntity).collect(Collectors.toList());
        if (verify_enty.toArray().length == 0) {
            ArrayList<String> firstname = new ArrayList<>();
            firstname.add("Igor");
            firstname.add("Fedor");
            firstname.add("Alexander");
            firstname.add("Semyon");
            firstname.add("Alex");
            firstname.add("Dmitriy");
            firstname.add("Stepan");
            ArrayList<String> lastname = new ArrayList<>();
            lastname.add("Kuzmin");
            lastname.add("Sidorov");
            lastname.add("Petrov");
            lastname.add("Seleznev");
            lastname.add("Alexandrov");
            lastname.add("Vilkov");
            lastname.add("Bolshakov");
            ArrayList<String> position = new ArrayList<>();
            position.add("Gynecologist");
            position.add("Ophthalmologist");
            position.add("Pediatrician");
            position.add("Therapist");
            position.add("Oncologist");
            position.add("Orthopedist");
            ArrayList<String> diagnosis = new ArrayList<>();
            diagnosis.add("Glaucoma");
            diagnosis.add("Cataract");
            diagnosis.add("Cancer");
            diagnosis.add("Fracture");
            ArrayList<Long> doctors = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Doctor newDoctor = new Doctor(firstname.get((int) Math.floor(Math.random() * firstname.toArray().length)),
                        lastname.get((int) Math.floor(Math.random() * lastname.toArray().length)),
                        position.get((int) Math.floor(Math.random() * position.toArray().length)),
                        "0" + (i % 10) + ":" + "0" + (i % 10), "1" + (i % 10) + ":" + "1" + (i % 10));
                doctorService.save(newDoctor);
                doctors.add(newDoctor.getId());
            }
            for (int i = 0; i < 10; i++) {
                int index = (int) Math.floor(Math.random() * doctors.toArray().length);
                Set<Doctor> doctor = doctorService.findById(doctors.get(index)).stream()
                        .map(doctorMapper::convertToEntity).collect(Collectors.toSet());
                Patient newPatient = new Patient(firstname.get((int) Math.floor(Math.random() * firstname.toArray().length)),
                        lastname.get((int) Math.floor(Math.random() * lastname.toArray().length)),
                        diagnosis.get((int) Math.floor(Math.random() * diagnosis.toArray().length)),
                        ((int) Math.floor(Math.random() * 99)), doctor);
                patientService.save(newPatient);
            }
        } else {
            log.info("Database is inizializier");
        }

    }
}


