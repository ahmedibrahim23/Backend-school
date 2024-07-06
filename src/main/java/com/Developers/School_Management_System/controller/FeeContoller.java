package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.Exception.StudentNotFoundException;
import com.Developers.School_Management_System.modal.Fee;
import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.repo.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Fee")
public class FeeContoller {
    private  final FeeRepository feeRepository;

@Autowired
    public FeeContoller(FeeRepository feeRepository) {  this.feeRepository = feeRepository;}
  @GetMapping
    public List<Fee> getAllFee(){
        return feeRepository.findAll();
    }
    @GetMapping("/{id}")
    public Fee getFeeById( @PathVariable Long id){
        return feeRepository.findById(id).orElse(null);
    }
    @PostMapping
    public void saveFee(@RequestBody  Fee fee){
        feeRepository.save(fee);
    }
    @DeleteMapping("/{id}")
    public void deleteFee( @PathVariable Long id){
        if(!feeRepository.existsById(id)){
            throw new StudentNotFoundException(id);
        }
        feeRepository.deleteById(id);

    }
    Fee  updateFee(@PathVariable Long id, @RequestBody Fee fee){
        return feeRepository.findById(id)
                .map(fee1 ->{
                    fee1.setAmount(fee.getAmount());
                    fee1.setStatus(fee.getStatus());
                    fee1.setDate(fee.getDate());
                    fee1.setStudentTble(fee.getStudentTble());
                    return feeRepository.save(fee1);


                }).orElseThrow(() -> new StudentNotFoundException(id));

    }


}
