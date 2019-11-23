/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.model.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junior
 */
@XmlRootElement
public class ErrorMessage {

    private String errorMessage;
    private String errorMessgeKey;
    private String href;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessage, String errorMessgeKey, String href) {
        this.errorMessage = errorMessage;
        this.errorMessgeKey = errorMessgeKey;
        this.href = href;
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

    /**
     * @return the errorMessgeKey
     */
    public String getErrorMessgeKey() {
        return errorMessgeKey;
    }

    /**
     * @param errorMessgeKey the errorMessgeKey to set
     */
    public void setErrorMessgeKey(String errorMessgeKey) {
        this.errorMessgeKey = errorMessgeKey;
    }

    /**
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href the href to set
     */
    public void setHref(String href) {
        this.href = href;
    }

}
