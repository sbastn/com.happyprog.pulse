package com.happyprog.pulse.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.chart.Chart;
import com.happyprog.pulse.subscribers.TestSubscriber;
import com.happyprog.pulse.views.IconLoader;
import com.happyprog.pulse.views.SaveDialog;

public class ActionsControllerTest {

	private PulseController controller;
	private TestSubscriber testSubscriber;
	private Chart chart;
	private SaveDialog saveDialog;

	@Before
	public void before() {
		testSubscriber = mock(TestSubscriber.class);
		saveDialog = mock(SaveDialog.class);
		chart = mock(Chart.class);

		controller = new PulseController(chart, null, saveDialog, testSubscriber);
	}

	@Test
	public void onStartAction_showChart() throws Exception {
		controller.onStartAction();

		verify(chart).start();
	}

	@Test
	public void onStartAction_subscribeToTestRuns() throws Exception {
		controller.onStartAction();

		verify(testSubscriber).subscribe(controller);
	}

	@Test
	public void onSaveAction_saveChartSuccessfully() throws Exception {
		when(saveDialog.open()).thenReturn("path-to-file");

		controller.onSaveAction();

		verify(chart).save("path-to-file");
	}

	@Test
	public void onSaveAction_FileDialogIsCanceled() throws Exception {
		when(saveDialog.open()).thenReturn(null);

		controller.onSaveAction();

		verify(chart, never()).save(null);
	}

	@Test
	public void onSaveAction_saveChartFails() throws Exception {
		StubbedController stubbedController = new StubbedController(chart, null, saveDialog, null);

		when(saveDialog.open()).thenReturn("path-to-file");

		doThrow(new IOException()).when(chart).save("path-to-file");

		stubbedController.onSaveAction();

		assertTrue(stubbedController.userWasAlerted);
	}

	class StubbedController extends PulseController {
		boolean userWasAlerted;

		public StubbedController(Chart chart, IconLoader iconLoader, SaveDialog saveDialog, TestSubscriber testSubscriber) {
			super(chart, iconLoader, saveDialog, testSubscriber);
		}

		@Override
		protected void displaySaveError(String file) {
			userWasAlerted = true;
		}
	}
}
