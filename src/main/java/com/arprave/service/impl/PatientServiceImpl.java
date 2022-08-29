package com.arprave.service.impl;

import com.arprave.model.Patient;
import com.arprave.repo.IGenericRepo;
import com.arprave.repo.IPatientRepo;
import com.arprave.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl extends CRUDImpl<Patient, Integer> implements IPatientService {

    @Autowired
    private IPatientRepo repo;

    @Override
    protected IGenericRepo<Patient, Integer> getRepo() {
        return repo;
    }

    /*
    public PatientServiceImpl(IPatientRepo repo) {
        this.repo = repo;
    }*/

    /*
    @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient findById(Integer id) {
        Optional<Patient> op = repo.findById(id);
        return op.orElseGet(Patient::new);
        //op.isPresent() ? op.get() : new Patient();
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }*/
}
