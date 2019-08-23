package com.iluwatar.storeandprocessstreamprocessing;

/**
 * Inter
 *
 *
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 */
public interface Record {

  /**
   * The process method is an abstract method which can be called when processing a record.
   * This method is abstract as in that the producer does not know what happens when a record
   * gets processed.
   * How and when a record is processed, is decided by the designer of the architecture.
   * @return If the task has successfully been processed (initially).
   */
  boolean process();

}
