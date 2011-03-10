package com.happyprog.pulse.controller;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.controller.PulseController;
import com.happyprog.pulse.subscribers.TestSubscriber;

public class ControllerWhenPlayButtonPressedTest {

	private PulseController controller;
	private TestSubscriber testSubscriber;

	@Before
	public void before() {
		testSubscriber = mock(TestSubscriber.class);
		controller = new PulseController(null, testSubscriber);
	}

	@Test
	public void showChart() throws Exception {
		controller.onPlayButtonPressed();
		// TODO: chart needs to appear when play button is pressed
		// verify(view).showChart();
	}

	@Test
	public void subscribeToTestRuns() throws Exception {
		controller.onPlayButtonPressed();

		verify(testSubscriber).subscribe(controller);
	}
}
