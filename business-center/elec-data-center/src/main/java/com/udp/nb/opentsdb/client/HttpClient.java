package com.udp.nb.opentsdb.client;

import java.io.IOException;

import com.udp.nb.opentsdb.client.builder.MetricBuilder;
import com.udp.nb.opentsdb.client.response.Response;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
                                ExpectResponse exceptResponse) throws IOException;
}