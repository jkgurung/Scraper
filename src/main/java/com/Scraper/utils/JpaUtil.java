package com.Scraper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JpaUtil.class);

    private static final EntityManagerFactory emf;
    static {
        try {
            emf = Persistence.createEntityManagerFactory("webpagedata");
        } catch (Throwable ex) {
            LOG.error("Initial  EntityManagerFactory creation failed", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEMF() {
        return emf;
    }

}