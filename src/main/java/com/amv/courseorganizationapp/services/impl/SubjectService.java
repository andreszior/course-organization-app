package com.amv.courseorganizationapp.services.impl;

import com.amv.courseorganizationapp.entities.Subject;
import com.amv.courseorganizationapp.mappers.SubjectMapper;
import com.amv.courseorganizationapp.services.ISubjectService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SubjectService implements ISubjectService {

    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }


    @Override
    public Subject createSubject(Subject subject) {
        subjectMapper.createSubject(subject);
        return subject;
    }

    @Override
    public boolean subjectExists(Long id) {
        return subjectMapper.subjectExists(id);
    }

    @Override
    public boolean deleteSubject(Long id) {
        return subjectMapper.deleteSubject(id);
    }

    @Override
    public boolean updateSubject(Long id, Map<String, Object> updates) {
        Subject subject = subjectMapper.getSubjectById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    subject.setName((String) value);
                    break;
                case "idMentor":
                    subject.setIdTeacher((Long) value);
                    break;
            }
        });
        return subjectMapper.updateSubject(subject);
    }

    @Override
    public Subject getSubject(Long id) {
        return subjectMapper.getSubjectById(id);
    }
}