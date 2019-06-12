package com.felix.springjpa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseDao<T,ID extends Serializable> extends CrudRepository<T,ID> {

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(Specification<T> specification, Pageable pageable);

    List<T> findAll(Specification<T> specification);
}
