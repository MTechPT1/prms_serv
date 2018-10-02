/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.core.exceptions;

/**
 *
 * @author Karen Athaide
 */

public class ErrorMessage {
    private String httpStatus;
    private String errorMessage;
    
    /**
     *
     */
    public ErrorMessage() {}
    
    /**
     *
     * @param httpStatus
     * @param errorMessage
     */
    public ErrorMessage(String httpStatus, String errorMessage)
    {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
    /**
     * @return the httpStatus
     */
    public String getHttpStatus() {
        return httpStatus;
    }
    /**
     * @param httpStatus
     */
    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }
    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    /**
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }   
}