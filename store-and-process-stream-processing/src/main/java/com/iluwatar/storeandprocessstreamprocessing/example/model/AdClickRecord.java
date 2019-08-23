package com.iluwatar.storeandprocessstreamprocessing.example.model;

/**
 * AdClickRecord representing a Record Class for when a user clicks on an ad.
 *
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 * @see com.iluwatar.storeandprocessstreamprocessing.Record
 */
public class AdClickRecord extends StatefulRecord {
  
  /**
   * Constructor for AdClickRecord, allows control over processed state.
   * @param processed whether or not record is processed.
   */
  public AdClickRecord(Boolean processed) {
    super(processed);
  }
  
  /**
   * Processes the AdClickRecord, execute what task needs to process and set task as processed.
   * @return if processing went successful.
   */
  @Override
  public boolean process() {
    this.setProcessed(Boolean.TRUE);
    return true;
  }
}
