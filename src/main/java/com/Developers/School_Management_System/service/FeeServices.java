package com.Developers.School_Management_System.service;

import com.Developers.School_Management_System.modal.Fee;
import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.repo.FeeRepository;

import com.Developers.School_Management_System.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeServices {
    private final FeeRepository feeRepository;
    @Autowired
    public FeeServices(FeeRepository feeRepository) {  this.feeRepository = feeRepository;}
    public List<Fee> getAllFee(){  return feeRepository.findAll();}
    public Fee getFeeById(Long id){return feeRepository.findById(id).orElse(null);}
    public void saveFee(Fee fee){feeRepository.save(fee);}
    public void deleteFee(Long id){feeRepository.deleteById(id);}
}
