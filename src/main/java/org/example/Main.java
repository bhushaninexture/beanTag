package org.example;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext context
                = new ClassPathXmlApplicationContext(
                "Beans.xml");

        StudentDao studentDAO = context.getBean(
                "s", StudentDao.class);

        studentDAO.deleteStudentRecord(2);
        studentDAO.selectAllRows();
    }
}