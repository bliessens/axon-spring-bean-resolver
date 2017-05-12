package be.cheops.axer.axon;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
class AxonEntityManagerProvider implements EntityManagerProvider {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
