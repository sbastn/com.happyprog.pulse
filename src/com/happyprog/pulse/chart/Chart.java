package com.happyprog.pulse.chart;

import org.eclipse.swt.widgets.Composite;

public interface Chart {

	void updateChartWithFailingTests();

	void updateChartWithPassingTests();

	void initialize(Composite parent);

	void start();

}
