/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.exceptions.mappers;

import br.com.smartercode.zombiesurvival.ws.exceptions.ErrorMessages;
import br.com.smartercode.zombiesurvival.ws.exceptions.TradeItemsException;
import br.com.smartercode.zombiesurvival.ws.model.response.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author junior
 */
@Provider
public class TradeItemsExceptionMapper implements ExceptionMapper<TradeItemsException> {

    @Override
    public Response toResponse(TradeItemsException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), ErrorMessages.TRADE_NOT_ALLOWED.name(), null);

        return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
    }

}
