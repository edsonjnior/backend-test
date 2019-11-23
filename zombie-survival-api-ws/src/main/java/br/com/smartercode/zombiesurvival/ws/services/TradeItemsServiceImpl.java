/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.services;

import br.com.smartercode.zombiesurvival.ws.exceptions.ErrorMessages;
import br.com.smartercode.zombiesurvival.ws.exceptions.TradeItemsException;
import br.com.smartercode.zombiesurvival.ws.io.dao.InventoryDAO;
import br.com.smartercode.zombiesurvival.ws.io.dao.ResourceDAO;
import br.com.smartercode.zombiesurvival.ws.io.dao.SurvivorDAO;
import br.com.smartercode.zombiesurvival.ws.model.request.InventoryRequestModel;
import br.com.smartercode.zombiesurvival.ws.model.request.SurvivorTradeRequestModel;
import br.com.smartercode.zombiesurvival.ws.shared.dto.SurvivorDTO;
import java.util.stream.IntStream;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author junior
 */
@Stateless
public class TradeItemsServiceImpl implements TradeItemsService {

    @Inject
    private SurvivorDAO survivorDao;

    @Override
    public void makeTrade(SurvivorTradeRequestModel survivorTradeOrigin, SurvivorTradeRequestModel survivorTradeDest) {
        SurvivorDTO survivorOrigin = survivorDao.findBySurvivorIdWithInventory(survivorTradeOrigin.getSurvivorId());
        SurvivorDTO survivorDest = survivorDao.findBySurvivorIdWithInventory(survivorTradeDest.getSurvivorId());

        // Validating quantities, survivors and points
        validateTrade(survivorOrigin, survivorDest, survivorTradeOrigin, survivorTradeDest);

        // Updating inventory items
        updateQuantities(survivorTradeOrigin, survivorOrigin, survivorDest);
        updateQuantities(survivorTradeDest, survivorDest, survivorOrigin);

        // Save changes into database
        survivorDao.edit(survivorOrigin);
        survivorDao.edit(survivorDest);
    }

    private void validateTrade(SurvivorDTO survivorOrigin, SurvivorDTO survivorDest, SurvivorTradeRequestModel survivorTradeOrigin, SurvivorTradeRequestModel survivorTradeDest) throws RuntimeException {
        // Checking if survivors was found
        checkStoredSurvivorFound(survivorOrigin, survivorTradeOrigin);
        checkStoredSurvivorFound(survivorDest, survivorTradeDest);

        // Checking if survivor's inventory is empty
        checkEmptyInventory(survivorTradeOrigin);
        checkEmptyInventory(survivorTradeDest);

        // Checking if survivor is infected
        checkSurvivorInfected(survivorOrigin);
        checkSurvivorInfected(survivorDest);

        // Checking if you have requested items from your inventory and you have enough amount of resources
        if (!availableItemsInventory(survivorTradeOrigin, survivorOrigin)) {
            throw new TradeItemsException("Trade not allowed. Requested items not available or insufficient amount of resources.");
        }

        // Checking if the other part has the items and enough amount of resources
        if (!availableItemsInventory(survivorTradeDest, survivorDest)) {
            throw new TradeItemsException("Trade not allowed. Requested items for other part not available or insufficient amount of resources.");
        }

        // Calculating amount of points
        int totalPointsOrigin = calculateAmountPoints(survivorTradeOrigin, survivorOrigin);
        int totalPointsDest = calculateAmountPoints(survivorTradeDest, survivorDest);

        if (totalPointsOrigin != totalPointsDest) {
            throw new TradeItemsException("Trade not allowed. You should verify the amount of points for each item.");
        }
    }

    private void checkStoredSurvivorFound(SurvivorDTO survivor, SurvivorTradeRequestModel survivorTrade) {
        if (survivor == null) {
            throw new TradeItemsException("Trade not allowed. We can't find survivor with id: " + survivorTrade.getSurvivorId());
        }
    }

    private boolean availableItemsInventory(SurvivorTradeRequestModel survivorTrade, SurvivorDTO survivor) {
        long count = survivorTrade.getItems().stream().filter(item
                -> survivor.getInventory().stream()
                        .anyMatch(inv -> inv.getResource().getResourceId().equals(item.getResourceId()) && inv.getQuantity() >= item.getQuantity()))
                .count();

        return survivorTrade.getItems().size() == count;
    }

    private int calculateAmountPoints(SurvivorTradeRequestModel survivorTrade, SurvivorDTO survivor) {
        return survivorTrade.getItems().stream().reduce(0, (partialSum, item) -> partialSum + item.getQuantity() * survivor.getInventory().stream()
                .filter(i -> i.getResource().getResourceId().equals(item.getResourceId()))
                .findFirst().get().getResource().getPoints(), Integer::sum);
    }

    private void checkEmptyInventory(SurvivorTradeRequestModel survivorTrade) {
        if (survivorTrade.getItems().size() <= 0) {
            throw new TradeItemsException("Trade not allowed. No items to trade for survivor id: " + survivorTrade.getSurvivorId());
        }
    }

    private void checkSurvivorInfected(SurvivorDTO survivor) {
        if (survivor.isInfected()) {
            throw new TradeItemsException("Trade not allowed. Survivor id: " + survivor.getSurvivorId() + " is infected.");
        }
    }

    private void updateQuantities(SurvivorTradeRequestModel survivorTrade, SurvivorDTO survivorOrigin, SurvivorDTO survivorDest) {
        survivorTrade.getItems().forEach(item -> {
            survivorOrigin.getInventory().forEach(a -> {
                if (item.getResourceId().equals(a.getResource().getResourceId())) {
                    int old = a.getQuantity();
                    int newQty = old - item.getQuantity();
                    a.setQuantity(newQty);
                }
            });
            survivorDest.getInventory().forEach(a -> {
                if (item.getResourceId().equals(a.getResource().getResourceId())) {
                    int old = a.getQuantity();
                    int newQty = old + item.getQuantity();
                    a.setQuantity(newQty);
                }
            });
        });
    }

}
