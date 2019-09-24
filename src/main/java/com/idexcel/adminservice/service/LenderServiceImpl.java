package com.idexcel.adminservice.service;

import com.idexcel.adminservice.dao.LenderRepo;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.exception.LenderExistsException;
import com.idexcel.adminservice.exception.LenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LenderServiceImpl implements LenderService{

    @Autowired
    private LenderRepo repo;

    @Override
    @Transactional
    public String create(Lender lender) {
       Optional<Lender> existing = repo.findByName("John");
       if(existing.isPresent())
           throw new LenderExistsException("Matching record found. Cannot create new lender. Lender with name exists!");
      repo.save(lender);
       return lender.getId();
    }

    @Override
    public List<Lender> findAll() {
         return repo.findAll();

    }

    @Override
    public Lender findById(String id) {
        Optional<Lender> existing = repo.findById(id);
        if(!existing.isPresent())
            throw new LenderNotFoundException("No matching identification found for lender!");
        return existing.get();
    }

    @Override
    @Transactional
    public void update(String id, Lender lender) {
        Optional<Lender> existing = repo.findById(id);
        if(!existing.isPresent())
            throw new LenderNotFoundException("Update failed. No matching identification found for lender!");
        repo.save(lender);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<Lender> existing = repo.findById(id);
        if(!existing.isPresent())
            throw new LenderNotFoundException("No matching identification found for lender!");
        repo.delete(existing.get());
    }
}
