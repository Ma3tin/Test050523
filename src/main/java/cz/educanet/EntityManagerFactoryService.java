package cz.educanet;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyUserApp");

    public EntityManagerFactory getEmf() {
        return emf;
    }

    @PreDestroy
    public void onDestroy() {
        emf.close();
    }
}
