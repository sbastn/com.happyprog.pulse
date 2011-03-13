package com.happyprog.pulse.chart;

import java.io.IOException;

import org.eclipse.swt.widgets.Composite;

public interface Chart {

	void updateChartWithFailingTests();

	void updateChartWithPassingTests();

	void updateChartWithRefactoredCode();

	void initialize(Composite parent);

	void start();

	void save(String file) throws IOException;

}
