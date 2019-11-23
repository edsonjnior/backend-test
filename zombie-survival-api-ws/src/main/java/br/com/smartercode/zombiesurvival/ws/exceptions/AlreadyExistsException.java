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
public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 8036093089020169757L;

    public AlreadyExistsException(String message) {
        super(message);
    }

}
