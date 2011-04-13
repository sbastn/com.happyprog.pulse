package com.happyprog.pulse.actions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.happyprog.pulse.views.IconLoader;

public class StartActionTest {

	@Test
	public void whenConstructed_showsStartIcon() throws Exception {
		IconLoader iconLoader = mock(IconLoader.class);

		new StartAction(null, iconLoader);

		verify(iconLoader).load("icons/start.gif");
	}

	@Test
	public void whenPressed_observableIsNotified() throws Exception {
		IconLoader iconLoader = mock(IconLoader.class);
		SpyObservable observer = new SpyObservable();
		StartAction button = new StartAction(observer, iconLoader);

		button.run();

		assertTrue(observer.buttonWasPressed);
	}

	class SpyObservable implements StartActionObserver {
		boolean buttonWasPressed;

		@Override
		public void onStartAction() {
			buttonWasPressed = true;
		}
	}
}
