package com.arprave.service.impl;

import com.arprave.model.Medic;
import com.arprave.repo.IGenericRepo;
import com.arprave.repo.IMedicRepo;
import com.arprave.service.IMedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicServiceImpl extends CRUDImpl<Medic, Integer> implements IMedicService {

    @Autowired
    private IMedicRepo repo;

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }
}
