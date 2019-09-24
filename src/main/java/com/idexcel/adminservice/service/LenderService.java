package com.idexcel.adminservice.service;

import com.idexcel.adminservice.entity.Lender;

import java.util.List;

public interface LenderService {

    String create(Lender lender);
    List<Lender> findAll();
    Lender findById(String id);
    void update(String id, Lender lender);
    void delete(String id);
}
