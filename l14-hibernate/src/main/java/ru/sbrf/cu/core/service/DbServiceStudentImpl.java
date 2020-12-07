package ru.sbrf.cu.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbrf.cu.core.dao.StudentDao;
import ru.sbrf.cu.core.model.Student;
import ru.sbrf.cu.core.sessionmanager.SessionManager;

public class DbServiceStudentImpl implements DBServiceStudent {
  private static Logger logger = LoggerFactory.getLogger(DbServiceStudentImpl.class);

  private final StudentDao studentDao;

  public DbServiceStudentImpl(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public long saveStudent( Student student) {
    try (SessionManager sessionManager = studentDao.getSessionManager()) {
      sessionManager.beginSession();
      try {

        long studentId = studentDao.saveStudent(student);

        sessionManager.commitSession();

        return studentId;
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
        sessionManager.rollbackSession();
        throw new DbServiceException(e);
      }
    }
  }

}
