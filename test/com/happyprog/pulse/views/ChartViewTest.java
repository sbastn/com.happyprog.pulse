package com.happyprog.pulse.views;

import static org.mockito.Mockito.*;

import org.eclipse.swt.widgets.Composite;
import org.junit.Before;
import org.junit.Test;

public class ChartViewTest {

	private ChartView view;
	private Chart chart;

	@Before
	public void before() {
		chart = mock(Chart.class);
		view = new ChartView(chart);
	}

	@Test
	public void createPartControlInitializesChart() throws Exception {
		Composite parent = mock(Composite.class);
		view.createPartControl(parent);

		verify(chart).initialize(parent);
	}

	@Test
	public void onPassingTestsUpdatesChart() throws Exception {
		view.onPassingTests();

		verify(chart).updateChartWithFailingTests();
	}

	@Test
	public void onFailingTestsUpdatesChart() throws Exception {
		view.onFailingTests();

		verify(chart).updateChartWithFailingTests();
	}
}
