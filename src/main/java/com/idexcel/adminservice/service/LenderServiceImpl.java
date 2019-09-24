package com.idexcel.adminservice.service;

import com.idexcel.adminservice.dao.LenderRepo;
import com.idexcel.adminservice.entity.Lender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LenderServiceImpl implements LenderService{

    @Autowired
    private LenderRepo repo;

    @Override
    public String create(Lender lender) {
       // Optional<Lender> existing = repo.findByName();
        return null;
    }

    @Override
    public List<Lender> findAll() {
        return null;
    }

    @Override
    public Lender findById(String id) {
        return null;
    }

    @Override
    public void update(String id, Lender lender) {

    }

    @Override
    public void delete(String id) {

    }
}
