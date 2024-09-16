package com.example.demo.Service;


import com.example.demo.Api.ApiException;
import com.example.demo.Model.Doctor;
import com.example.demo.Model.HealthRecord;
import com.example.demo.Model.Patient;
import com.example.demo.Model.User;
import com.example.demo.Repository.DoctorRepository;
import com.example.demo.Repository.HealthRecordRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthRecordService {
    private final HealthRecordRepository healthRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    // ADMIN
    public void addHealthRecord(Integer patientId, HealthRecord healthRecord) {
//        Doctor doctor=doctorRepository.findDoctorByDoctorId(doctorId);
//        if(doctor==null){
//            throw new ApiException("Doctor not found");
//        }
        Patient patient = patientRepository.findPatientByPatientId(patientId);
        if (patient == null) {
            throw new ApiException("Patient not found");
        }
        healthRecord.setPatient(patient);
        healthRecordRepository.save(healthRecord);
    }
//        // Assuming the patient always has a health record
//        healthRecord.setHealthHabits(patient.getHealthRecord().getHealthHabits());
//        healthRecord.setPastSurgeries(patient.getHealthRecord().getPastSurgeries());
//        healthRecord.setPastMedications(patient.getHealthRecord().getPastMedications());
//        healthRecord.setPastIllnesses(patient.getHealthRecord().getPastIllnesses());
//        healthRecord.setPatient(patient);
//        healthRecordRepository.save(healthRecord);
//    }

    // ADMIN
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    // ADMIN
    public void updateHealthRecord(Integer patientId,HealthRecord healthRecord) {
        Patient patient=patientRepository.findPatientByPatientId(patientId);
       // HealthRecord healthRecord1=healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if(patient==null) {
            throw new ApiException("Patient Not Found");
        }
        healthRecord.setHealthHabits(patient.getHealthRecord().getHealthHabits());
        healthRecord.setPastSurgeries(patient.getHealthRecord().getPastSurgeries());
        healthRecord.setPastMedications(patient.getHealthRecord().getPastMedications());
        healthRecord.setPatient(patient);
        healthRecordRepository.save(healthRecord);
//        healthRecord1.setHealthHabits(healthRecord.getHealthHabits());
//        healthRecord1.setPastIllnesses(healthRecord.getPastIllnesses());
//        healthRecord1.setPastMedications(healthRecord.getPastMedications());
//        healthRecord1.setPastSurgeries(healthRecord.getPastSurgeries());
    }

    // ADMIN
    public void deleteHealthRecord(Integer patientId,Integer healthRecordId) {
        Patient patient=patientRepository.findPatientByPatientId(patientId);
        HealthRecord healthRecord=healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if(patient==null) {
            throw new ApiException("Patient Not Found");
        }
        if(healthRecord==null) {
            throw new ApiException("Health Record Not Found");
        }
        healthRecordRepository.delete(healthRecord);
    }


    //Patient
    public HealthRecord getHealthRecordByPatientId(Integer patientId,Integer healthRecordId) {
        Patient patient = patientRepository.findPatientByPatientId(patientId);
        HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(healthRecordId);
        if (healthRecord == null) {
            throw new ApiException("Health Record Not Found");
        }
        if (healthRecord.getPatient().getPatientId().equals(patient.getPatientId())) {
            return healthRecord;
        } else {
            throw new ApiException("Patient Not Found");
        }

    }
// ------------------------------- end point ----
public void patientHealthHabitsUpdate(Integer patientId,  List<String> newHealthHabits) {
    HealthRecord healthRecord = healthRecordRepository.findHealthRecordByHealthRecordId(patientId);
    if(healthRecord==null){
        throw new ApiException("not found");
    }
    healthRecord.setHealthHabits(newHealthHabits);
    healthRecordRepository.save(healthRecord);
}

}

    









