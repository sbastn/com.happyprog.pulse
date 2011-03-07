package com.happyprog.pulse;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.views.PulseView;

public class ControllerWhenTestRunsTest {

	private PulseView view;
	private Controller controller;

	@Before
	public void before() {
		view = mock(PulseView.class);
		controller = new Controller(view, null);
	}

	@Test
	public void whenTestsArePassing_notifiesView() throws Exception {
		controller.onPassingTests();

		verify(view).updateChartForPassingTests();
	}

	@Test
	public void whenTestsAreFailing_notifiesView() throws Exception {
		controller.onFailingTests();

		verify(view).updateChartForFailingTests();
	}
}
