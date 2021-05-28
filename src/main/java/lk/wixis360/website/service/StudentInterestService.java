package lk.wixis360.website.service;


import lk.wixis360.website.dto.AccountDTO;
import lk.wixis360.website.dto.StudentDTO;
import lk.wixis360.website.entity.Student;

public interface StudentInterestService {
    public void saveStudentInterest(Student student, String interestSubject);

}
