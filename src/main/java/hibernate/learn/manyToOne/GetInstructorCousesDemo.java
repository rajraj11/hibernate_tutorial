package hibernate.learn.manyToOne;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCousesDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            int id=1;
            Instructor instructor=session.get(Instructor.class,id);
            System.out.println(instructor.toString());
            System.out.println(instructor.getCourseList());
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
