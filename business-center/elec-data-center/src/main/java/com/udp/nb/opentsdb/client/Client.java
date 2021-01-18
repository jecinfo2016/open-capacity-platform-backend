package com.udp.nb.opentsdb.client;

import java.io.IOException;

import com.udp.nb.opentsdb.client.builder.MetricBuilder;
import com.udp.nb.opentsdb.client.response.Response;
import com.udp.nb.opentsdb.client.response.SimpleHttpResponse;

public interface Client {

	String POST_API = "/api/put";

	/**
	 * Sends metrics from the builder to the KairosDB server.
	 *
	 * @param builder
	 *            metrics builder
	 * @return response from the server
	 * @throws IOException
	 *             problem occurred sending to the server
	 */
	Response pushMetrics(MetricBuilder builder) throws IOException;

}