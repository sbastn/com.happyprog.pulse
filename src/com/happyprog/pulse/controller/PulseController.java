package com.happyprog.pulse.controller;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.happyprog.pulse.actions.PlayButton;
import com.happyprog.pulse.actions.PlayButtonObserver;
import com.happyprog.pulse.chart.Chart;
import com.happyprog.pulse.chart.LabelChart;
import com.happyprog.pulse.subscribers.JUnitSubscriber;
import com.happyprog.pulse.subscribers.TestObserver;
import com.happyprog.pulse.subscribers.TestSubscriber;

public class PulseController implements Controller, PlayButtonObserver, TestObserver {

	private final TestSubscriber testSubscriber;
	private final Chart chart;
	private final PlayButton playButton;

	public PulseController() {
		this(new LabelChart(), new PlayButton(), new JUnitSubscriber());
	}

	public PulseController(Chart chart, PlayButton playButton, TestSubscriber testSubscriber) {
		this.chart = chart;
		this.playButton = playButton;
		this.testSubscriber = testSubscriber;
	}

	@Override
	public void onPlayButtonPressed() {
		testSubscriber.subscribe(this);
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
		toolbarManager.add(playButton);
	}
}
