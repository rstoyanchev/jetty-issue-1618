package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {


	public static void main(String[] args) throws Exception {

		TestServlet servlet = new TestServlet();

		Server server = new Server();
		ServletHolder servletHolder = new ServletHolder(servlet);

		ServletContextHandler contextHandler = new ServletContextHandler(server, "/", false, false);
		contextHandler.addServlet(servletHolder, "/*");
		contextHandler.start();

		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.addConnector(connector);
		server.start();

	}

}
