package ru.sbrf.cu.core.sessionmanager;

import ru.sbrf.cu.hibernate.sessionmanager.DatabaseSessionHibernate;

public interface SessionManager extends AutoCloseable {
    void beginSession();
    void commitSession();
    void rollbackSession();
    void close();


    DatabaseSessionHibernate getCurrentSession();
}
