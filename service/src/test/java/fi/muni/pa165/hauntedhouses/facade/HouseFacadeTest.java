package fi.muni.pa165.hauntedhouses.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.dto.HouseDTO;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.service.HouseService;

/**
 *
 * @author Mario Majernik, 422165
 *
 */

public class HouseFacadeTest extends AbstractFacadeTest {

    @Mock
    private HouseService houseService;

    @Autowired
    @InjectMocks
    private HouseFacadeImpl houseFacade;

    private House house;
    private HouseDTO houseDTO;

    private Long houseId = 1L;
    private String houseName = "My House";

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        houseFacade = (HouseFacadeImpl) unwrapProxy(houseFacade);
        ReflectionTestUtils.setField(houseFacade, "houseService", houseService);
        ReflectionTestUtils.setField(houseFacade, "beanMappingService", beanMappingService);

        house = new House();
        house.setName(houseName);

        houseDTO = new HouseDTO();
        houseDTO.setName(houseName);
    }

    @Test
    public void testCreateHouse() {
        when(beanMappingService.mapTo(houseDTO, House.class)).thenReturn(house);
        houseFacade.createHouse(houseDTO);

        verify(houseService).createHouse(house);
        verify(beanMappingService).mapTo(houseDTO, House.class);
    }

    @Test
    public void testUpdateHouse() {
        when(beanMappingService.mapTo(houseDTO, House.class)).thenReturn(house);
        houseFacade.createHouse(houseDTO);

        verify(houseService).createHouse(house);
        verify(beanMappingService).mapTo(houseDTO, House.class);

        houseDTO.setHistory("new history");

        houseFacade.updateHouse(houseDTO);
        verify(houseService).updateHouse(house);
        verify(beanMappingService, times(2)).mapTo(houseDTO, House.class);
    }

    @Test
    public void testDeleteHouse() {
        when(beanMappingService.mapTo(houseDTO, House.class)).thenReturn(house);
        houseFacade.createHouse(houseDTO);

        verify(houseService).createHouse(house);
        verify(beanMappingService).mapTo(houseDTO, House.class);

        houseFacade.deleteHouse(houseId);
        verify(houseService).deleteHouse(house);
        verify(beanMappingService.mapTo(houseDTO, House.class));
    }

    @Test
    public void testFindAll() {
        when(houseService.findAllHouses()).thenReturn(Collections.singletonList(house));

        List<HouseDTO> abilities = houseFacade.findAllHouses();

        assertThat(house.getName()).isEqualTo(abilities.get(0).getName());
        verify(houseService).findAllHouses();
        verify(beanMappingService).mapTo(Collections.singletonList(house), HouseDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(houseService.findAllHouses()).thenReturn(null);

        List<HouseDTO> houses = houseFacade.findAllHouses();

        assertThat(houses).isNull();
        verify(houseService).findAllHouses();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(houseService.findById(houseId)).thenReturn(house);

        HouseDTO houseDTO = houseFacade.findById(houseId);

        assertThat(houseDTO).isNotNull();
        assertThat(house.getName()).isEqualTo(houseDTO.getName());
        verify(houseService).findById(houseId);
        verify(beanMappingService).mapTo(house, HouseDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(houseService.findById(houseId)).thenReturn(null);

        HouseDTO houseDTO = houseFacade.findById(houseId);

        assertThat(houseDTO).isNull();
        verify(houseService).findById(houseId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(houseService.findByName(houseName)).thenReturn(house);

        HouseDTO houseDTO = houseFacade.findByName(houseName);

        assertThat(houseDTO).isNotNull();
        assertThat(house.getName()).isEqualTo(houseDTO.getName());
        verify(houseService).findByName(houseName);
        verify(beanMappingService).mapTo(house, HouseDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(houseService.findByName(houseName)).thenReturn(null);

        HouseDTO houseDTO = houseFacade.findByName(houseName);

        assertThat(houseDTO).isNull();
        verify(houseService).findByName(houseName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

}
