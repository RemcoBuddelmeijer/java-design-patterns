package com.iluwatar.storeandprocessstreamprocessing.example.database;

import com.iluwatar.storeandprocessstreamprocessing.example.model.CustomerRecord;

import java.util.List;

/**
 * Dao placeholder for DaoStreamProcessorImpl example.
 * Represents a Dao for CustomerRecord(s).
 * Created to support simulation for Out of Order Record arrival based Stream Processors.
 *
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 * @see com.iluwatar.storeandprocessstreamprocessing.example.database.Dao
 */
public class CustomerDao implements Dao<CustomerRecord> {
  
  /**
   * Fetches specific CustomerRecord from the DAO connected data source.
   * @param id Customer record id
   * @return CustomerRecord
   */
  @Override
  public CustomerRecord find(Long id) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Persists (inserts) specific CustomerRecord in the DAO connected data source.
   * @param entity CustomerRecord entity to be persisted.
   */
  @Override
  public void persist(CustomerRecord entity) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Merges (inserts or update) specific CustomerRecord in the DAO connected data source.
   * @param entity CustomerRecord entity to be merged.
   * @return merged CustomerRecord
   */
  @Override
  public CustomerRecord merge(CustomerRecord entity) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Deleted specific CustomerRecord from the DAO connected data source.
   * @param entity CustomerRecord entity to be deleted.
   */
  @Override
  public void delete(CustomerRecord entity) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Fetches all CustomerRecords from the DAO connected data source.
   * @return a List containing all CustomerRecords in existence.
   */
  @Override
  public List<CustomerRecord> findAll() {
    throw new UnsupportedOperationException();
  }
}
