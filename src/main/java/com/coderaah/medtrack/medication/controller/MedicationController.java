package com.coderaah.medtrack.medication.controller;

import com.coderaah.medtrack.medication.dto.MedicationRequest;
import com.coderaah.medtrack.medication.dto.MedicationResponse;
import com.coderaah.medtrack.medication.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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









}
