package com.infernalwhaler.testproject.service;

import java.util.Set;

/**
 * @author sDeseure
 * @project TestProject
 * @date 18/08/2021
 */

public interface ICrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
