package com.example.demo.Service;


import com.example.demo.Api.ApiException;
import com.example.demo.Model.Hospital;
import com.example.demo.Repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public void addHospitalToSystem(Hospital hospital) {
        hospitalRepository.save(hospital);
    }


    // ADMIN
    public void updateHospital(Integer hospitalId,Hospital hospital) {
        Hospital hospitalToUpdate = hospitalRepository.findHospitalByHospitalId(hospitalId);
        if (hospitalToUpdate == null) {
            throw new ApiException("Hospital not found");
        }
        hospitalToUpdate.setHospitalName(hospital.getHospitalName());
        hospitalToUpdate.setLocation(hospital.getLocation());
        hospitalRepository.save(hospital);
    }


    // ADMIN
    public void deleteHospital(Integer hospitalId) {
        Hospital hospital=hospitalRepository.findHospitalByHospitalId(hospitalId);
        if (hospital == null) {
            throw new ApiException("Hospital not found");
        }
        hospitalRepository.delete(hospital);
    }
   // -------------------------------- End point -----------------


}
