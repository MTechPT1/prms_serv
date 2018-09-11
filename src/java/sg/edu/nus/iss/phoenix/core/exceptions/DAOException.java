/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core.exceptions;

/**
 * This class represents a generic DAO exception.  It will wrap any exception 
 * of the underlying code
 * @author Karen Athaide
 */
public class DAOException extends RuntimeException{
    
    //Constructors
    /**
     * Constructs a DAOException with a detailed message
     * @param message The detailed exception message
     */
    public DAOException(String message) {
        super(message);
    }
    
    /**
     * Constructs a DAOExpection with the root cause
     * @param cause The root cause of the exception
     */
    public DAOException(Throwable cause) {
        super (cause);
    }
    
    /**
     * Constructs a DAOException with detailed message and root cause
     * @param message The detailed exception message
     * @param cause The root cause of the exception
     */
    public DAOException(String message, Throwable cause) {
        super (message, cause);
    }
}
