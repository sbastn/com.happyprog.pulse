package com.happyprog.pulse.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.happyprog.pulse.controller.Controller;
import com.happyprog.pulse.controller.PulseController;

public class ChartView extends ViewPart {

	public static final String ID = "com.happyprog.pulse.views.ChartView";
	private final Controller controller;

	public ChartView() {
		this(new PulseController());
	}

	public ChartView(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void createPartControl(Composite parent) {
		controller.initializeView(this, parent);
	}

	@Override
	public void setFocus() {
		// Required to be here, but we have nothing to focus on...
	}

}