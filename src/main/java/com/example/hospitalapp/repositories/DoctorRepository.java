package com.example.hospitalapp.repositories;

import com.example.hospitalapp.model.Doctor;
import com.example.hospitalapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
List<Doctor> findByPatientsId (Long id);

}
