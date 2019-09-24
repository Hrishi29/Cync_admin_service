package com.idexcel.adminservice.controller;

import com.idexcel.adminservice.dto.*;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.service.LenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "lenders")
public class LenderController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LenderService service;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<LenderListDTO> findAll(){
        List<Lender> lenderList = service.findAll();

       return lenderList.stream().map(lender -> modelMapper.map(lender, LenderListDTO.class)).collect(Collectors.toList());

    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value  = "{lenderId}")
    public LenderListByStatusDTO findById(@PathVariable("lenderId") String lenderId){
        Lender lender = service.findById(lenderId);
        return modelMapper.map(lender, LenderListByStatusDTO.class);
    }


    @PostMapping
    public ResponseEntity.BodyBuilder createLender(@RequestBody LenderCreateDTO lenderCreateDTO) {
        Lender lender = modelMapper.map(lenderCreateDTO, Lender.class);
        String id = service.create(lender);
         String location = "http://localhost:8080/lenderd/" + id;

        return  ResponseEntity.created(URI.create(location));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="{lenderId}")
    public void updateLender(@PathVariable("lenderId") String lenderId, @RequestBody LenderUpdateDTO lenderUpdateDTO){
        Lender lender = modelMapper.map(lenderUpdateDTO, Lender.class);
        service.update(lenderId, lender);

    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="{lenderId}")
    public void deleteLender(@PathVariable("lenderId") String lenderId){
        service.delete(lenderId);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping(value="{lenderId}")
    public void patchLender(@PathVariable("lenderId") String lenderId, @RequestBody LenderPatchDTO lenderPatchDTO){
        Lender lender = modelMapper.map(lenderPatchDTO, Lender.class);
        service.update(lenderId, lender);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "{lenderId}", method = RequestMethod.HEAD)
    public void getStatus(@PathVariable("lenderId") String lenderId){
        service.findById(lenderId);
    }

}
