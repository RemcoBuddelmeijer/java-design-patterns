package com.iluwatar.storeandprocessstreamprocessing.example.processor;

import com.iluwatar.storeandprocessstreamprocessing.example.model.AdClickRecord;
import com.iluwatar.storeandprocessstreamprocessing.example.model.PageVisitRecord;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

public class MultipleStreamsProcessorTest {

  private MultipleStreamsProcessorImpl multipleStreamsProcessor;

  @Before
  public void setup() {
    this.multipleStreamsProcessor = new MultipleStreamsProcessorImpl();
  }

  @Test
  public void testWhenAdClickRecordIsAddedToBatchThatRecordIsProcessed() throws InterruptedException {
    AdClickRecord unprocessedAdClickRecord = Mockito.spy(new AdClickRecord(Boolean.FALSE));

    multipleStreamsProcessor.onAdClickRecord(unprocessedAdClickRecord);

    verify(unprocessedAdClickRecord).process();

    assertThat(unprocessedAdClickRecord.process(), is(true));
    assertThat(unprocessedAdClickRecord.getProcessed(), is(true));
  }

  @Test
  public void testWhenPageVisitClickRecordIsAddedToBatchThatRecordIsProcessed() throws InterruptedException {
    PageVisitRecord unprocessedPageVisitRecord = Mockito.spy(new PageVisitRecord(Boolean.FALSE));

    multipleStreamsProcessor.onLandingPageVisitRecord(unprocessedPageVisitRecord);

    verify(unprocessedPageVisitRecord).process();

    assertThat(unprocessedPageVisitRecord.process(), is(true));
    assertThat(unprocessedPageVisitRecord.getProcessed(), is(true));
  }
}
