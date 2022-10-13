package ru.java_b34.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.java_b34.mantis.model.UserData;
import ru.java_b34.mantis.model.Users;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }
  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public UserData getUserByUsername(String username) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    UserData result = (UserData) session.createQuery( "from UserData where username = " + username).getSingleResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public UserData getUserById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    UserData result = (UserData) session.createQuery("from ContactData where id = " + id).getSingleResult();
    session.getTransaction().commit();
    session.close();
    return result;
  }
}
