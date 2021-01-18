package com.open.device.opentsdb;


import com.open.device.opentsdb.builder.MetricBuilder;
import com.open.device.opentsdb.response.Response;

import java.io.IOException;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
								ExpectResponse exceptResponse) throws IOException;
}