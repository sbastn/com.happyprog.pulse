package com.happyprog.pulse.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

public class LabelChart implements Chart {

	private Label label;

	@Override
	public void initialize(Composite parent) {
		label = (Label) createLabel(parent);
		label.setText("initialized");
	}

	Object createLabel(Composite parent) {
		return new Label(parent, SWT.NONE);
	}

	public String getText() {
		return label.getText();
	}

	@Override
	public void updateChartWithPassingTests() {
		updateLabelText("test passed");
	}

	@Override
	public void updateChartWithFailingTests() {
		updateLabelText("test failed");
	}

	void updateLabelText(final String text) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				label.setText(text);
			}

		});
	}
}
