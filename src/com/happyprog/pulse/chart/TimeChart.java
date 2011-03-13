package com.happyprog.pulse.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.jfree.chart.ChartUtilities;
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

	private static final int MAX_RANGE_POINT = 10;
	private static final int MIN_RANGE_POINT = -10;

	private static final int FLAT_POINT = 0;
	private static final int TESTS_PASS_POINT = 5;
	private static final int TESTS_FAIL_POINT = -5;
	private static final int REFACTOR_POINT = 3;

	private static final int PNG_HEIGHT = 400;
	private static final int PNG_WIDTH = 800;

	private static final int MAIN_TIMELINE = 0;
	private static final int GREEN_TEST_TIMELINE = 1;
	private static final int REFACTOR_TEST_TIMELINE = 3;
	private static final int RED_TEST_TIMELINE = 2;

	private static final Color GRAY_COLOR = Color.lightGray;
	private static final BasicStroke STROKE = new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
	private static final Color RED_COLOR = Color.red;
	private static final Color GREEN_COLOR = new Color(89, 182, 106);
	private static final Double CIRCLE_SHAPE = new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0);
	private static final Color BLUE_COLOR = new Color(0, 159, 227);

	private TimeSeries serie;
	private TimeSeries passingTestsTimeSerie;
	private TimeSeries failingTestsTimeSerie;
	private TimeSeries refactorTimeSerie;
	private JFreeChart chart;

	@Override
	public void updateChartWithFailingTests() {
		drawPoint(failingTestsTimeSerie, TESTS_FAIL_POINT);
	}

	@Override
	public void updateChartWithPassingTests() {
		drawPoint(passingTestsTimeSerie, TESTS_PASS_POINT);
	}

	@Override
	public void updateChartWithRefactoredCode() {
		drawPoint(refactorTimeSerie, REFACTOR_POINT);
	}

	@Override
	public void save(String file) throws IOException {
		ChartUtilities.saveChartAsPNG(new File(file), chart, PNG_WIDTH, PNG_HEIGHT);
	}

	@Override
	public void start() {
		serie.clear();
		passingTestsTimeSerie.clear();
		failingTestsTimeSerie.clear();
		refactorTimeSerie.clear();
		serie.add(new Second(), FLAT_POINT);
	}

	@Override
	public void initialize(Composite parent) {
		initializeSeries();

		chart = new JFreeChart(null, createPlot());
		chart.removeLegend();

		ChartComposite chartComposite = new ChartComposite(parent, SWT.NONE, chart, false, false, false, false, false);
		chartComposite.getChartRenderingInfo().setEntityCollection(null);
	}

	private void initializeSeries() {
		serie = new TimeSeries("timeline");
		passingTestsTimeSerie = new TimeSeries("green tests");
		failingTestsTimeSerie = new TimeSeries("red tests");
		refactorTimeSerie = new TimeSeries("refactor");
	}

	private XYPlot createPlot() {
		XYPlot plot = new XYPlot(createDataset(), createXAxis(), createYAxis(), configureShapeAndStroke());
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		plot.setRangeGridlinePaint(Color.white);
		plot.setDomainGridlinePaint(Color.white);
		return plot;
	}

	private TimeSeriesCollection createDataset() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(serie);
		dataset.addSeries(passingTestsTimeSerie);
		dataset.addSeries(failingTestsTimeSerie);
		dataset.addSeries(refactorTimeSerie);
		return dataset;
	}

	private DateAxis createXAxis() {
		DateAxis domain = new DateAxis("Time");
		domain.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
		domain.setAutoRange(true);
		domain.setTickLabelsVisible(true);
		return domain;
	}

	private NumberAxis createYAxis() {
		NumberAxis range = new NumberAxis("Points");
		range.setRange(new Range(MIN_RANGE_POINT, MAX_RANGE_POINT));
		range.setVisible(false);
		range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		return range;
	}

	private XYLineAndShapeRenderer configureShapeAndStroke() {
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		configureTimeSerie(renderer, MAIN_TIMELINE, GRAY_COLOR, CIRCLE_SHAPE, STROKE, true, false);
		configureTimeSerie(renderer, GREEN_TEST_TIMELINE, GREEN_COLOR, CIRCLE_SHAPE, null, false, true);
		configureTimeSerie(renderer, RED_TEST_TIMELINE, RED_COLOR, CIRCLE_SHAPE, null, false, true);
		configureTimeSerie(renderer, REFACTOR_TEST_TIMELINE, BLUE_COLOR, CIRCLE_SHAPE, null, false, true);
		return renderer;
	}

	private void configureTimeSerie(XYLineAndShapeRenderer renderer, int series, Color paint, Double shape,
			BasicStroke stroke, boolean linesVisible, boolean shapesVisible) {
		renderer.setSeriesPaint(series, paint);
		renderer.setSeriesShape(series, shape);
		renderer.setSeriesStroke(series, stroke);
		renderer.setSeriesLinesVisible(series, linesVisible);
		renderer.setSeriesShapesVisible(series, shapesVisible);
	}

	private void drawPoint(final TimeSeries shapeSerie, final int dimension) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				Second second = new Second();
				serie.add(second.previous(), FLAT_POINT);
				serie.add(second, dimension);
				shapeSerie.add(second, dimension);
				serie.add(second.next(), FLAT_POINT);
			}
		});
	}
}
