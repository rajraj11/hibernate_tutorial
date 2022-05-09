package hibernate.learn.oneToOne;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InstructorDeleteDemo {
    public static void main(String[] args) {
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        Session session=factory.getCurrentSession();
        try {
            session.beginTransaction();
            int id=1;
            Instructor instructor=session.get(Instructor.class,id);
            if(instructor!=null)
            {
                session.delete(instructor); // It will also delete instructor_detail
            }
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
