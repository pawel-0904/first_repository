package ru.sbrf.cu.core.dao;

import ru.sbrf.cu.core.model.Student;
import ru.sbrf.cu.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface StudentDao {
    Optional<Student> findById( long id);

    long saveStudent(Student student);

    SessionManager getSessionManager();

    /*void saveEmail( EMail email );*/
}