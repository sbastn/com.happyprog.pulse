package com.happyprog.pulse.actions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.happyprog.pulse.views.IconLoader;

public class PlayButtonTest {

	@Test
	public void whenConstructed_showsStartIcon() throws Exception {
		IconLoader iconLoader = mock(IconLoader.class);

		new PlayButton(null, iconLoader);

		verify(iconLoader).load("icons/start.gif");
	}

	@Test
	public void whenPressed_observableIsNotified() throws Exception {
		IconLoader iconLoader = mock(IconLoader.class);
		SpyObservable observer = new SpyObservable();
		PlayButton button = new PlayButton(observer, iconLoader);

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
