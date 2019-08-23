package com.iluwatar.storeandprocessstreamprocessing.example.database;

import com.iluwatar.storeandprocessstreamprocessing.example.model.OrderRecord;

import java.util.List;

/**
 * Dao placeholder for DaoStreamProcessorImpl example.
 * Represents a Dao for OrderRecord(s), including a #findUnprocessedOrdersForCustomer method.
 * Created to support simulation for Out of Order Record arrival based Stream Processors.
 *
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 * @see com.iluwatar.storeandprocessstreamprocessing.example.database.Dao
 */
public class OrderDao implements Dao<OrderRecord> {
  
  /**
   * Fetches specific OrderRecord from the DAO connected data source.
   * @param id Order record id
   * @return OrderRecord
   */
  @Override
  public OrderRecord find(Long id) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Persists (inserts) specific OrderRecord in the DAO connected data source.
   * @param entity OrderRecord entity to be persisted.
   */
  @Override
  public void persist(OrderRecord entity) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Merges (inserts or update) specific OrderRecord in the DAO connected data source.
   * @param entity OrderRecord entity to be merged.
   * @return merged OrderRecord
   */
  @Override
  public OrderRecord merge(OrderRecord entity) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Deleted specific OrderRecord from the DAO connected data source.
   * @param entity OrderRecord entity to be deleted.
   */
  @Override
  public void delete(OrderRecord entity) {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Fetches all OrderRecords from the DAO connected data source.
   * @return a List containing all OrderRecords in existence.
   */
  @Override
  public List<OrderRecord> findAll() {
    throw new UnsupportedOperationException();
  }
  
  /**
   * Fetches all OrderRecords from the DAO connected data source that has a matching customer.
   * @param customerId Customer ID connected to the OrderRecord
   * @return a list containing all OrderRecords connected to the matching customer.
   */
  public List<OrderRecord> findUnprocessedOrdersForCustomer(Long customerId) {
    throw new UnsupportedOperationException();
  }
}
