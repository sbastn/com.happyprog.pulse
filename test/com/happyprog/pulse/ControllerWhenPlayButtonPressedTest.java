package com.happyprog.pulse;

import static org.mockito.Mockito.*;

import org.eclipse.ui.IViewPart;
import org.junit.Before;
import org.junit.Test;

public class ControllerWhenPlayButtonPressedTest {

	private IViewPart view;
	private Controller controller;
	private TestSubscriber testSubscriber;

	@Before
	public void before() {
		view = mock(IViewPart.class);
		testSubscriber = mock(TestSubscriber.class);
		controller = new Controller(view, null, testSubscriber);
	}

	@Test
	public void showChart() throws Exception {
		controller.onPlayButtonPressed();

		// verify(view).showChart();
	}

	@Test
	public void subscribeToTestRuns() throws Exception {
		controller.onPlayButtonPressed();

		verify(testSubscriber).subscribe(controller);
	}
}
