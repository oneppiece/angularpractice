package com.demo.angularpractice.auth.ajax;


import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * liyan-下午7:37
 *
 * @author liyan
 **/
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private final byte[] body;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		ServletInputStream stream = request.getInputStream();
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = stream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		this.body = swapStream.toByteArray();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);
		return new ServletInputStream() {

			/**
			 * Has the end of this InputStream been reached?
			 *
			 * @return <code>true</code> if all the data has been read from the stream,
			 * else <code>false</code>
			 * @since Servlet 3.1
			 */
			@Override
			public boolean isFinished() {
				return false;
			}

			/**
			 * Can data be read from this InputStream without blocking?
			 * Returns  If this method is called and returns false, the container will
			 * invoke {@link ReadListener#onDataAvailable()} when data is available.
			 *
			 * @return <code>true</code> if data can be read without blocking, else
			 * <code>false</code>
			 * @since Servlet 3.1
			 */
			@Override
			public boolean isReady() {
				return false;
			}

			/**
			 * Sets the {@link ReadListener} for this {@link ServletInputStream} and
			 * thereby switches to non-blocking IO. It is only valid to switch to
			 * non-blocking IO within async processing or HTTP upgrade processing.
			 *
			 * @param listener The non-blocking IO read listener
			 * @throws IllegalStateException If this method is called if neither
			 *                               async nor HTTP upgrade is in progress or
			 *                               if the {@link ReadListener} has already
			 *                               been set
			 * @throws NullPointerException  If listener is null
			 * @since Servlet 3.1
			 */
			@Override
			public void setReadListener(ReadListener listener) {

			}

			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}

}
