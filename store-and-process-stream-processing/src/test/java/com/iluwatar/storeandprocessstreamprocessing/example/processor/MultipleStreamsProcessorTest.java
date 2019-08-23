package com.iluwatar.storeandprocessstreamprocessing.example.processor;

import com.iluwatar.storeandprocessstreamprocessing.example.model.AdClickRecord;
import com.iluwatar.storeandprocessstreamprocessing.example.model.PageVisitRecord;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MultipleStreamsProcessorTest {

  private MultipleStreamsProcessorImpl multipleStreamsProcessor;

  @Before
  public void setup() {
    this.multipleStreamsProcessor = new MultipleStreamsProcessorImpl();
  }

  @Test
  public void testWhenRecordIsAddedToBatchThatRecordIsProcessed() throws InterruptedException {
    AdClickRecord unprocessedAdClickRecord = new AdClickRecord(Boolean.FALSE);
    PageVisitRecord unprocessedPageVisitRecord = new PageVisitRecord(Boolean.FALSE);

    multipleStreamsProcessor.onAdClickRecord(unprocessedAdClickRecord);
    multipleStreamsProcessor.onLandingPageVisitRecord(unprocessedPageVisitRecord);

    assertThat(unprocessedAdClickRecord.getProcessed(), is(true));
    assertThat(unprocessedPageVisitRecord.getProcessed(), is(true));
  }
}
