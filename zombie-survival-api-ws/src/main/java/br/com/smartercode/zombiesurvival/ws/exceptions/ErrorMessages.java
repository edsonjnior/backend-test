/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.exceptions;

/**
 *
 * @author junior
 */
public enum ErrorMessages {

    VALIDATION_FIELD("Missing required field or field is not valid. Please check documentation for more details."),
    RECORD_ALREADY_EXISTS("Already exists."),
    INTERNAL_SERVER_ERROR("Internal server error."),
    TRADE_NOT_ALLOWED("Trade not allowed. Please check items.");

    private String errorMessage;

    private ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
