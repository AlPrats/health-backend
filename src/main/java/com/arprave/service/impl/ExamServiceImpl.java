package com.arprave.service.impl;

import com.arprave.model.Exam;
import com.arprave.repo.IExamRepo;
import com.arprave.repo.IGenericRepo;
import com.arprave.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {

    @Autowired
    private IExamRepo repo;

    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }
}
