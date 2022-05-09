package hibernate.learn.manyToOne;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCousesDemo {
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
            if(instructor!=null)
            {
                Course course1=new Course("C1");
                Course course2=new Course("C2");
                Course course3=new Course("C3");
                instructor.add(course1);
                instructor.add(course2);
                instructor.add(course3);
                session.save(course1);
                session.save(course2);
                session.save(course3);
                session.getTransaction().commit();
            }

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
