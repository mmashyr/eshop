package com.mmashyr.service;

import com.mmashyr.entity.BasicEntity;

import java.util.List;

/**
 * Created by Anton on 16.01.2017.
 */
public interface CRUDService<T extends BasicEntity> {

    public void save(T entity);

    public void delete(Long id);

    public T findOne(Long id);

    public List<T> getAll();
}
