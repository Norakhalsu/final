package com.example.demo.Controller;


import com.example.demo.Model.Requests;
import com.example.demo.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
public class RequestController {
    private final RequestService requestService;

    @PostMapping("/new-request/{hospitalId}/{patientId}")//HOSPITAL
    public ResponseEntity newRequest(@PathVariable int hospitalId, @PathVariable int patientId , @RequestBody @Valid Requests request) {
        requestService.newRequest(hospitalId, patientId, request);
        return ResponseEntity.status(200).body("Request has been created");
    }

    @GetMapping("/get-all")// HOSPITAL
    public ResponseEntity getAllRequests() {
        return ResponseEntity.status(200).body(requestService.getAllRequests());
    }

    @PutMapping("/update/{hospitalId}/{requestId}")
    public ResponseEntity updateRequest(@PathVariable int hospitalId, @PathVariable int requestId, @RequestBody @Valid Requests request) {
        requestService.updateRequest(hospitalId, requestId, request);
        return ResponseEntity.status(200).body("Request has been updated");
    }


    @DeleteMapping("/delete/{hospitalId}/{requestId}")//HOSPITAL
    public ResponseEntity deleteRequest(@PathVariable int hospitalId, @PathVariable int requestId) {
        requestService.deleteRequest(hospitalId, requestId);
        return ResponseEntity.status(200).body("Request has been deleted");
    }


    // ------------------------------------- End point ----------------------
    @GetMapping("/emergency-cases/related-requests/{requestId}") // request related to emergency
    public ResponseEntity<List<Requests>> requestsRelatedToEmergencyCase(@PathVariable Integer requestId) {
       return ResponseEntity.status(200).body(requestService.requestRelatedToEmergency(requestId));
    }



}

