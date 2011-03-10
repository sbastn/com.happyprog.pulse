package com.happyprog.pulse.controller;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.happyprog.pulse.actions.PlayButton;
import com.happyprog.pulse.actions.PlayButtonObserver;
import com.happyprog.pulse.actions.SaveAction;
import com.happyprog.pulse.actions.SaveActionObserver;
import com.happyprog.pulse.chart.Chart;
import com.happyprog.pulse.chart.TimeChart;
import com.happyprog.pulse.subscribers.JUnitSubscriber;
import com.happyprog.pulse.subscribers.TestObserver;
import com.happyprog.pulse.subscribers.TestSubscriber;
import com.happyprog.pulse.views.IconLoader;
import com.happyprog.pulse.views.SimpleIconLoader;

public class PulseController implements Controller, PlayButtonObserver, SaveActionObserver, TestObserver {

	private final TestSubscriber testSubscriber;
	private final Chart chart;
	private final IconLoader iconLoader;

	public PulseController() {
		this(new TimeChart(), new SimpleIconLoader(), new JUnitSubscriber());
	}

	public PulseController(Chart chart, IconLoader iconLoader, TestSubscriber testSubscriber) {
		this.chart = chart;
		this.iconLoader = iconLoader;
		this.testSubscriber = testSubscriber;
	}

	@Override
	public void onPlayButtonPressed() {
		testSubscriber.subscribe(this);
		chart.start();
	}

	@Override
	public void onSaveAction() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPassingTests() {
		chart.updateChartWithPassingTests();
	}

	@Override
	public void onFailingTests() {
		chart.updateChartWithFailingTests();
	}

	@Override
	public void initializeView(ViewPart viewPart, Composite parent) {
		chart.initialize(parent);
		IToolBarManager toolbarManager = viewPart.getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(new PlayButton(this, iconLoader));
		toolbarManager.add(new SaveAction(this, iconLoader));
	}

}
