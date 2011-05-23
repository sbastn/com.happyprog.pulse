package com.happyprog.pulse.actions;

import org.eclipse.jface.action.Action;

import com.happyprog.pulse.views.IconLoader;

public class SaveAction extends Action {

	private SaveActionObserver observer;
	private IconLoader iconLoader;

	public SaveAction(SaveActionObserver observer, IconLoader iconLoader) {
		this.observer = observer;
		this.iconLoader = iconLoader;
		this.setText("Stop Pulse");
		showIcon();
	}

	@Override
	public void run() {
		super.run();
		observer.onSaveAction();
	}

	void showIcon() {
		setImageDescriptor(iconLoader.load("icons/save.gif"));
	}

}
