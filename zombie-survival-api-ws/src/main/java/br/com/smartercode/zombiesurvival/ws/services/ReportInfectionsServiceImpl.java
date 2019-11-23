/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.smartercode.zombiesurvival.ws.services;

import br.com.smartercode.zombiesurvival.ws.io.dao.ReportInfectionDAO;
import br.com.smartercode.zombiesurvival.ws.io.dao.SurvivorDAO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.ReportInfectionDTO;
import br.com.smartercode.zombiesurvival.ws.shared.dto.SurvivorDTO;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author junior
 */
@Stateless
public class ReportInfectionsServiceImpl implements ReportInfectionsService {

    @Inject
    private ReportInfectionDAO reportInfectionDAO;

    @Inject
    private SurvivorDAO survivorDAO;

    @Override
    public void createReport(ReportInfectionDTO infectionDTO) {
        SurvivorDTO reporterSurvivorDTO = survivorDAO.findBySurvivorId(infectionDTO.getReporter().getSurvivorId());
        SurvivorDTO infectedSurvivorDTO = survivorDAO.findBySurvivorId(infectionDTO.getInfected().getSurvivorId());

        List<ReportInfectionDTO> reports = reportInfectionDAO.countReportsFromSurvivor(infectedSurvivorDTO);

        // Reporter can't report the same person twice
        if (reports.stream().filter(ri -> ri.getReporter().equals(reporterSurvivorDTO)).count() == 0) {
            // Survivor has been reported by 2 others survivors and needs to be flagged as zombie
            if (reports.size() > 1) {
                infectedSurvivorDTO.setInfected(true);
                survivorDAO.edit(infectedSurvivorDTO);
            }

            infectionDTO.setDateReport(new Date());
            infectionDTO.setReporter(reporterSurvivorDTO);
            infectionDTO.setInfected(infectedSurvivorDTO);

            reportInfectionDAO.create(infectionDTO);
        } else {
            throw new RuntimeException("You already reported this survivor.");
        }

    }

}
