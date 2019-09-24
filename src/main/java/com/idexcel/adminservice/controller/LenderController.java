package com.idexcel.adminservice.controller;

import com.idexcel.adminservice.dto.LenderCreateDTO;
import com.idexcel.adminservice.entity.Lender;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "lenders")
public class LenderController {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Lender> findAll(){
        return null;
    }

    @GetMapping(value  = "{lenderId}")
    public Lender findById(@PathVariable("lenderId") String lenderId){
        return null;
    }

    @PostMapping
    public void createLender(@RequestBody LenderCreateDTO lenderCreateDTO){
    }

    @PutMapping(value="{lenderId}")
    public void updateLender(@PathVariable("lenderId") String lenderId, @RequestBody Lender lender){
    }

    @DeleteMapping(value="{lenderId}")
    public void deleteLender(@PathVariable("lenderId") String lenderId){
    }

    @PatchMapping(value="{lenderId}")
    public void patchLender(@PathVariable("lenderId") String lenderId){
    }


}
