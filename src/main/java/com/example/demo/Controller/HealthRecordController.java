package com.example.demo.Controller;


import com.example.demo.Api.ApiResponse;
import com.example.demo.Model.Doctor;
import com.example.demo.Model.HealthRecord;
import com.example.demo.Model.User;
import com.example.demo.Service.HealthRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/healthRecord")
public class HealthRecordController {

     private final HealthRecordService healthRecordService;


    @PostMapping("/add/to/{patientId}")//ADMIN
    public ResponseEntity addMedicalHistory( @PathVariable Integer patientId, @RequestBody @Valid HealthRecord healthRecord){
        healthRecordService.addHealthRecord(patientId, healthRecord);
        return ResponseEntity.ok(new ApiResponse("add Patient's Health Record successfully"));
    }

    @GetMapping("/get-all") //ADMIN
    public ResponseEntity getAllHealthRecords(){
        return ResponseEntity.ok(healthRecordService.getAllHealthRecords());
    }

    @PutMapping("/update/{patientId}") // ADMIN
    public ResponseEntity updateMedicalHistory(@PathVariable Integer patientId, @RequestBody @Valid HealthRecord healthRecord){
        healthRecordService.updateHealthRecord(patientId, healthRecord);
        return ResponseEntity.status(200).body(new ApiResponse("update Patient's Health Record Successful"));
    }

    @DeleteMapping("/delete/{patientId}/{healthRecordId}") // ADMIN
    public ResponseEntity deleteHealthRecord(@PathVariable Integer patientId,@PathVariable Integer healthRecordId){
        healthRecordService.deleteHealthRecord(patientId,healthRecordId);
        return ResponseEntity.ok(new ApiResponse("Health Record deleted"));
    }

    @GetMapping("/get-Health-Record/{patientId}/{healthRecordId}") // ADMIN
    public ResponseEntity getHealthRecord(@PathVariable Integer patientId,@PathVariable Integer healthRecordId){
        return ResponseEntity.status(200).body(healthRecordService.getHealthRecordByPatientId(patientId,healthRecordId));
    }


    // ----------------------- end point -----------------
    @PutMapping("/{patientId}/health-habits")
    public ResponseEntity updatePatientHealthHabits(@PathVariable Integer patientId, @Valid @RequestBody List<String> newHealthHabits) {
        healthRecordService.patientHealthHabitsUpdate(patientId,newHealthHabits);
        return ResponseEntity.status(200).body("update Patient's Health Habits successfully");
    }

}