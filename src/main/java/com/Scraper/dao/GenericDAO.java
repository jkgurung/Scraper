package com.Scraper.dao;


import com.Scraper.utils.JpaUtil;
import com.Scraper.entity.WebPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class GenericDAO {

    public static final Logger LOGGER = LoggerFactory.getLogger(GenericDAO.class);
    private static GenericDAO INSTANCE = new GenericDAO();

    public static GenericDAO getInstance() {
        return GenericDAO.INSTANCE;
    }

    private GenericDAO() {
    }


    public void save(Object obj) {
        EntityManager entityManager = null;
        try {
            entityManager = JpaUtil.getEMF().createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("ERROR - save.", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }


    public boolean isExist(String inputUrl) {
        EntityManager entityManager = null;
        try {
            entityManager = JpaUtil.getEMF().createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<WebPage> query = entityManager
                    .createQuery(
                            "SELECT en FROM WebPage en WHERE en.inputUrl = :inputUrl", WebPage.class);
            query.setParameter("inputUrl", inputUrl);
            List<WebPage> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("ERROR - add.", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return true;
    }

}
