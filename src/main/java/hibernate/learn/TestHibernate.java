package hibernate.learn;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session=factory.getCurrentSession();
        try
        {
            Student tempStudent=new Student("Kamal","Raj","kamal@123");
            session.beginTransaction();
            session.save(tempStudent);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        session=factory.getCurrentSession();
        try
        {
            session.beginTransaction();
            Student myStudent=session.get(Student.class,2);
            System.out.println("-----------------"+myStudent+"--------------------");
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            factory.close();
        }
    }
}
