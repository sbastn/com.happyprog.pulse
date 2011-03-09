package com.happyprog.pulse.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ChartView extends ViewPart {

	public static final String ID = "com.happyprog.pulse.views.ChartView";
	private final Chart chart;

	public ChartView() {
		this(new LabelChart());
	}

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

}