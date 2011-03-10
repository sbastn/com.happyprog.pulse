package com.happyprog.pulse.actions;

import org.eclipse.jface.action.Action;

import com.happyprog.pulse.views.IconLoader;

public class PlayButton extends Action {

	private PlayButtonObserver observer;
	private final IconLoader iconLoader;

	public PlayButton(PlayButtonObserver observer, IconLoader iconLoader) {
		this.observer = observer;
		this.iconLoader = iconLoader;
		showIcon();
	}

	@Override
	public void run() {
		super.run();
		observer.onPlayButtonPressed();
	}

	void showIcon() {
		setImageDescriptor(iconLoader.load("icons/start.gif"));
	}

}
