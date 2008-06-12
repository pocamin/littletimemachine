package org.littleTeamMachine.srv.controller.Invoker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

public class CustomHttpInvokerServiceExporter extends HttpInvokerServiceExporter {
	
	@Override
	protected ObjectOutputStream createObjectOutputStream(OutputStream os)
			throws IOException {
		ObjectOutputStream oos = new CustomObjectOutputStream(os);
		
		return oos;
	}
	

}
