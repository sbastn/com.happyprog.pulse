package com.happyprog.pulse;

import com.happyprog.pulse.views.PulseView;

public class Controller implements PlayButtonObserver, TestObserver {

	private final PulseView view;
	private final TestSubscriber testSubscriber;

	public Controller(PulseView view, TestSubscriber testSubscriber) {
		this.view = view;
		this.testSubscriber = testSubscriber;
	}

	@Override
	public void onPlayButtonPressed() {
		view.showChart();
		testSubscriber.subscribe(this);
	}

	@Override
	public void onPassingTests() {
		view.onPassingTests();
	}

	@Override
	public void onFailingTests() {
		view.onFailingTests();
	}

}
