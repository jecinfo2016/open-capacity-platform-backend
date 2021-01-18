package org.opentsdb;

import java.io.IOException;

import org.opentsdb.builder.MetricBuilder;
import org.opentsdb.response.Response;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
                                ExpectResponse exceptResponse) throws IOException;
}