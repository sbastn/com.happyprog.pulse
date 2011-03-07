package com.happyprog.pulse;

import static org.junit.Assert.*;

import org.junit.Test;

import com.happyprog.pulse.views.PlayButton;

public class PlayButtonTest {

	@Test
	public void whenPressed_observableIsNotified() throws Exception {
		PlayButton button = new PlayButton();
		SpyObservable observer = new SpyObservable();
		button.subscribe(observer);

		button.run();

		assertTrue(observer.wasCalled());
	}

	class SpyObservable implements PlayButtonObserver {
		private boolean buttonWasPressed;

		boolean wasCalled() {
			return buttonWasPressed;
		}

		@Override
		public void onPlayButtonPressed() {
			buttonWasPressed = true;
		}
	}
}
