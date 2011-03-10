package com.happyprog.pulse.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.experimental.chart.swt.ChartComposite;

public class TimeChart implements Chart {

	private TimeSeries serie;
	private TimeSeries passingTestsTimeSerie;
	private TimeSeries failingTestsTimeSerie;
	private TimeSeries refactorTimeSerie;

	@Override
	public void updateChartWithFailingTests() {
		makeWave(failingTestsTimeSerie, -5);
	}

	@Override
	public void updateChartWithPassingTests() {
		makeWave(passingTestsTimeSerie, 5);
	}

	private void makeWave(final TimeSeries shapeSerie, final int dimension) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				Second second = new Second();
				serie.add(second.previous(), 0);
				serie.add(second, dimension);
				shapeSerie.add(second, dimension);
				serie.add(second.next(), 0);
			}
		});
	}

	@Override
	public void start() {
		serie.add(new Second(), 0);
	}

	@Override
	public void initialize(Composite parent) {
		serie = new TimeSeries("timeline");
		passingTestsTimeSerie = new TimeSeries("green tests");
		failingTestsTimeSerie = new TimeSeries("red tests");
		refactorTimeSerie = new TimeSeries("refactor");

		int thirtySeconds = 30000;
		serie.setMaximumItemAge(thirtySeconds);
		passingTestsTimeSerie.setMaximumItemAge(thirtySeconds);
		failingTestsTimeSerie.setMaximumItemAge(thirtySeconds);
		refactorTimeSerie.setMaximumItemAge(thirtySeconds);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(serie);
		dataset.addSeries(passingTestsTimeSerie);
		dataset.addSeries(failingTestsTimeSerie);
		dataset.addSeries(refactorTimeSerie);

		DateAxis domain = new DateAxis("Time");
		domain.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
		domain.setAutoRange(true);
		domain.setTickLabelsVisible(true);

		NumberAxis range = new NumberAxis("Points");
		range.setRange(new Range(-10, 10));
		range.setVisible(false);
		range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		configureMainLine(renderer);
		configureGreenTest(renderer);
		configureRedTest(renderer);
		configureRefactorTimeSerie(renderer);

		XYPlot plot = new XYPlot(dataset, domain, range, renderer);
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		plot.setRangeGridlinePaint(Color.white);
		plot.setDomainGridlinePaint(Color.white);

		JFreeChart chart = new JFreeChart(null, plot);
		chart.removeLegend();
		ChartComposite chartComposite = new ChartComposite(parent, SWT.NONE, chart, false, false, false, false, false);
		chartComposite.getChartRenderingInfo().setEntityCollection(null);
	}

	private void configureRefactorTimeSerie(XYLineAndShapeRenderer renderer) {
		renderer.setSeriesPaint(3, new Color(0, 159, 227));
		renderer.setSeriesShape(3, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
		renderer.setSeriesLinesVisible(3, false);
	}

	private void configureRedTest(XYLineAndShapeRenderer renderer) {
		renderer.setSeriesPaint(2, Color.red);
		renderer.setSeriesShape(2, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
		renderer.setSeriesLinesVisible(2, false);
	}

	private void configureGreenTest(XYLineAndShapeRenderer renderer) {
		renderer.setSeriesShape(1, new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
		renderer.setSeriesPaint(1, new Color(89, 182, 106));
		renderer.setSeriesLinesVisible(1, false);
	}

	private void configureMainLine(XYLineAndShapeRenderer renderer) {
		renderer.setSeriesPaint(0, Color.lightGray);
		renderer.setSeriesStroke(0, new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		renderer.setSeriesShapesVisible(0, false);
	}

}
