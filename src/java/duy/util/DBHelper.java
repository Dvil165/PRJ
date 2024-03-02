/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duy.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author baodu
 */
/*
    1. load driver (phai co san ben trong ung dung)
    2. create connection string
    3. open connection
    */
public class DBHelper implements Serializable{
    //1 load driver
    public static Connection getConnection() 
            throws SQLException, NamingException{ //ko con can den classnotfound nua, thay = naming
        
        //1. lay context hien hanh
        Context currentContext = new InitialContext();
        //2. lookup tomcat context
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        //3. lookup ds
        DataSource ds = (DataSource)tomcatContext.lookup("DS007");
        //4. open con from ds
        Connection con = ds.getConnection();
        // luc nay ko can truyen tham so nua, trao het cho container roi
        
        return con;
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        
//        String url = "jdbc:sqlserver://localhost:1433;"
//                + "databaseName=PRJ1850;";
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        
//        return con;
    }
    
    
    
    
    
}
