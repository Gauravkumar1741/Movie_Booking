package com.movie.util;

import jakarta.persistence.*;

public class JPAUtil {

    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("moviePU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}