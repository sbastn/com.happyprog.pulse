package com.happyprog.pulse;

import static org.mockito.Mockito.*;

import org.eclipse.ui.IViewPart;
import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.views.Chart;

public class ControllerWhenTestRunsTest {

	private IViewPart view;
	private Controller controller;
	private Chart chart;

	@Before
	public void before() {
		view = mock(IViewPart.class);
		chart = mock(Chart.class);
		controller = new Controller(view, chart, null);
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
}
