package com.happyprog.pulse.actions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.happyprog.pulse.views.IconLoader;

public class SaveActionTest {
	@Test
	public void whenConstructed_showsSaveIcon() throws Exception {
		IconLoader iconLoader = mock(IconLoader.class);

		new SaveAction(null, iconLoader);

		verify(iconLoader).load("icons/save.gif");
	}

	@Test
	public void whenPressed_observableIsNotified() throws Exception {
		IconLoader iconLoader = mock(IconLoader.class);
		SpyObservable observer = new SpyObservable();
		SaveAction button = new SaveAction(observer, iconLoader);

		button.run();

		assertTrue(observer.buttonWasPressed);
	}

	class SpyObservable implements SaveActionObserver {
		boolean buttonWasPressed;

		@Override
		public void onSaveAction() {
			buttonWasPressed = true;
		}
	}
}
