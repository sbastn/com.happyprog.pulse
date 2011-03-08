package com.happyprog.pulse.views;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.junit.Test;

public class LabelChartTest {

	private Label internalLabel;

	@Test
	public void onInitializeShowLabelInitialize() throws Exception {
		LabelChart chart = new LabelChartStub();
		Composite parent = mock(Composite.class);

		internalLabel = mock(Label.class);
		when(internalLabel.getText()).thenReturn("initialized");

		chart.initialize(parent);

		assertEquals("initialized", chart.getText());
	}

	class LabelChartStub extends LabelChart {
		@Override
		Object createLabel(Composite parent) {
			return internalLabel;
		}
	}
}
