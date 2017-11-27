package fi.muni.pa165.hauntedhouses.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
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

import fi.muni.pa165.hauntedhouses.dto.AbilityDTO;
import fi.muni.pa165.hauntedhouses.dto.GhostDTO;
import fi.muni.pa165.hauntedhouses.entity.Ability;
import fi.muni.pa165.hauntedhouses.entity.Ghost;
import fi.muni.pa165.hauntedhouses.service.GhostService;
/**
 * 
 * @author Mario Majernik, 422165
 *
 */
public class GhostFacadeTest extends AbstractFacadeTest{

    @Mock
    private GhostService ghostService;

    @Autowired
    @InjectMocks
    private GhostFacadeImpl ghostFacade;

    private Ghost ghost;

    private GhostDTO ghostDTO;
    
    private Ability ability;
    private AbilityDTO abilityDTO;
    
    private List<Ghost> ghosts;

    private Long ghostId = 1L;
    private String ghostName = "Witcher";

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ghostFacade = (GhostFacadeImpl) unwrapProxy(ghostFacade);
        ReflectionTestUtils.setField(ghostFacade, "ghostService", ghostService);
        ReflectionTestUtils.setField(ghostFacade, "beanMappingService", beanMappingService);

        ghost = new Ghost();
        ghost.setName(ghostName);

        ghostDTO = new GhostDTO();
        ghostDTO.setName(ghostName);
    }
    
    @Test
    public void testCreateGhost() {
    	when(beanMappingService.mapTo(ghostDTO, Ghost.class)).thenReturn(ghost);
    	ghostFacade.createGhost(ghostDTO);

    	verify(ghostService).createGhost(ghost);
        verify(beanMappingService).mapTo(ghostDTO, Ghost.class);
    }
    
    @Test
    public void testUpdateGhost() {
    	when(beanMappingService.mapTo(ghostDTO, Ghost.class)).thenReturn(ghost);
    	ghostFacade.createGhost(ghostDTO);

    	verify(ghostService).createGhost(ghost);
        verify(beanMappingService).mapTo(ghostDTO, Ghost.class);
        
        ghostDTO.setDescription("description");
        
        ghostFacade.updateGhost(ghostDTO);
        verify(ghostService).updateGhost(ghost);
        verify(beanMappingService).mapTo(ghostDTO, Ghost.class);
    }
    
    @Test
    public void testDeleteGhost() {
    	when(beanMappingService.mapTo(ghostDTO, Ghost.class)).thenReturn(ghost);
    	ghostFacade.createGhost(ghostDTO);

    	verify(ghostService).createGhost(ghost);
        verify(beanMappingService).mapTo(ghostDTO, Ghost.class);
        
        ghostFacade.deleteGhost(ghostId);
        verify(ghostService).deleteGhost(ghost);
        verify(beanMappingService.mapTo(ghostDTO, Ghost.class));
    }
    
    @Test
    public void testFindAll() {
        when(ghostService.findAll()).thenReturn(Collections.singletonList(ghost));

        List<GhostDTO> ghosts = ghostFacade.findAllGhosts();

        assertThat(ghost.getName()).isEqualTo(ghosts.get(0).getName());
        verify(ghostService).findAll();
        verify(beanMappingService).mapTo(Collections.singletonList(ghosts), GhostDTO.class);
    }
    
    @Test
    public void testFindAllWithNull() {
        when(ghostService.findAll()).thenReturn(null);

        List<GhostDTO> ghosts = ghostFacade.findAllGhosts();

        assertThat(ghosts).isNull();
        verify(ghostService).findAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }
    
    @Test
    public void testFindById() {
        when(ghostService.findById(ghostId)).thenReturn(ghost);

        GhostDTO ghostDTO = ghostFacade.findById(ghostId);
        
        assertThat(ghostDTO).isNotNull();
        assertThat(ghost.getName()).isEqualTo(ghostDTO.getName());
        verify(ghostService).findById(ghostId);
        verify(beanMappingService).mapTo(ghost, GhostDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(ghostService.findById(ghostId)).thenReturn(null);

        GhostDTO ghostDTO = ghostFacade.findById(ghostId);

        assertThat(ghostDTO).isNull();
        verify(ghostService).findById(ghostId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(ghostService.findByName(ghostName)).thenReturn(ghost);

        GhostDTO ghostDTO = ghostFacade.findByName(ghostName);

        assertThat(ghostDTO).isNotNull();
        assertThat(ghost.getName()).isEqualTo(ghostDTO.getName());
        verify(ghostService).findByName(ghostName);
        verify(beanMappingService).mapTo(ghost, GhostDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(ghostService.findByName(ghostName)).thenReturn(null);

        GhostDTO ghostDTO = ghostFacade.findByName(ghostName);

        assertThat(ghostDTO).isNull();
        verify(ghostService).findByName(ghostName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }
    
    @Test
    public void testFindByAbility() {
    	when(ghostService.findByAbility(ability)).thenReturn(ghosts);

        List<GhostDTO> ghostsDTO = ghostFacade.findByAbility(abilityDTO);

        assertThat(ghostDTO).isNotNull();
        assertThat(ghosts.get(0).getName()).isEqualTo(ghostsDTO.get(0).getName());
        verify(ghostService).findByAbility(ability);
        verify(beanMappingService).mapTo(ghost, GhostDTO.class);
    }
}
