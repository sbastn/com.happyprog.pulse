package com.happyprog.pulse.controller;

import java.io.IOException;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.happyprog.pulse.actions.SaveAction;
import com.happyprog.pulse.actions.SaveActionObserver;
import com.happyprog.pulse.actions.StartAction;
import com.happyprog.pulse.actions.StartActionObserver;
import com.happyprog.pulse.chart.Chart;
import com.happyprog.pulse.chart.TimeChart;
import com.happyprog.pulse.subscribers.JUnitSubscriber;
import com.happyprog.pulse.subscribers.RefactorObserver;
import com.happyprog.pulse.subscribers.RefactorSubscriber;
import com.happyprog.pulse.subscribers.SimpleRefactorSubscriber;
import com.happyprog.pulse.subscribers.TestObserver;
import com.happyprog.pulse.subscribers.TestSubscriber;
import com.happyprog.pulse.views.IconLoader;
import com.happyprog.pulse.views.SaveDialog;
import com.happyprog.pulse.views.SimpleIconLoader;
import com.happyprog.pulse.views.SimpleSaveDialog;

public class PulseController implements Controller, StartActionObserver, SaveActionObserver, TestObserver,
		RefactorObserver {

	private final TestSubscriber testSubscriber;
	private final Chart chart;
	private final IconLoader iconLoader;
	private final SaveDialog saveDialog;
	private final RefactorSubscriber refactorSubscriber;
	private StackLayout stackLayout;

	public PulseController() {
		this(new TimeChart(), new SimpleIconLoader(), new SimpleSaveDialog(), new JUnitSubscriber(),
				new SimpleRefactorSubscriber());
	}

	public PulseController(Chart chart, IconLoader iconLoader, SaveDialog saveDialog, TestSubscriber testSubscriber,
			RefactorSubscriber refactorSubscriber) {
		this.chart = chart;
		this.iconLoader = iconLoader;
		this.saveDialog = saveDialog;
		this.testSubscriber = testSubscriber;
		this.refactorSubscriber = refactorSubscriber;
	}

	@Override
	public void onStartAction() {
		testSubscriber.subscribe(this);
		refactorSubscriber.subscribe(this);
		chart.start();
	}

	@Override
	public void onSaveAction() {
		String file = saveDialog.open();
		if (file != null) {
			saveChart(file);
		}
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
	public void onCodeRefactor() {
		chart.updateChartWithRefactoredCode();
	}

	@Override
	public void initializeView(ViewPart viewPart, Composite parent) {
		chart.initialize(parent);
		IToolBarManager toolbarManager = viewPart.getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(new StartAction(this, iconLoader));
		toolbarManager.add(new SaveAction(this, iconLoader));
	}

	private void saveChart(String file) {
		try {
			chart.save(file);
		} catch (IOException e) {
			displaySaveError(file);
		}
	}

	protected void displaySaveError(String file) {
		MessageDialog
				.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						"Pulse could not save your session!", String.format(
								"Could not save %s. Is the location correct? Do you have rights to save in that location?", file));
	}

}
