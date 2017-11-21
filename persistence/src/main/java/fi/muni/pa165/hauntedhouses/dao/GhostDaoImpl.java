package fi.muni.pa165.hauntedhouses.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fi.muni.pa165.hauntedhouses.entity.Ghost;

/**
 *
 * @author Mario Majernik, 422165
 *
 */

@Repository
public class GhostDaoImpl implements GhostDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Ghost ghost) {
        em.persist(ghost);
    }

    @Override
    public void update(Ghost ghost) {
        em.merge(ghost);
    }

    @Override
    public void remove(Ghost ghost) {
        em.remove(ghost);
    }

    @Override
    public Ghost findById(Long id) {
        return em.find(Ghost.class, id);
    }

    @Override
    public Ghost findByName(String name) {
        try {
            return em.createQuery("SELECT g FROM Ghost g WHERE g.name = :name", Ghost.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Ghost> findAll() {
        return em.createQuery("SELECT g FROM Ghost g", Ghost.class).getResultList();
    }
}
