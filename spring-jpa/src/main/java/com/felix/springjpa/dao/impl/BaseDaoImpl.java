package com.felix.springjpa.dao.impl;

import com.felix.springjpa.dao.BaseDao;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.io.Serializable;



public class BaseDaoImpl<T,ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T,ID> {

    private EntityManager entityManager;


    public BaseDaoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public BaseDaoImpl(Class<T> domainClass, EntityManager entityManager) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager), entityManager);
        this.entityManager = entityManager;
    }

}
