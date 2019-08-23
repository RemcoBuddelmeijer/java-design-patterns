package com.iluwatar.storeandprocessstreamprocessing.example.model;

import com.iluwatar.storeandprocessstreamprocessing.Record;
import java.io.Serializable;

/**
 * @author Remco Buddelmeijer (remco.buddelmeijer@gmail.com)
 * @author Jakob Jenkov (creator of design pattern)
 * @see com.iluwatar.storeandprocessstreamprocessing.Record
 */
public abstract class StatefulRecord implements Record, Serializable, Comparable<StatefulRecord> {

  private Boolean processed;

  protected StatefulRecord(Boolean processed) {
    this.processed = processed;
  }

  public Boolean getProcessed() {
    return processed;
  }

  protected void setProcessed(Boolean processed) {
    this.processed = processed;
  }

  @Override
  public String toString() {
    return String.format("StateFulRecord(processed=%s)", processed);
  }

  @Override
  public int compareTo(StatefulRecord o) {
    if (o.getProcessed()) {
      return this.getProcessed() ? 0 : 1;
    }
    return this.getProcessed() ? -1 : 0;

  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (!(o instanceof StatefulRecord)) {
      return false;
    }

    return this.compareTo((StatefulRecord) o) == 0
        && getClass().getName().equals(o.getClass().getName());
  }

  //TODO: Write tests for equals and hashcode
  @Override
  public int hashCode() {
    return getClass().getName().hashCode() * processed.hashCode();
  }
}
