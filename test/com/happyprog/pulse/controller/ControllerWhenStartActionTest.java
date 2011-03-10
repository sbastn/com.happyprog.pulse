package com.happyprog.pulse.controller;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.chart.Chart;
import com.happyprog.pulse.subscribers.TestSubscriber;

public class ControllerWhenStartActionTest {

	private PulseController controller;
	private TestSubscriber testSubscriber;
	private Chart chart;

	@Before
	public void before() {
		testSubscriber = mock(TestSubscriber.class);
		chart = mock(Chart.class);

		controller = new PulseController(chart, null, testSubscriber);
	}

	@Test
	public void showChart() throws Exception {
		controller.onStartAction();

		verify(chart).start();
	}

	@Test
	public void subscribeToTestRuns() throws Exception {
		controller.onStartAction();

		verify(testSubscriber).subscribe(controller);
	}
}
