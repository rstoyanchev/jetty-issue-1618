package org.example;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet(asyncSupported = true)
public class TestServlet extends GenericServlet{

	public void service(ServletRequest req, ServletResponse res) {

		DispatcherType dispatcherType = req.getDispatcherType();
		String requestURI = ((HttpServletRequest) req).getRequestURI();
		System.out.println("Processing " + dispatcherType + " to " + requestURI);

		if (!dispatcherType.equals(DispatcherType.REQUEST)) {
			System.out.println("Completing async request");
			return;
		}

		final AsyncContext context = req.startAsync(req, res);

		new Thread(() -> {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			context.dispatch();
		}).start();

	}

}
