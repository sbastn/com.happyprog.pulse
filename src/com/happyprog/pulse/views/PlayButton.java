package com.happyprog.pulse.views;

import org.eclipse.jface.action.Action;

import com.happyprog.pulse.PlayButtonObserver;

public class PlayButton extends Action {

	private PlayButtonObserver observer;

	public void subscribe(PlayButtonObserver observer) {
		this.observer = observer;
	}

	@Override
	public void run() {
		super.run();
		observer.onPlayButtonPressed();
	}

}
