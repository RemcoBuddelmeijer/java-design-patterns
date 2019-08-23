package com.iluwatar.storeandprocessstreamprocessing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StreamProcessorTest {

  @Mock
  private Queue<Record> batch;

  private StreamProcessor streamProcessor;

  @Before
  public void contextLoads() {
    MockitoAnnotations.initMocks(this);

    this.streamProcessor = new StreamProcessor(batch) {
      @Override
      protected void processBatch() throws NullPointerException {
        super.processBatch();
      }
    };

    assertThat(this.streamProcessor.batch, is(batch));
  }

  @Test
  public void testIfRecordIsAddedToBatchWhenBatchAllowsRecords() {
    Record record = new Record() {
      @Override
      public boolean process() {
        return true;
      }
    };

    doReturn(true).when(streamProcessor.batch).offer(eq(record));

    assertThat(streamProcessor.addRecordToBatch(record), is(true));

    verify(streamProcessor.batch).offer(eq(record));
  }

  @Test
  public void testIfRecordIsNotAddedToBatchWhenBatchDoesNotAllowsRecords()
      throws InterruptedException {
    Record record = new Record() {
      @Override
      public boolean process() {
        return true;
      }
    };

    doReturn(false).when(streamProcessor.batch).offer(eq(record));

    assertThat(streamProcessor.addRecordToBatch(record), is(false));

    verify(streamProcessor.batch).offer(eq(record));
  }

  @Test(expected = NullPointerException.class)
  public void testIfRecordIsNotAddedToBatchWhenBatchThrowsNullPointerException() {
    doThrow(NullPointerException.class).when(streamProcessor.batch).offer(any());

    assertThat(streamProcessor.addRecordToBatch(null), is(false));

    verify(streamProcessor.batch).offer(eq(null));
  }

  @Test
  public void testIfMultipleRecordsAreAddedToBatchWhenBatchAllowsRecords() {
    Record[] records = new Record[]{
        new Record() {
          @Override
          public boolean process() {
            return false;
          }
        },
        new Record() {
          @Override
          public boolean process() {
            return false;
          }
        }
    };

    doReturn(true).when(streamProcessor.batch).offer(any(Record.class));

    assertThat(streamProcessor.addRecordsToBatch(records), is(true));

    verify(streamProcessor.batch, times(2)).offer(any(Record.class));
  }

  @Test
  public void testIfMultipleRecordsAreNotAddedToBatchWhenBatchDoesNotAllowRecords()
      throws InterruptedException {
    Record[] records = new Record[]{
        new Record() {
          @Override
          public boolean process() {
            return false;
          }
        },
        new Record() {
          @Override
          public boolean process() {
            return false;
          }
        }
    };

    doReturn(false).when(streamProcessor.batch).offer(any(Record.class));

    assertThat(streamProcessor.addRecordsToBatch(records), is(false));

    verify(streamProcessor.batch, times(1)).offer(any(Record.class));
  }

  @Test(expected = NullPointerException.class)
  public void testIfMultipleRecordsAreNotAddedToBatchWhenBatchThrowsNullPointerExceptionAtFirstTry() {
    Record[] records = new Record[]{
        null,
        new Record() {
          @Override
          public boolean process() {
            return false;
          }
        }
    };

    doAnswer((invocationOnMock) -> {
      if (invocationOnMock.getArguments()[0] == records[0]) {
        throw new NullPointerException();
      }
      return true;
    }).when(this.streamProcessor.batch).offer(any());

    assertThat(streamProcessor.addRecordsToBatch(records), is(false));

    verify(streamProcessor.batch, times(1)).offer(any());
  }

  @Test(expected = NullPointerException.class)
  public void testIfMultipleRecordsAreNotAddedToBatchWhenBatchThrowsNullPointerExceptionAtSecondTry() {
    Record[] records = new Record[]{
        new Record() {
          @Override
          public boolean process() {
            return false;
          }
        },
        null
    };

    doAnswer((invocationOnMock) -> {
      if (invocationOnMock.getArguments()[0] == records[0]) {
        return true;
      }
      throw new NullPointerException();
    }).when(this.streamProcessor.batch).offer(any());

    assertThat(streamProcessor.addRecordsToBatch(records), is(false));

    verify(streamProcessor.batch, times(2)).offer(any());
  }

  @Test
  public void testIfBatchGetsProcessedWhenSingleRecordIsScheduled() {
    Record record = mock(Record.class);
    doReturn(true).when(record).process();

    doReturn(true).when(streamProcessor.batch).offer(any(Record.class));
    when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.FALSE);

    doAnswer(invocationOnMock -> {
      when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.TRUE);
      return record;
    }).when(streamProcessor.batch).poll();

    streamProcessor.addRecordToBatch(record);
    streamProcessor.processBatch();

    verify(record).process();
  }

  @Test
  public void testIfBatchGetsProcessedWhenMultipleRecordsAreScheduled()
      throws InterruptedException {
    Record record = mock(Record.class);
    doReturn(true).when(record).process();

    Record recordTwo = mock(Record.class);
    doReturn(false).when(recordTwo).process();

    doReturn(true).when(streamProcessor.batch).offer(any(Record.class));
    when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.FALSE);

    final AtomicBoolean invoked = new AtomicBoolean(false);
    doAnswer(invocationOnMock -> {
      if (!invoked.getAndSet(true)) {
        when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.FALSE);
        return record;
      }

      when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.TRUE);
      return recordTwo;
    }).when(streamProcessor.batch).poll();

    streamProcessor.addRecordsToBatch(record, recordTwo);
    streamProcessor.processBatch();

    verify(record).process();
    verify(recordTwo).process();
  }

  @Test(expected = NullPointerException.class)
  public void testIfBatchDoesNotGetProcessedWhenSingleRecordIsNull() {
    doReturn(true).when(streamProcessor.batch).offer(any(Record.class));
    when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.FALSE);
    doAnswer(invocationOnMock -> {
      when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.TRUE);
      return null;
    }).when(streamProcessor.batch).poll();

    streamProcessor.addRecordToBatch(null);
    streamProcessor.processBatch();
  }

  @Test
  public void testIfBatchDoesNotGetProcessedWhenRecordIsProcessed() {
    Record record = new Record() {
      @Override
      public boolean process() {
        return false;
      }
    };

    doReturn(true).when(streamProcessor.batch).offer(eq(record));
    when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.FALSE);
    doAnswer(invocationOnMock -> {
      when(streamProcessor.batch.isEmpty()).thenReturn(Boolean.TRUE);
      return record;
    }).when(streamProcessor.batch).poll();

    streamProcessor.addRecordToBatch(record);
    streamProcessor.addRecordToBatch(record);
    streamProcessor.processBatch();

    verify(streamProcessor.batch, times(2)).offer(any(Record.class));
    verify(streamProcessor.batch, times(1)).poll();
  }

}
