package com.arprave.service.impl;

import com.arprave.model.Consult;
import com.arprave.model.Exam;
import com.arprave.repo.IConsultExamRepo;
import com.arprave.repo.IConsultRepo;
import com.arprave.repo.IGenericRepo;
import com.arprave.service.IConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    @Autowired
    private IConsultRepo consultRepo;

    @Autowired
    private IConsultExamRepo ceRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }

    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        //Consult + ConsultDetail
        consult.getDetails().forEach(det -> det.setConsult(consult));
        consultRepo.save(consult);

        //ConsultExam
        exams.forEach(ex -> ceRepo.saveExam(consult.getIdConsult(), ex.getIdExam()));

        return consult;
    }

    @Override
    public List<Consult> search(String dni, String fullname) {
        return consultRepo.search(dni, fullname);
    }

    @Override
    public List<Consult> searchByDates(LocalDateTime date1, LocalDateTime date2) {
        return consultRepo.searchByDates(date1, date2);
    }
}
