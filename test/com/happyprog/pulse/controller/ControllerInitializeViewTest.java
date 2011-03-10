package com.happyprog.pulse.controller;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.part.ViewPart;
import org.junit.Before;
import org.junit.Test;

import com.happyprog.pulse.actions.PlayButton;
import com.happyprog.pulse.chart.Chart;
import com.happyprog.pulse.subscribers.TestSubscriber;

public class ControllerInitializeViewTest {

	private Chart chart;
	private TestSubscriber testSubscriber;
	private ViewPart viewPart;
	private IViewSite viewSite;
	private IActionBars actionBars;
	private IToolBarManager toolBarManager;
	private Composite parentComposite;
	private PulseController controller;

	@Before
	public void before() {
		chart = mock(Chart.class);
		testSubscriber = mock(TestSubscriber.class);
		viewPart = mock(ViewPart.class);
		viewSite = mock(IViewSite.class);
		actionBars = mock(IActionBars.class);
		toolBarManager = mock(IToolBarManager.class);
		parentComposite = mock(Composite.class);

		controller = new PulseController(chart, testSubscriber);

		when(viewPart.getViewSite()).thenReturn(viewSite);
		when(viewSite.getActionBars()).thenReturn(actionBars);
		when(actionBars.getToolBarManager()).thenReturn(toolBarManager);
	}

	@Test
	public void addsPlayActionToTheToolbar() throws Exception {
		controller.initializeView(viewPart, parentComposite);

		verify(toolBarManager).add(isA(PlayButton.class));
	}

	@Test
	public void initializeTheChart() throws Exception {
		controller.initializeView(viewPart, parentComposite);

		verify(chart).initialize(parentComposite);
	}
}
