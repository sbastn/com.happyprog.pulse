package com.happyprog.pulse;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.views.PulseView;

public class ControllerWhenPlayButtonPressedTest {

	private PulseView view;
	private Controller controller;
	private TestSubscriber testSubscriber;

	@Before
	public void before() {
		view = mock(PulseView.class);
		testSubscriber = mock(TestSubscriber.class);
		controller = new Controller(view, testSubscriber);
	}

	@Test
	public void showChart() throws Exception {
		controller.onPlayButtonPressed();

		verify(view).showChart();
	}

	@Test
	public void subscribeToTestRuns() throws Exception {
		controller.onPlayButtonPressed();

		verify(testSubscriber).subscribe();
	}
}
