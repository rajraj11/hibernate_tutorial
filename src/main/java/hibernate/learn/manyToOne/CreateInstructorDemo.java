package hibernate.learn.manyToOne;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            Instructor instructor=new Instructor("kk","ll","kk@ll.com");
            InstructorDetail instructorDetail=new InstructorDetail("y1.com","Video Games");
            session.beginTransaction();
            instructor.setInstructorDetail(instructorDetail);
            session.save(instructor);
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
