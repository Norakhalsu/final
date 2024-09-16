package com.example.demo.Controller;

import com.example.demo.Model.Hospital;
import com.example.demo.Service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping("/add")//ADMIN
    public ResponseEntity addHospital(@RequestBody @Valid Hospital hospital) {
        hospitalService.addHospitalToSystem(hospital);
        return ResponseEntity.status(200).body("hospital added successfully");
    }

    @GetMapping("/get-all")//ADMIN
    public ResponseEntity getAllHospitals() {
        return ResponseEntity.status(200).body(hospitalService.getAllHospitals());
    }

    @PutMapping("/update/{hospitalId}")//ADMIN
    public ResponseEntity updateHospital(@PathVariable  Integer hospitalId,@RequestBody @Valid Hospital hospital) {
        hospitalService.updateHospital(hospitalId,hospital);
        return ResponseEntity.status(200).body("hospital updated successfully");
    }

   // --------------------- end point ------------------
}
