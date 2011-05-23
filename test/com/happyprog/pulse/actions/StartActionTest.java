package com.happyprog.pulse.actions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.views.IconLoader;

public class StartActionTest {

	private IconLoader iconLoader;

	@Before
	public void before() {
		iconLoader = mock(IconLoader.class);
	}

	@Test
	public void whenConstructed_showsStartIcon() throws Exception {
		new StartAction(null, iconLoader);

		verify(iconLoader).load("icons/start.gif");
	}

	@Test
	public void whenPressed_observableIsNotified() throws Exception {
		SpyObservable observer = new SpyObservable();
		StartAction button = new StartAction(observer, iconLoader);

		button.run();

		assertTrue(observer.buttonWasPressed);
	}

	@Test
	public void actionTextIsSet() throws Exception {
		// mousefeed crashes trying to find the action's text if it is not set.
		StartAction button = new StartAction(null, iconLoader);
		assertEquals("Start Pulse", button.getText());
	}

	class SpyObservable implements StartActionObserver {
		boolean buttonWasPressed;

		@Override
		public void onStartAction() {
			buttonWasPressed = true;
		}
	}
}
