package com.iluwatar.storeandprocessstreamprocessing.example.processor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

import com.iluwatar.storeandprocessstreamprocessing.Record;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SingleStreamProcessorTest {

  private SingleStreamProcessorImpl singleStreamProcessor;
  
  @Before
  public void setup() {
    this.singleStreamProcessor = new SingleStreamProcessorImpl();
  }

  //TODO: Fix names

  @Test
  public void testWhenRecordIsAddedToBatchThatRecordIsProcessed() {
    Record unprocessedRecord = Mockito.spy(new Record() {
      @Override
      public boolean process() {
        return true;
      }
    });
    
    this.singleStreamProcessor.onRecord(unprocessedRecord);
    assertThat(verify(unprocessedRecord).process(), is(true));
  }
  
  @Test
  public void testWhenRecordIsAddedToBatchButThatRecordIsProcessed() {
    Record unprocessedRecord = Mockito.spy(new Record() {
      @Override
      public boolean process() {
        return false;
      }
    });
    
    this.singleStreamProcessor.onRecord(unprocessedRecord);
    assertThat(verify(unprocessedRecord).process(), is(false));
  }
}
