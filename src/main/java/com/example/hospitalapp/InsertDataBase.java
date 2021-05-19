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

import static java.util.stream.Collectors.toSet;

@Component
public class InsertDataBase implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorMapper doctorMapper;

    private static Logger log = Logger.getLogger(InsertDataBase.class.getName());

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        List<Doctor> doctorVerifyToEmpty = doctorService.findAll().stream().map(doctorMapper::convertToEntity).collect(Collectors.toList());
        if (doctorVerifyToEmpty.toArray().length == 0) {
            List<String> firstName = new ArrayList<>();
            firstName.add("Igor");
            firstName.add("Fedor");
            firstName.add("Alexander");
            firstName.add("Semyon");
            firstName.add("Alex");
            firstName.add("Dmitriy");
            firstName.add("Stepan");
            List<String> lastName = new ArrayList<>();
            lastName.add("Kuzmin");
            lastName.add("Sidorov");
            lastName.add("Petrov");
            lastName.add("Seleznev");
            lastName.add("Alexandrov");
            lastName.add("Vilkov");
            lastName.add("Bolshakov");
            List<String> position = new ArrayList<>();
            position.add("Gynecologist");
            position.add("Ophthalmologist");
            position.add("Pediatrician");
            position.add("Therapist");
            position.add("Oncologist");
            position.add("Orthopedist");
            List<String> diagnosis = new ArrayList<>();
            diagnosis.add("Glaucoma");
            diagnosis.add("Cataract");
            diagnosis.add("Cancer");
            diagnosis.add("Fracture");
            List<Long> doctors = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Doctor newDoctor = new Doctor(firstName.get((int) Math.floor(Math.random() * firstName.toArray().length)),
                        lastName.get((int) Math.floor(Math.random() * lastName.toArray().length)),
                        position.get((int) Math.floor(Math.random() * position.toArray().length)),
                        "0" + (i % 10) + ":" + "0" + (i % 10), "1" + (i % 10) + ":" + "1" + (i % 10));
                doctorService.save(newDoctor);
                doctors.add(newDoctor.getId());
            }
            for (int i = 0; i < 10; i++) {
                int index = (int) Math.floor(Math.random() * doctors.toArray().length);
                log.info(Integer.toString(index));
                Set<Doctor> doctor = doctorService.findById(doctors.get(index)).stream()
                        .map(doctorMapper::convertToEntity).collect(toSet());
                Patient newPatient = new Patient(firstName.get((int) Math.floor(Math.random() * firstName.toArray().length)),
                        lastName.get((int) Math.floor(Math.random() * lastName.toArray().length)),
                        diagnosis.get((int) Math.floor(Math.random() * diagnosis.toArray().length)),
                        ((int) Math.floor(Math.random() * 99)), doctor);
                patientService.save(newPatient);
            }
        } else {
            log.info("Database is inizializier");
        }

    }
}


