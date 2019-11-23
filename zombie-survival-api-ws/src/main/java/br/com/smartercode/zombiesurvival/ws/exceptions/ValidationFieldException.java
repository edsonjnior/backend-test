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
public class ValidationFieldException extends RuntimeException {

    private static final long serialVersionUID = 6533841421849400306L;

    public ValidationFieldException(String message) {
        super(message);
    }

}
