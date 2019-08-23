package com.iluwatar.storeandprocessstreamprocessing.example.processor;

import com.iluwatar.storeandprocessstreamprocessing.Record;
import com.iluwatar.storeandprocessstreamprocessing.StreamProcessor;
import com.iluwatar.storeandprocessstreamprocessing.example.model.AdClickRecord;
import com.iluwatar.storeandprocessstreamprocessing.example.model.PageVisitRecord;

/**
 *
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 * @see com.iluwatar.storeandprocessstreamprocessing.StreamProcessor
 */
public class MultipleStreamsProcessorImpl extends StreamProcessor {

  /**
   * Add single AdClickRecord record to batch and process immediately.
   * @param record A AdClickRecord object instance.
   */
  public void onAdClickRecord(AdClickRecord record) {
    if (this.addRecordToBatch(record)) {
      this.processBatch();
    }
  }

  /**
   * Add single PageVisitRecord record to batch and process immediately.
   * @param record A PageVisitRecord object instance.
   */
  public void onLandingPageVisitRecord(PageVisitRecord record) {
    if (this.addRecordToBatch(record)) {
      this.processBatch();
    }
  }
}
