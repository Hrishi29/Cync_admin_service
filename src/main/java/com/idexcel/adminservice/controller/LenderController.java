package com.idexcel.adminservice.controller;

import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.entity.Lender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "lenders")
public class LenderController {

    private LenderDTO dto;

    @GetMapping
    public List<Lender> findAll(){
        return null;
    }
}
