package com.iluwatar.storeandprocessstreamprocessing.example.processor;

import com.iluwatar.storeandprocessstreamprocessing.Record;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SingleStreamProcessorTest {
  
  private SingleStreamProcessorImpl singleStreamProcessor;
  
  @Before
  public void setup() {
    this.singleStreamProcessor = new SingleStreamProcessorImpl();
  }
  
  @Test
  public void testWhenRecordIsAddedToBatchThatRecordIsProcessed() throws InterruptedException {
    Record unprocessedRecord = new Record() {
      @Override
      public boolean process() {
        return true;
      }
    };
    
    this.singleStreamProcessor.onRecord(unprocessedRecord);
  }
  
  @Test
  public void testWhenRecordIsAddedToBatchButThatRecordIsProcessed() throws InterruptedException {
    Record unprocessedRecord = new Record() {
      @Override
      public boolean process() {
        return false;
      }
    };
    
    this.singleStreamProcessor.onRecord(unprocessedRecord);
  }
  
  
}
