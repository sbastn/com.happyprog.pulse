package com.happyprog.pulse.views;

import static org.mockito.Mockito.*;

import org.eclipse.swt.widgets.Composite;
import org.junit.Test;

import com.happyprog.pulse.controller.Controller;

public class ChartViewTest {
	@Test
	public void createPartControlInitializesView() throws Exception {
		Controller controller = mock(Controller.class);
		Composite parentComposite = mock(Composite.class);

		ChartView view = new ChartView(controller);
		view.createPartControl(parentComposite);

		verify(controller).initializeView(view, parentComposite);
	}
}
