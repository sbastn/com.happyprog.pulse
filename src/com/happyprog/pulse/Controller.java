package com.happyprog.pulse;

import org.eclipse.ui.IViewPart;

import com.happyprog.pulse.views.Chart;

public class Controller implements PlayButtonObserver, TestObserver {

	private final IViewPart view;
	private final TestSubscriber testSubscriber;
	private final Chart chart;

	public Controller(IViewPart view, Chart chart, TestSubscriber testSubscriber) {
		this.view = view;
		this.chart = chart;
		this.testSubscriber = testSubscriber;
	}

	@Override
	public void onPlayButtonPressed() {
		// chart.showChart();
		testSubscriber.subscribe(this);
	}

	@Override
	public void onPassingTests() {
		chart.updateChartWithPassingTests();
	}

	@Override
	public void onFailingTests() {
		chart.updateChartWithFailingTests();
	}

}
