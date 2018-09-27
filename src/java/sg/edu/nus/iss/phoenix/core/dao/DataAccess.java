/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sg.edu.nus.iss.phoenix.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.core.exceptions.DAOException;

/**
 *
 * @author Karen Athaide
 */
public class DataAccess {
    private Connection connection;
    
    public DataAccess() {
        
    }
    
    public Connection getConnection() {
        return (connection);
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void openConnection() {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        try {
        this.connection = DriverManager.getConnection(DBConstants.dbUrl,
        DBConstants.dbUserName, DBConstants.dbPassword);
        }catch (SQLException e) {
                throw new DAOException(e);
            }
        
        /*
        if (System.getenv("RDS_HOSTNAME") != null) {
            try {
                String hostname = System.getenv("RDS_HOSTNAME");
                String port = System.getenv("RDS_PORT");
                String db = System.getenv("RDS_DB_NAME");
                String userName = System.getenv("RDS_USERNAME");
                String password = System.getenv("RDS_PASSWORD");
                
                String remote_dbUrl = "jdbc:mysql://"+hostname+":"+port+"/"+db;
                this.connection = DriverManager.getConnection(remote_dbUrl,
                        userName, password);
                
            }catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        */
    }
    
    public void closeConnection() {
        try {
            this.connection.close();
        }
        catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
