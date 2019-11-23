/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.services;

import br.com.smartercode.zombiesurvival.ws.shared.dto.SurvivorDTO;
import java.util.List;

/**
 *
 * @author junior
 */
public interface SurvivorsService {

    public SurvivorDTO create(SurvivorDTO survivor);

    public SurvivorDTO findBySurvivorId(String survivorId);

    public List<SurvivorDTO> listAll();

    public List<SurvivorDTO> listAllWithInventory();

    public List<SurvivorDTO> listAllSurvivorsWithInventory();

    public SurvivorDTO updateLocation(SurvivorDTO survivorDTO);
}
