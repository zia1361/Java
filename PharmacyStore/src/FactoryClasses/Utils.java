/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoryClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author A.U Computer
 */
public class Utils {
    private Connection oConnection;
    private PreparedStatement oStatement;
    private ResultSet oResult;
    
    public Utils() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        oConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Medicine;user=TECHFIXER;password=admin123;");
        System.out.println("db Connected");
    }
    
    public ResultSet GetResult(String query) throws SQLException{
        
        oStatement = oConnection.prepareStatement(query);
        oResult = oStatement.executeQuery();
        return oResult;
    }
    
    public int InsertData(String query){
        int result = 0;
        try{
            oStatement = oConnection.prepareStatement(query);
            result = oStatement.executeUpdate();
        
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    public int DeleteData(String query){
        int result = 0;
        try{
            oStatement = oConnection.prepareStatement(query);
            result = oStatement.executeUpdate();
        
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
