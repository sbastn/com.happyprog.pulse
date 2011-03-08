package com.happyprog.pulse.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ChartView extends ViewPart implements PulseView {

	public static final String ID = "com.happyprog.pulse.views.ChartView";
	private final Chart chart;

	public ChartView(Chart chart) {
		this.chart = chart;
	}

	@Override
	public void createPartControl(Composite parent) {
		chart.initialize(parent);
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void showChart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPassingTests() {
		chart.updateChartWithFailingTests();
	}

	@Override
	public void onFailingTests() {
		chart.updateChartWithFailingTests();
	}
}