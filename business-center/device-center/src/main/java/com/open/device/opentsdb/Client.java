package com.open.device.opentsdb;

import com.open.device.opentsdb.builder.MetricBuilder;
import com.open.device.opentsdb.response.Response;

import java.io.IOException;

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