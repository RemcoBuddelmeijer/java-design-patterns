package com.iluwatar.storeandprocessstreamprocessing.example.model;

import com.iluwatar.storeandprocessstreamprocessing.Record;

/**
 * PageVisitRecord representing a Record Class for when a user visits a page.
 *
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 * @see com.iluwatar.storeandprocessstreamprocessing.Record
 */
public class PageVisitRecord extends StatefulRecord {

  /**
   * Constructor for PageVisitRecord, allows control over processed state.
   * @param processed whether or not record is processed.
   */
  public PageVisitRecord(Boolean processed) {
    super(processed);
  }

  /**
   * Processes the PageVisitRecord, execute what task needs to process and set task as processed.
   * @return if processing went successful.
   */
  @Override
  public boolean process() {
    this.setProcessed(Boolean.TRUE);
    return true;
  }

  @Override
  public String toString() {
    return String.format("PageVisitRecord(processed=%s)", this.getProcessed());
  }
}
