package com.example.demo.Repository;

import com.example.demo.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository  extends JpaRepository<Hospital, Integer> {
    Hospital findHospitalByHospitalId(Integer hospitalId);
}
