package com.idexcel.adminservice.service;

import com.idexcel.adminservice.dao.LenderRepo;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.enums.LenderStatus;
import com.idexcel.adminservice.exception.LenderExistsException;
import com.idexcel.adminservice.exception.LenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Calendar;

@Service
public class LenderServiceImpl implements LenderService{

    @Autowired
    private LenderRepo repo;

    @Override
    @Transactional
    public String create(Lender lender) {
       Optional<Lender> existing = repo.findByName(lender.getName());
       if(existing.isPresent())
           throw new LenderExistsException("Matching record found. Cannot create new lender. Lender with name exists!");
       lender.setStatus(LenderStatus.ACTIVE);
       lender.setCreatedBy("Hrishi G");
       lender.setCreatedDate(Calendar.getInstance().getTime());
       lender.setUpdatedBy("Hrishi G");
       lender.setUpdatedDate(Calendar.getInstance().getTime());
       Lender newLender = repo.save(lender);
        return newLender.getId();
    }

    @Override
    public List<Lender> findAll() {
         return repo.findAll();

    }

    @Override
    public Lender findById(String id) {
        Optional<Lender> existing = repo.findById(id);
        if(!existing.isPresent() || existing.get().getStatus() == LenderStatus.SUSPENDED )
            throw new LenderNotFoundException("Lender does not exist or status is suspended!");
        return existing.get();
    }

    @Override
    @Transactional
    public void update(String id, Lender lender) {
        lender.setUpdatedDate(Calendar.getInstance().getTime());
        lender.setId(id);
        repo.save(lender);
    }

    @Override
    public Lender updateById(String id) {
        Optional<Lender> existing = repo.findById(id);
        if(!existing.isPresent())
            throw new LenderNotFoundException("Lender not found!");
        return existing.get();
    }

    @Override
    @Transactional
    public void delete(String id) {
        Lender lender = updateById(id);
        repo.delete(lender);
    }

}
