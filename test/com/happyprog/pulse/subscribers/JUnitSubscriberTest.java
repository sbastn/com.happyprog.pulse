package com.happyprog.pulse.subscribers;

import static org.mockito.Mockito.*;

import org.eclipse.jdt.junit.model.ITestElement;
import org.junit.Before;
import org.junit.Test;

public class JUnitSubscriberTest {
	private JUnitSubscriber subscriber;
	private TestObserver observer;

	@Before
	public void before() {
		subscriber = new JUnitSubscriberStub();
		observer = mock(TestObserver.class);
		subscriber.subscribe(observer);
	}

	@Test
	public void observerIsNotifiedOnPassingTestsWhenTestsPass() throws Exception {
		subscriber.reportResults(ITestElement.Result.OK);

		verify(observer).onPassingTests();
	}

	@Test
	public void observerIsNotifiedOnFailingWhenTestsHaveErrors() throws Exception {
		subscriber.reportResults(ITestElement.Result.ERROR);

		verify(observer).onFailingTests();
	}

	@Test
	public void observerIsNotifiedOnFailingTestsWhenTestsHaveFailures() throws Exception {
		subscriber.reportResults(ITestElement.Result.FAILURE);

		verify(observer).onFailingTests();
	}

	@Test
	public void observerIsNotifiedOnFailingTestsWhenTestsHaveAnyOtherIssue() throws Exception {
		subscriber.reportResults(ITestElement.Result.UNDEFINED);

		verify(observer).onFailingTests();
	}

	class JUnitSubscriberStub extends JUnitSubscriber {
		@Override
		void addTestRunListener() {
			// overriding to avoid calling JUnitCore
		}
	}
}
