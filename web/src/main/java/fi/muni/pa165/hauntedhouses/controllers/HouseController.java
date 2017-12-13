package fi.muni.pa165.hauntedhouses.controllers;

import fi.muni.pa165.hauntedhouses.ApiContract;
import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.facade.HouseFacade;
import java.util.Collections;

import java.util.List;

import javax.inject.Inject;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Klara Kufova, 410091
 */

@RestController
@RequestMapping(ApiContract.House.BASE)
public class HouseController {
    
    @Inject
    private HouseFacade houseFacade;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<HouseDTO> getHouses() {
        List<HouseDTO> result = houseFacade.findAllHouses();
        
        if (result == null) {
            result = Collections.emptyList();
        }
        
        return result;
    }
    
    // TODO

}
