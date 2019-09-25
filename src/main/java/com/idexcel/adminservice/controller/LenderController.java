package com.idexcel.adminservice.controller;

import com.idexcel.adminservice.dto.*;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.service.LenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "lenders")
public class LenderController {

    @Autowired
    private ModelMapper modelMapper;


    @Qualifier("lenderServiceImpl")
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
    public ResponseEntity<Object> createLender(@Valid @RequestBody LenderCreateDTO lenderCreateDTO, HttpServletRequest request) {
        Lender lender = modelMapper.map(lenderCreateDTO, Lender.class);
        String id = service.create(lender);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Location", request.getRequestURL().toString() + "/" + id);
        return new ResponseEntity<>("Id: " + id, responseHeaders, HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value="{lenderId}")
    public void updateLender(@PathVariable("lenderId") String lenderId, @Valid @RequestBody LenderUpdateDTO lenderUpdateDTO){
        Lender lender = service.updateById(lenderId);
        modelMapper.map(lenderUpdateDTO, lender);
        service.update(lenderId, lender);

    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value="{lenderId}")
    public void deleteLender(@PathVariable("lenderId") String lenderId){
        service.delete(lenderId);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping(value="{lenderId}")
    public void patchLender(@PathVariable("lenderId") String lenderId, @Valid @RequestBody LenderPatchDTO lenderPatchDTO){

        Lender lender = service.findById(lenderId);
        modelMapper.map(lenderPatchDTO, lender);
        service.update(lenderId, lender);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "{lenderId}", method = RequestMethod.HEAD)
    public void getStatus(@PathVariable("lenderId") String lenderId){
        service.findById(lenderId);
    }

}
