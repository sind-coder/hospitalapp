package com.example.hospitalapp.repositories;

import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByDoctorsId (Long id);
}
