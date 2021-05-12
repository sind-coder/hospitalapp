package com.example.hospitalapp;

import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import com.example.hospitalapp.repositories.DoctorRepository;
import com.example.hospitalapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class InsertDataBase implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
                    ArrayList<String> firstname= new ArrayList<>();
                    firstname.add("Igor");
                    firstname.add("Fedor");
                    firstname.add("Alexander");
                    firstname.add("Semyon");
                    firstname.add("Alex");
                    firstname.add("Dmitriy");
                    firstname.add("Stepan");
                    ArrayList<String> lastname= new ArrayList<>();
                    lastname.add("Kuzmin");
                    lastname.add("Sidorov");
                    lastname.add("Petrov");
                    lastname.add("Seleznev");
                    lastname.add("Alexandrov");
                    lastname.add("Vilkov");
                    lastname.add("Bolshakov");
                    ArrayList<String> position= new ArrayList<>();
                    position.add("Gynecologist");
                    position.add("Ophthalmologist");
                    position.add("Pediatrician");
                    position.add("Therapist");
                    position.add("Oncologist");
                    position.add("Orthopedist");
                    ArrayList<String> diagnosis= new ArrayList<>();
                    diagnosis.add("Glaucoma");
                    diagnosis.add("Cataract");
                    diagnosis.add("Cancer");
                    diagnosis.add("Fracture");
                    ArrayList<Long> doctors = new ArrayList<>();
                    for (int i = 0; i < 10; i++ ){
                        Doctor c = new Doctor( firstname.get((int)Math.floor(Math.random() * firstname.toArray().length)), lastname.get((int)Math.floor(Math.random() * lastname.toArray().length)), position.get((int)Math.floor(Math.random() * position.toArray().length)),"0"+(i % 10) + ":" + "0"+(i % 10), "1"+(i % 10) + ":" + "1"+(i % 10));
                        doctorRepository.save(c);
                        doctors.add(c.getId());
                    }
                    for (int i = 0; i < 10; i++ ){
                        int inx = (int)Math.floor(Math.random() * doctors.toArray().length);
                        Optional<Doctor> doc = doctorRepository.findById(doctors.get(inx));
                        Set<Doctor> res = new HashSet<>();
                        doc.ifPresent(res::add);
                        Patient c = new Patient( firstname.get((int)Math.floor(Math.random() * firstname.toArray().length)), lastname.get((int)Math.floor(Math.random() * lastname.toArray().length)), diagnosis.get((int)Math.floor(Math.random() * diagnosis.toArray().length)),((int)Math.floor(Math.random() * 99)),res);
                        patientRepository.save(c);
                    }

                }
            }

