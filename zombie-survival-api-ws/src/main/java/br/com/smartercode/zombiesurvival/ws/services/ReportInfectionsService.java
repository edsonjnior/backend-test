/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.services;

import br.com.smartercode.zombiesurvival.ws.shared.dto.ReportInfectionDTO;

/**
 *
 * @author junior
 */
public interface ReportInfectionsService {

    public void createReport(ReportInfectionDTO infectionDTO);
}
