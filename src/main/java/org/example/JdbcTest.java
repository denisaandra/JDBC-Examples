package org.example;

import java.sql.*;

/**
 *
 * @author www.luv2code.com
 *
 */
public class JdbcTest {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        PreparedStatement myPrepStmt = null;
        ResultSet myRs = null;

        try {
            // Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student" , "student");

            System.out.println("Database connection successful!\n");

            // Create/prepare a statement
            myStmt = myConn.createStatement();
            myPrepStmt = myConn.prepareStatement("select * from employees where salary > ? and department = ?");

            // Set parameters
            myPrepStmt.setDouble(1, 80000);
            myPrepStmt.setString(2, "Legal");

            // Execute SQL queries
            int rowsAffected = myStmt.executeUpdate("insert into employees" +
                    "(last_name, first_name, email, department, salary) " +
                    "values" +
                    "('NewInsertedFirstName', 'NewInsertedLastName', 'dummyMail@gmail.com', 'HR', '20000.00')");
            myRs = myPrepStmt.executeQuery();

            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }

}