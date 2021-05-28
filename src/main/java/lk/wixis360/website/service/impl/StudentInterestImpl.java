package lk.wixis360.website.service.impl;

import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.StudentDTO;
import lk.wixis360.website.entity.Account;
import lk.wixis360.website.entity.Student;
import lk.wixis360.website.entity.StudentInterest;
import lk.wixis360.website.repo.AccountRepo;
import lk.wixis360.website.repo.StudentInterestRepo;
import lk.wixis360.website.service.AccountService;
import lk.wixis360.website.service.StudentInterestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StudentInterestImpl implements StudentInterestService {

    @Autowired
    StudentInterestRepo repo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveStudentInterest(Student student, String interestSubject) {
        StudentInterest studentInterest = new StudentInterest(interestSubject,student);
        repo.save(studentInterest);
    }
}
