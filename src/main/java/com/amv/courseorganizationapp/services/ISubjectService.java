package com.amv.courseorganizationapp.services;

import com.amv.courseorganizationapp.entities.Subject;

import java.util.Map;

public interface ISubjectService {

    Subject createSubject(Subject subject);

    boolean subjectExists(Long id);

    boolean deleteSubject(Long id);

    boolean updateSubject(Long id, Map<String, Object> updates);

    Subject getSubject(Long id);

}







