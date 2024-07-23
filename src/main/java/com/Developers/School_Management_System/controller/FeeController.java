package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Fee;
import com.Developers.School_Management_System.repo.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fees")
public class FeeController {
    @Autowired
    private FeeRepository feeRepository;

    @GetMapping
    public List<Fee> getAllFees() {
        return this.feeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fee> getFeeById(@PathVariable(value = "id") Long feeId)
            throws ResourceNotFoundException {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found for this id :: " + feeId));
        return ResponseEntity.ok().body(fee);
    }

    @PostMapping("/new")
    public Fee createFee(@RequestBody Fee fee) {
        return this.feeRepository.save(fee);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Fee> updateFee(@PathVariable(value = "id") Long feeId,
                                         @Validated @RequestBody Fee feeDetails) throws ResourceNotFoundException {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found for this id :: " + feeId));

        fee.setAmount(feeDetails.getAmount());
        fee.setDate(feeDetails.getDate());
        fee.setStatus(feeDetails.getStatus());
        fee.setStudent(feeDetails.getStudent());

        return ResponseEntity.ok(this.feeRepository.save(fee));
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteFee(@PathVariable(value = "id") Long feeId) throws ResourceNotFoundException {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found for this id :: " + feeId));

        this.feeRepository.delete(fee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
