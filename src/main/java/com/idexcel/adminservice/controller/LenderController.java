package com.idexcel.adminservice.controller;

import com.idexcel.adminservice.dto.*;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.service.LenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "lenders")
public class LenderController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LenderService service;

    @GetMapping
    public List<LenderListDTO> findAll(){
        List<Lender> lenderList = service.findAll();

       return lenderList.stream().map(lender -> modelMapper.map(lender, LenderListDTO.class)).collect(Collectors.toList());

    }

    @GetMapping(value  = "{lenderId}")
    public LenderListByStatusDTO findById(@PathVariable("lenderId") String lenderId){
        Lender lender = service.findById(lenderId);
        return modelMapper.map(lender, LenderListByStatusDTO.class);
    }

    @PostMapping
    public void createLender(@RequestBody LenderCreateDTO lenderCreateDTO) {
        Lender lender = modelMapper.map(lenderCreateDTO, Lender.class);
        String id = service.create(lender);

    }

    @PutMapping(value="{lenderId}")
    public void updateLender(@PathVariable("lenderId") String lenderId, @RequestBody LenderUpdateDTO lenderUpdateDTO){
        Lender lender = modelMapper.map(lenderUpdateDTO, Lender.class);
        service.update(lenderId, lender);

    }

    @DeleteMapping(value="{lenderId}")
    public void deleteLender(@PathVariable("lenderId") String lenderId){
        service.delete(lenderId);
    }

    @PatchMapping(value="{lenderId}")
    public void patchLender(@PathVariable("lenderId") String lenderId, @RequestBody LenderPatchDTO lenderPatchDTO){
        Lender lender = modelMapper.map(lenderPatchDTO, Lender.class);
        service.update(lenderId, lender);
    }



}
