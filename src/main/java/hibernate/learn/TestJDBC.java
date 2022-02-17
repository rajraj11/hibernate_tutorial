package hibernate.learn;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
    public static void main(String[] args) {
        String jdbcUrl="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        String userName="root";
        String password="root";
        try {
            System.out.println("Connecting to database" +jdbcUrl);
            Connection con= DriverManager.getConnection(jdbcUrl,userName,password);
            System.out.println("Connection Successful");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
