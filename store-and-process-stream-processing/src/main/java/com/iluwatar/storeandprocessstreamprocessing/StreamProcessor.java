package com.iluwatar.storeandprocessstreamprocessing;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 */
public abstract class StreamProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(StreamProcessor.class);

  //Default batch size, used as guide and default value for StreamProcessors.
  public static final int DEFAULT_BATCH_SIZE = 200;

  protected final Queue<Record> batch;

  /**
   * Default StreamProcessor Implementation
   */
  protected StreamProcessor() {
    this.batch = new ArrayDeque<>(DEFAULT_BATCH_SIZE);
  }

  protected StreamProcessor(Queue<Record> batch) {
    this.batch = batch;
  }

  /**
   * Adds multiple records to the end of the batch, in order of insertion.
   *
   * @param <T> Record Type, anything that is a record (f.e: CustomerRecord).
   * @param records Single/multiple records to be added to the batch.
   * @return if all records are successfully added to the end of the batch.
   */
  @SafeVarargs
  protected final <T extends Record> boolean addRecordsToBatch(T... records) {
    boolean successful = false;

    LOGGER.info("Adding {} to batch.", Arrays.toString(records));
    for (T record : records) {
      if (!(successful = addRecordToBatch(record))) {
        LOGGER.warn("Stopped adding records {}, as last insertion was unsuccessful.",
            Arrays.toString(records));
        break;
      }
    }
    LOGGER.info("Done adding {} to batch.", Arrays.toString(records));

    return successful;
  }

  /**
   * Adds a single record to the end of the batch.
   *
   * @param <T> Record Type, anything that is a record (f.e: CustomerRecord).
   * @param record Record to be added to the batch.
   * @return if the record has successfully been added to the end of the batch.
   */
  protected final <T extends Record> boolean addRecordToBatch(T record) {
    if (!this.batch.offer(record)) {
      LOGGER.warn("Unable to add {} to batch.", record);
      return false;
    }

    LOGGER.info("Successfully added {} to batch ({}).", record, this.batch.size());
    return true;
  }

  /**
   * Processes the batch in order of insertion (FIFO)
   */
  protected void processBatch() throws NullPointerException {
    LOGGER.info("Started processing batch {}", this.batch);
    while (!this.batch.isEmpty()) {
      final Record record = this.batch.poll();
      if (Objects.isNull(record)) {
        LOGGER.warn("Unable to process null");
        throw new NullPointerException();
      }

      //Process the task
      if (record.process()) {
        LOGGER.info("Successfully processed {} ({}).", record, this.batch.size());
      } else {
        LOGGER.warn("Unsuccessfully processed {} ({}).", record, this.batch.size());
      }
    }

    LOGGER.info("Done processing batch.");
  }
}
