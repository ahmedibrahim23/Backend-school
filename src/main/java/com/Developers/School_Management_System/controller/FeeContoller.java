package com.Developers.School_Management_System.controller;


import com.Developers.School_Management_System.Exception.ResourceNotFoundException;
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
@RequestMapping("/api/v1/")
public class FeeContoller {
    @Autowired
    private FeeRepository feeRepository;
    //get fee
    @GetMapping("/fees")
    public List<Fee> getAllFee(){
        return this.feeRepository.findAll();
    }
    //get fee by id
    @GetMapping("/fees/{id}")
    public ResponseEntity<Fee> getFeeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
            Fee fee= feeRepository.findById(id)
                    .orElseThrow(() ->  new  ResourceNotFoundException("fee not found of that id: "+ id));
            return ResponseEntity.ok().body(fee);


        }
        //save fee
        @PostMapping("fees")
        public Fee createFee(@RequestBody Fee fee){
            return this.feeRepository.save(fee);
        }
        //update fee
        @PutMapping("fees/id")
        public ResponseEntity<Fee> updateFee(@PathVariable(value = "id") Long id,
                                             @Validated @RequestBody Fee feeDetail) throws ResourceNotFoundException{

        Fee fee= feeRepository.findById(id)
                .orElseThrow(()-> new  ResourceNotFoundException("fee not found of id: "+id));
        fee.setAmount(feeDetail.getAmount());
        fee.setStatus(feeDetail.getStatus());
        fee.setDate(feeDetail.getDate());
        return ResponseEntity.ok(this.feeRepository.save(fee));
        }
        //delete fee
    public Map<String,Boolean> deleteFee(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Fee fee=feeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("fee not found of this id:"+id));
        this.feeRepository.delete(fee);
        Map<String, Boolean> response=new HashMap<>();
        response.put("delete",Boolean.TRUE);
        return response;

    }

    }





