package com.happyprog.pulse.actions;

import org.eclipse.jface.action.Action;

import com.happyprog.pulse.views.IconLoader;

public class StarAction extends Action {

	private StartActionObserver observer;
	private final IconLoader iconLoader;

	public StarAction(StartActionObserver observer, IconLoader iconLoader) {
		this.observer = observer;
		this.iconLoader = iconLoader;
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
