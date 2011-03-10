package com.happyprog.pulse.actions;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayButtonTest {

	@Test
	public void whenPressed_observableIsNotified() throws Exception {
		SpyObservable observer = new SpyObservable();
		PlayButton button = new PlayButton(observer);

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
