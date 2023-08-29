package org.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

public class StudentDao {

    private String driver;
    private String url;
    private String userName;
    private String password;

    Connection con;

    Statement stmt;

    // Setter
    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public void setUrl(String url) { this.url = url; }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @PostConstruct
    public void init()
            throws SQLException, ClassNotFoundException
    {
        createStudentDBConnection();
    }


    public void createStudentDBConnection()
            throws ClassNotFoundException, SQLException
    {

        Class.forName(driver);


        con = DriverManager.getConnection(url, userName,
                password);


        stmt = con.createStatement();
    }

    public void selectAllRows()
            throws ClassNotFoundException, SQLException
    {

        System.out.println("Retrieving all student data..");


        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM studentdb.student");


        while (rs.next()) {
            int studentId = rs.getInt(1);
            String studentName = rs.getString(2);
            String age = rs.getString(3);


            System.out.println(studentId + " " + studentName
                    + " " + age );
        }
    }

    // Method 3
    public void deleteStudentRecord(int studentId)
            throws ClassNotFoundException, SQLException
    {
        System.out.println("Delete student data..");

        stmt.executeUpdate(
                "delete from studentdb.student where STUDENT_ID = "
                        + studentId);
        System.out.println("Record Deleted with the ID "
                + studentId);
    }

    // Method 4
    public void closeConnection() throws SQLException
    {
        con.close();
    }

    // Method 5
    @PreDestroy
    public void destroy() throws SQLException
    {
        closeConnection();
    }

}
