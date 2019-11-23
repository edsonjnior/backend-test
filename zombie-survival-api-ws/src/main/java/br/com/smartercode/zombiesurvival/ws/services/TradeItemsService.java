/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.services;

import br.com.smartercode.zombiesurvival.ws.model.request.SurvivorTradeRequestModel;

/**
 *
 * @author junior
 */
public interface TradeItemsService {

    public void makeTrade(SurvivorTradeRequestModel survivorTradeOrigin, SurvivorTradeRequestModel survivorTradeDest);
}
