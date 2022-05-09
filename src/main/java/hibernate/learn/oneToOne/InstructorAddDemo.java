package hibernate.learn.oneToOne;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InstructorAddDemo {
    public static void main(String[] args) {
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        Session session=factory.getCurrentSession();
        try {
            Instructor instructor=new Instructor("i1","l1","a@b.com");
            InstructorDetail instructorDetail=new InstructorDetail("y1@youtube.com","coding");
            instructor.setInstructorDetail(instructorDetail);
            session.beginTransaction();
            session.save(instructor);// This will also save detail object because of cascade type all.
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
