package com.example.hospitalapp.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Patient {

    @Id
    @NotNull
    @Column(name = "patientid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Vue.class)
    private Long id;

    @NotNull
    @Column(name = "firstname")
    @JsonView(Views.Vue.class)
    private String firstName;

    @NotNull
    @Column(name = "lastname")
    @JsonView(Views.Vue.class)
    private String lastName;

    @NotNull
    @Column(name = "diagnosis")
    @JsonView(Views.Vue.class)
    private String diagnosis;
    @JsonView(Views.Vue.class)
    private int age;

    @ManyToMany
    @JoinTable(name = "patientacceptance",
            joinColumns = @JoinColumn(name="patientid"),
            inverseJoinColumns = @JoinColumn(name = "doctorid"))
    private Set<Doctor> doctors = new HashSet<>();

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Patient(){}

    public Patient(String firstName, String lastName, String diagnosis, int age, Set<Doctor> doctors) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.diagnosis = diagnosis;
        this.age = age;
        this.doctors = doctors;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}