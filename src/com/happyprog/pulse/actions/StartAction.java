package com.happyprog.pulse.actions;

import org.eclipse.jface.action.Action;

import com.happyprog.pulse.views.IconLoader;

public class StartAction extends Action {

	private StartActionObserver observer;
	private final IconLoader iconLoader;

	public StartAction(StartActionObserver observer, IconLoader iconLoader) {
		this.observer = observer;
		this.iconLoader = iconLoader;
		this.setText("Start Pulse");
		showIcon();
	}

	@Override
	public void run() {
		super.run();
		observer.onStartAction();
	}

	void showIcon() {
		setImageDescriptor(iconLoader.load("icons/start.gif"));
	}

}
