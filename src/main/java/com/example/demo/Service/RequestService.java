package com.example.demo.Service;


import com.example.demo.Api.ApiException;
import com.example.demo.Model.*;
import com.example.demo.Repository.HospitalRepository;
import com.example.demo.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.AopInvocationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final HospitalRepository hospitalRepository;

    // HOSPITAL add urgent request
    public void addUrgentRequest(Integer hospitalId, Integer patientId, Requests request) {
        Hospital hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);

        if (hospital == null) {
            throw new ApiException("Hospital not found");
        }
        if (hospital.getPatients().contains(patientId)) {
            if (request.getTypeRequest().equalsIgnoreCase("urgent")) {
                if (request.getOnset().equalsIgnoreCase("core")
                        && request.getHoursCase() >= 3) {
                    saveRequest(request);
                } else if (request.getOnset().equalsIgnoreCase("pinmpra")
                        && request.getHoursCase() >= 5) {
                    saveRequest(request);
                } else if (request.getOnset().equalsIgnoreCase("unknown")
                        && request.getHoursCase() >= 6) {
                    saveRequest(request);
                }
            } else {
                throw new ApiException("Request must be urgent");
            }
        }
    }



    private void saveRequest(Requests request) {
        requestRepository.save(request);
        request.setHospital(request.getHospital());
        request.setHotLine(request.getHotLine());
    }
       // request.setHospital(hospital);



        // HOSPITAL
    public List<Requests> getAllRequests() {
        return requestRepository.findAll();
    }


    // HOSPITAL - HOTLINE
    public void updateRequest(Integer hospitalId,Integer requestId, Requests request) {
        Hospital hospital=hospitalRepository.findHospitalByHospitalId(hospitalId);
        if (hospital == null) {
            throw new ApiException("Hospital Not Found");
        }
        Requests requests=requestRepository.findRequestByRequestId(requestId);
        if (requests == null) {
            throw new ApiException("Request Not Found");
        }
        if (request.getStatusRequest().equalsIgnoreCase("emergency")) {
            request.setHotLine(request.getHotLine());
            requestRepository.save(request);
        }
    }


    public void deleteRequest(Integer hospitalId,Integer requestId) {
        Hospital hospital=hospitalRepository.findHospitalByHospitalId(hospitalId);
        if (hospital == null) {
            throw new ApiException("Hospital Not Found");
        }
        Requests request=requestRepository.findRequestByRequestId(requestId);
        if (request == null) {
            throw new ApiException("Request Not Found");
        }
        requestRepository.delete(request);
    }

     // --------------- Ebd point --------------
     public List<Requests> requestRelatedToEmergency(Integer requestId) {
         Requests request = requestRepository.findRequestByRequestId(requestId);
         if (request == null) {
             throw new ApiException("Request not found with id: " + requestId);
         }
         List<Requests> relatedRequests = new ArrayList<>();
         if (request.getStatusRequest().equalsIgnoreCase("emergency")) {
             relatedRequests.add(request);
             requestRepository.save(request);
         }
         return relatedRequests;
       }


    public Patient getPatientById(Integer patientId) {
        HealthRecord healthRecord = new HealthRecord();
        Patient patient = healthRecord.getPatient(); // تأكد من أن الطريقة تعيد المريض بناءً على المعرف
        if (patient == null) {
            throw new ApiException("Patient Not Found");
        }
        if (patient.getPatientId().equals(patientId)) {
            return patient;
        } else {
            throw new ApiException("Patient ID does not match");
        }
    }

      public Requests getRequestById(Integer requestId) {
        Requests request = requestRepository.findRequestByRequestId(requestId);
        if (request == null) {
            throw new ApiException("Request not found with id: " + requestId);
        }
        return request;
      }



    public void emergencyRequest(Integer hospitalId, Integer patientId, Requests request) {
        Hospital hospital = hospitalRepository.findHospitalByHospitalId(hospitalId);

        if (hospital == null) {
            throw new ApiException("Hospital not found");
        }

        if (hospital.getPatients().contains(patientId)) {
            if (request.getTypeRequest().equalsIgnoreCase("emergency")) {
                    saveRequest(request);
            } else {
                throw new ApiException("Request must be urgent");
            }
        }
    }



}
