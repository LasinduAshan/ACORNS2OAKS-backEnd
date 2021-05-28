package lk.wixis360.website.service.impl;

import lk.wixis360.website.dto.StudentDTO;
import lk.wixis360.website.dto.SubjectDTO;
import lk.wixis360.website.entity.Subject;
import lk.wixis360.website.repo.StudentRepo;
import lk.wixis360.website.repo.SubjectRepo;
import lk.wixis360.website.service.StudentService;
import lk.wixis360.website.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepo repo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveSubject(SubjectDTO dto) {
        if (!repo.existsById(dto.getId())){
            Subject c = mapper.map(dto, Subject.class);
            repo.save(c);
        }else {
            throw new RuntimeException("Subject All ready exist");
        }
    }

    @Override
    public void updateSubject(SubjectDTO dto) {
        if (repo.existsById(dto.getId())) {
            Subject c = mapper.map(dto, Subject.class);
            repo.save(c);
        } else {
            throw new RuntimeException("No such subject for update..!");
        }
    }

    @Override
    public SubjectDTO searchSubject(String id) {
        Optional<Subject> subject =repo.findById(id);
        if (subject.isPresent()){
            return mapper.map(subject.get(), SubjectDTO.class);
        }else {
            throw new RuntimeException("No subject for ID "+ id);
        }
    }

    @Override
    public void deleteSubject(String id) {
        if (repo.existsById(id)){
            repo.deleteById(id);
        }else {
            throw new RuntimeException("No subject for the Delete ID "+ id);
        }
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<SubjectDTO>>(){
        }.getType());
    }
}
