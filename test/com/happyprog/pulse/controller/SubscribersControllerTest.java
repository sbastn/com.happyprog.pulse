package com.happyprog.pulse.controller;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.chart.Chart;

public class SubscribersControllerTest {

	private PulseController controller;
	private Chart chart;

	@Before
	public void before() {
		chart = mock(Chart.class);
		controller = new PulseController(chart, null, null, null, null);
	}

	@Test
	public void whenTestsArePassing_notifiesChart() throws Exception {
		controller.onPassingTests();

		verify(chart).updateChartWithPassingTests();
	}

	@Test
	public void whenTestsAreFailing_notifiesView() throws Exception {
		controller.onFailingTests();

		verify(chart).updateChartWithFailingTests();
	}

	@Test
	public void whenCodeIsRefactored_notifiesView() throws Exception {
		controller.onCodeRefactor();

		verify(chart).updateChartWithRefactoredCode();
	}
}
