package org.essilab.servlet.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpResponseCapture extends HttpServletResponseWrapper {

	private final OutputStream buffer;

	private PrintWriter writer;
	private ServletOutputStream outputStream;

	public HttpResponseCapture(HttpServletResponse response, OutputStream buffer) {
		super(response);
		this.buffer = buffer;
	}
	
	@Override
	public ServletResponse getResponse() {
		return this;
	}
	
	@Override
	public ServletOutputStream getOutputStream() {
		if (outputStream == null) {
			outputStream = new ServletOutputStream() {
				@Override
				public void write(int b) throws IOException {
					buffer.write(b);
				}
			};
		}
		return outputStream;
	}

	@Override
	public PrintWriter getWriter() {
		if (writer == null) {
			writer = new PrintWriter(buffer);
		}
		return writer;
	}

	@Override
	public void flushBuffer() throws IOException {
		if (writer != null) {
			writer.flush();
		}
		if (outputStream != null) {
			outputStream.flush();
		}
	}

}