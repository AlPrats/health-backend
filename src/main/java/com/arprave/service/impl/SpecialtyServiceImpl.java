package com.arprave.service.impl;

import com.arprave.model.Specialty;
import com.arprave.repo.IGenericRepo;
import com.arprave.repo.ISpecialtyRepo;
import com.arprave.service.ISpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyServiceImpl extends CRUDImpl<Specialty, Integer> implements ISpecialtyService {

    @Autowired
    private ISpecialtyRepo repo;

    @Override
    protected IGenericRepo<Specialty, Integer> getRepo() {
        return repo;
    }
}
