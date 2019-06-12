package com.felix.springjpa.service.impl;


import com.felix.springjpa.dao.BaseDao;
import com.felix.springjpa.service.BaseService;

import java.io.Serializable;

public class BaseServiceImpl<T, I extends Serializable> implements BaseService<T, I> {

    BaseDao<T, I> dao;

    public BaseServiceImpl() {
    }

    public BaseServiceImpl(BaseDao<T, I> dao) {
        this.dao = dao;
    }

    @Override
    public <S extends T> S save(S entity) {
        return dao.save(entity);
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return dao.saveAll(entities);
    }

    @Override
    public T findOne(I id) {
        return dao.findById(id).get();
    }

    @Override
    public boolean exists(I id) {
        return findOne(id) != null;
    }


    @Override
    public Iterable<T> findAll() {
        return dao.findAll();
    }

    @Override
    public Iterable<T> findAll(Iterable<I> ids) {
        return dao.findAllById(ids);
    }

    @Override
    public long count() {
        return dao.count();
    }

    @Override
    public void delete(I id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        dao.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
