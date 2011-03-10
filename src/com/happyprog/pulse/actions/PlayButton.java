package com.happyprog.pulse.actions;

import org.eclipse.jface.action.Action;

public class PlayButton extends Action {

	private PlayButtonObserver observer;

	public PlayButton(PlayButtonObserver observer) {
		this.observer = observer;
	}

	@Override
	public void run() {
		super.run();
		observer.onPlayButtonPressed();
	}

}
