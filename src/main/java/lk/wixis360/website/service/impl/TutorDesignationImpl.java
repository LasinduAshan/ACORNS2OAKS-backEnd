package lk.wixis360.website.service.impl;

import lk.wixis360.website.entity.Student;
import lk.wixis360.website.entity.StudentInterest;
import lk.wixis360.website.entity.Tutor;
import lk.wixis360.website.entity.TutorDesignation;
import lk.wixis360.website.repo.StudentInterestRepo;
import lk.wixis360.website.repo.TutorDesignationRepo;
import lk.wixis360.website.service.StudentInterestService;
import lk.wixis360.website.service.TutorDesignationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TutorDesignationImpl implements TutorDesignationService {

    @Autowired
    TutorDesignationRepo repo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveTutorDesignation(Tutor tutor, String designation) {
        TutorDesignation tutorDesignation = new TutorDesignation(designation, tutor);
        repo.save(tutorDesignation);
    }
}
