package hibernate.learn;

import entity.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudent {
    private static final Logger LOG=Logger.getLogger(QueryStudent.class);

    public static void main(String[] args) {
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session=factory.getCurrentSession();
        try {
            session.beginTransaction();
            List<Student> studentList=session.createQuery("from Student").getResultList();
            LOG.info(studentList);


            LOG.info("-------------------------------------------------------------------");
            studentList=session.createQuery("from Student s where s.firstName='pp'").getResultList();
            LOG.info(studentList);

            LOG.info("------------------------------- OR ------------------------------------");
            studentList=session.createQuery("from Student s where s.firstName='pp' or s.lastName='c'").getResultList();
            LOG.info(studentList);



            LOG.info("-------------------------Updating Values--------------------------------------------");
            Student student=session.get(Student.class,3);
            LOG.info("Before Updating : "+student);
            student.setFirstName("Updated");
            student=session.get(Student.class,3);
            LOG.info("After Updating :"+student);



            LOG.info("---------------------  Updating email id of all students----------------------------");
            session.createQuery("update Student set email='aaa@bbb.com'").executeUpdate();
            studentList=session.createQuery("from Student").getResultList();
            LOG.info(studentList);





            LOG.info("-------------------  Deleting column ------------------------------------------------");
            Student s1=session.get(Student.class,1);
            if(s1!=null)
                session.delete(s1);

            session.createQuery("delete from Student where id=2").executeUpdate();


            session.getTransaction().commit();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
