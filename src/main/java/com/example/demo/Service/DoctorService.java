package com.example.demo.Service;


import com.example.demo.Api.ApiException;
import com.example.demo.DTO.DoctorDTO;
import com.example.demo.DTO.PatientDTO;
import com.example.demo.Model.Appointment;
import com.example.demo.Model.Doctor;
import com.example.demo.Model.Patient;
import com.example.demo.Model.User;
import com.example.demo.Repository.DoctorRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;


    // ADMIN
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


    // ALL
    public void addDoctor(DoctorDTO doctorDTO){
        // 2 object [ user , Doctor]
        User user = new User();
        user.setUsername(doctorDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(doctorDTO.getPassword()));
        user.setEmail(doctorDTO.getEmail());
        user.setRole("DOCTOR");
        user.setFullName(doctorDTO.getFullName());
        user.setAge(doctorDTO.getAge());
        user.setAddress(doctorDTO.getAddress());
        user.setBirthDate(doctorDTO.getBirthDate());
        user.setEmergencyPhoneNumber(doctorDTO.getEmergencyPhoneNumber());
        user.setGender(doctorDTO.getGender());
        user.setCity(doctorDTO.getCity());


        Doctor doctor=new Doctor();
        doctor.setMajor(doctorDTO.getMajor());
        doctor.setHospitalName(doctorDTO.getHospitalName());
        doctor.setDegree(doctorDTO.getDegree());

        doctor.setUser(user);
        userRepository.save(user);
        doctorRepository.save(doctor);
    }


    // ADMIN -------------------------------
    public void updateDoctor(Integer id,Doctor doctor) {
        Doctor doctor1 =doctorRepository.findDoctorByDoctorId(id);
        if(doctor1==null){
            throw new ApiException("not found");
        }
        //  patient1.setHealthStatus(patient.getHealthStatus());
        //   patient1.setMedicalHistory(patient.getMedicalHistory());
        doctorRepository.save(doctor1);
    }


    // ADMIN ----------
    public void deleteDoctor(Integer id) {
        Doctor doctor =doctorRepository.findDoctorByDoctorId(id);
        if(doctor==null){
            throw new ApiException("not found");
        }
        doctorRepository.delete(doctor);
    }

   // ------------------------- End point -------------------------------

    // DOCTOR ---
   public int doctorAppointmentsCount(Integer doctorId) {
      Doctor doctor = doctorRepository.findDoctorByDoctorId(doctorId);

       if (doctor==null) {
           throw new ApiException("Doctor with ID " + doctorId + " not found");
       }
           Set<Appointment> appointments = doctor.getAppointments();
           return appointments.size();
          }




          // get all patient with doctor
    public List<Patient> getPatientsWithAppointments(Integer doctorId) {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);

        if (!doctor.isPresent()) {
            throw new ApiException("Doctor with ID " + doctorId + " not found");
        }
            Doctor doctor11 = doctor.get();
            Set<Appointment> appointments = doctor11.getAppointments();
            List<Patient> patientNames = new ArrayList<>();

            for (Appointment appointment : appointments) {
                patientNames.add(appointment.getPatient());
            }
            return patientNames;
        }


          // تفاصيل الموعيد بين الدكنور والمريض
//        public  Appointment getAppointmentWithPatient(Integer doctorId, Integer appointmentId) {}
//    Appointment appointment = doctorService.getPatientAppointment(doctorId, patientId);
//
//    if(appointment != null) {
//        return ResponseEntity.status(200).body(appointment);
//    }



}
