package com.example.hospitalapp.model;

import com.fasterxml.jackson.annotation.JsonView;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="doctor")
public class Doctor {
    @Id
    @NotNull
    @Column(name = "doctorid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Vue.class)
    private Long id;

    @Column(name = "firstname")
    @NotNull
    @JsonView(Views.Vue.class)
    private String firstName;

    @Column(name = "lastname")
    @NotNull
    @JsonView(Views.Vue.class)
    private String lastName;

    @Column(name = "position")
    @NotNull
    @JsonView(Views.Vue.class)
    private String position;

    @Column(name = "timestart")
    @NotNull
    @JsonView(Views.Vue.class)
    private String timeStart;

    @Column(name = "timeend")
    @NotNull
    @JsonView(Views.Vue.class)
    private String timeEnd;

    public Doctor(){}

    @ManyToMany
    @JoinTable(name = "patientacceptance",
            joinColumns = {@JoinColumn(name="doctorid")},
            inverseJoinColumns = {@JoinColumn(name = "patientid")})
    private Set<Patient> patients = new HashSet<>();


    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Doctor(String firstName, String lastName,
                  String position, String timeStart, String timeEnd) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
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