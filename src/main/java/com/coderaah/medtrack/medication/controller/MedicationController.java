package com.coderaah.medtrack.medication.controller;

import com.coderaah.medtrack.medication.dto.MedicationRequest;
import com.coderaah.medtrack.medication.dto.MedicationResponse;
import com.coderaah.medtrack.medication.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    private final MedicationService medicationService;
    public MedicationController(MedicationService medicationService){

        this.medicationService = medicationService;
    }
    @PostMapping
    public ResponseEntity<MedicationResponse> createMedication(@Valid @RequestBody MedicationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicationService.createMedication(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicationResponse>> getAllMedications(){
        return ResponseEntity.ok(medicationService.getAllMedications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationResponse>getMedicationById(@PathVariable Long id){
        return ResponseEntity.ok(medicationService.getMedicationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationResponse> updateMedication(@PathVariable Long id, @Valid @RequestBody MedicationRequest request){
        return ResponseEntity.ok(medicationService.updateMedication(id,request));
    }
















}
