package com.iluwatar.storeandprocessstreamprocessing.example.database;

import com.iluwatar.storeandprocessstreamprocessing.Record;

import java.util.List;

/**
 *
 * Dao based on 'com.iluwatar.servicelayer.common.Dao'
 *
 * @param <E> record
 */
public interface Dao<E extends Record> {
  
  E find(Long id);
  
  void persist(E entity);
  
  E merge(E entity);
  
  void delete(E entity);
  
  List<E> findAll();
}
