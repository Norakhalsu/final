package com.example.demo.Service;

import com.example.demo.Api.ApiException;
import com.example.demo.DTO.PatientDTO;
import com.example.demo.Model.HealthRecord;
import com.example.demo.Model.Hospital;
import com.example.demo.Model.Patient;
import com.example.demo.Model.User;
import com.example.demo.Repository.HealthRecordRepository;
import com.example.demo.Repository.HospitalRepository;
import com.example.demo.Repository.PatientRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;
    private final HealthRecordRepository healthRecordRepository;


    // ADMIN
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }


    // USER - Patient
    public void addPatientToHospital(Integer hospitalId,PatientDTO patientDTO){
        // 2 object [ user , Patient]
        Hospital hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);
        if(hospital == null){
            throw new ApiException("hospital not found");
        }
        User user = new User();
        user.setUsername(patientDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(patientDTO.getPassword()));
        user.setEmail(patientDTO.getEmail());
        user.setRole("PATIENT");
        user.setFullName(patientDTO.getFullName());
        user.setAge(patientDTO.getAge());
        user.setAddress(patientDTO.getAddress());
        user.setBirthDate(patientDTO.getBirthDate());
        user.setPhoneNumber(patientDTO.getPhoneNumber());
        user.setEmergencyPhoneNumber(patientDTO.getEmergencyPhoneNumber());
        user.setGender(patientDTO.getGender());
        user.setCity(patientDTO.getCity());



        Patient patient=new Patient();
        patient.setPatientCurrentDiet(patientDTO.getPatientCurrentDiet());
        patient.setSensitivy(patientDTO.isSensitivy());
        patient.setHealthStatus(patientDTO.getHealthStatus());
        patient.setHospital(hospital);

      //  patient.setHospital(patient.getHospital());
       //patient.setHospital(hospital);


        patient.setUser(user);
        userRepository.save(user);
        patientRepository.save(patient);
    }


    // ADMIN
    public void updatePatient(Integer id,Patient patient) {
        Patient patient1 =patientRepository.findPatientByPatientId(id);
        if(patient1==null){
            throw new ApiException("not found");
        }
         //  patient1.setHealthStatus(patient.getHealthStatus());
        //   patient1.setMedicalHistory(patient.getMedicalHistory());

        patientRepository.save(patient1);
    }


    // ADMIN
    public void deletePatient(Integer id) {
        Patient patient1 =patientRepository.findPatientByPatientId(id);
        if(patient1==null){
            throw new ApiException("not found");
        }
        patientRepository.delete(patient1);
    }

    //---------------------------  end point  ---------------------------------






}
