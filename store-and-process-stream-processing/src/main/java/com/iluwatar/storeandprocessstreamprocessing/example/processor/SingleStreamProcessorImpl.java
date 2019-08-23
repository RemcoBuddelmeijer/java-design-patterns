package com.iluwatar.storeandprocessstreamprocessing.example.processor;

import com.iluwatar.storeandprocessstreamprocessing.Record;
import com.iluwatar.storeandprocessstreamprocessing.StreamProcessor;

/**
 *
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 * @see com.iluwatar.storeandprocessstreamprocessing.StreamProcessor
 */
public class SingleStreamProcessorImpl extends StreamProcessor {

  /**
   * Add single record to batch and process immediately.
   * @param record Any class implementing the Record interface.
   * @see Record
   */
  public void onRecord(Record record) {
    if (this.addRecordToBatch(record)) {
      this.processBatch();
    }
  }
}
