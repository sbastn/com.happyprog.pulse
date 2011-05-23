package com.happyprog.pulse.actions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.views.IconLoader;

public class SaveActionTest {

	private IconLoader iconLoader;

	@Before
	public void before() {
		iconLoader = mock(IconLoader.class);
	}

	@Test
	public void whenConstructed_showsSaveIcon() throws Exception {
		new SaveAction(null, iconLoader);

		verify(iconLoader).load("icons/save.gif");
	}

	@Test
	public void whenPressed_observableIsNotified() throws Exception {
		SpyObservable observer = new SpyObservable();
		SaveAction button = new SaveAction(observer, iconLoader);

		button.run();

		assertTrue(observer.buttonWasPressed);
	}

	@Test
	public void actionTextIsSet() throws Exception {
		// Mousefeed crashes on actions with no text
		SaveAction button = new SaveAction(null, iconLoader);
		assertEquals("Stop Pulse", button.getText());
	}

	class SpyObservable implements SaveActionObserver {
		boolean buttonWasPressed;

		@Override
		public void onSaveAction() {
			buttonWasPressed = true;
		}
	}
}
