package ru.sbrf.cu.hibernate.dao;


import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbrf.cu.core.dao.StudentDao;
import ru.sbrf.cu.core.dao.StudentDaoException;
import ru.sbrf.cu.core.model.Student;
import ru.sbrf.cu.core.sessionmanager.SessionManager;
import ru.sbrf.cu.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.sbrf.cu.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Optional;

public class StudentDaoHibernate implements StudentDao {
  private static Logger logger = LoggerFactory.getLogger(StudentDaoHibernate.class);

  private final SessionManagerHibernate sessionManager;

  public StudentDaoHibernate(SessionManagerHibernate sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override
  public Optional<Student> findById( long id) {
    DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
    try {
      return Optional.ofNullable(currentSession.getHibernateSession().find(Student.class, id));
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return Optional.empty();
  }

  @Override
  public long saveStudent(Student student) {
    DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
    try {
      Session hibernateSession = currentSession.getHibernateSession();
      if (student.getId() > 0) {
        hibernateSession.merge(student);
      } else {
        hibernateSession.persist(student);
      }
      return student.getId();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new StudentDaoException(e);
    }
  }

  @Override
  public SessionManager getSessionManager() {
    return sessionManager;
  }
}
