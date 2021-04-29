package com.example.hospitalapp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonView(Views.Vue.class)
    private Long id;

    @JsonView(Views.Vue.class)
    private String firstName;

    @JsonView(Views.Vue.class)
    private String lastName;

    @JsonView(Views.Vue.class)
    private String position;

    @JsonView(Views.Vue.class)
    private String time_start;

    @JsonView(Views.Vue.class)
    private String time_end;

    public Doctor(){}

    @ManyToMany
    @JoinTable(name = "patient_acceptance",
            joinColumns = {@JoinColumn(name="id_doctor")},
            inverseJoinColumns = {@JoinColumn(name = "id_patient")})
    private Set<Patient> patients = new HashSet<>();


    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Doctor(String firstName, String lastName, String position, String time_start, String time_end) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
