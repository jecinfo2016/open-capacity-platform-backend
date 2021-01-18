package com.jecinfo.kelamqtt.opentsdb;

import com.jecinfo.kelamqtt.opentsdb.builder.MetricBuilder;
import com.jecinfo.kelamqtt.opentsdb.response.Response;

import java.io.IOException;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
                                ExpectResponse exceptResponse) throws IOException;
}