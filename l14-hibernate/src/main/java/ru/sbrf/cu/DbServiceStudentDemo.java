package ru.sbrf.cu;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbrf.cu.core.dao.StudentDao;
import ru.sbrf.cu.core.model.*;
import ru.sbrf.cu.core.service.DBServiceStudent;
import ru.sbrf.cu.core.service.DbServiceStudentImpl;
import ru.sbrf.cu.hibernate.HibernateUtils;
import ru.sbrf.cu.hibernate.dao.StudentDaoHibernate;
import ru.sbrf.cu.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;

public class DbServiceStudentDemo {
  private static Logger logger = LoggerFactory.getLogger(DbServiceStudentDemo.class);

  public static void main(String[] args) {

    SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", Student.class, Avatar.class, Course.class, StudentCourse.class, EMail.class);

    SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
    StudentDao studentDao = new StudentDaoHibernate(sessionManager);
    DBServiceStudent dbServiceStudent = new DbServiceStudentImpl(studentDao);

    //пусть у нас будет три курса
    Course courseMath = new Course(1, "Math");
    Course courseChemistry = new Course(2, "Chemistry");
    Course courseLanguage = new Course(3, "Language");

    //сформируем курсы для Студента_3000
    List<Course> courses3000 = new ArrayList<Course>();
    courses3000.add(courseMath);
    courses3000.add(courseChemistry);

    //создадим аватар для Студента_3000
    Avatar avatar3000 = new Avatar(10,"photo_url_3000");

    //сохраним Студента_3000
    dbServiceStudent.saveStudent(new Student(3000, "Студент_3000", avatar3000, courses3000));

    //Сформируем курсы для Васи
    List<Course> coursesVasya = new ArrayList<Course>();
    coursesVasya.add(courseMath);
    coursesVasya.add(courseLanguage);

    //сохраним Васю
    dbServiceStudent.saveStudent(new Student(10,"Вася", new Avatar(11,"photo_url_VASYA"), coursesVasya));

    //Сформируем курсы для Тани
    List<Course> coursesTanya = new ArrayList<Course>();
    coursesTanya.add(courseChemistry);
    coursesTanya.add(courseLanguage);

    //сохраним Васю
    dbServiceStudent.saveStudent(new Student(10,"Таня", new Avatar(44,"photo_url_TANYA"), coursesTanya));

    sessionManager.beginSession();

    //сформируем серчстрин - студенты, записавшиеся на курс Language
    String queryString = String.format("select st.id, st.name, a.photo_url from Student st, Avatar a, StudentCourse sc, Course c " +
                    "where st.avatar_id = a.id and st.id = sc.student_id and sc.course_id = c.id and c.name = 'Language'");

    //Кверим студентов с курса Language
    List<Object[]> resultSet = sessionManager.getCurrentSession().getHibernateSession().createSQLQuery(queryString).list();

    System.out.println("======================================================");

    System.out.println("Id Студента     ||     Имя     ||       Аватар ");
    for (Object[] row : resultSet)
    {
      System.out.println("      " + row[0].toString() + "               " + row[1].toString() + "           " + row[2].toString());
    }
    System.out.println("======================================================");

    sessionManager.getCurrentSession().close();

    //В чем смысл полей Id в классах, если в бд записи по студентам лежат со своим Id (как собственно и по другим сущностям/экземплярам класс),
    // отличным от того, который я задавал при создании объекта класса? Они же никак не мапятся между собой?

  }

}
