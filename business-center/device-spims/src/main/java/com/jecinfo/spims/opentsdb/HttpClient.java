package com.jecinfo.spims.opentsdb;

import com.jecinfo.spims.opentsdb.builder.MetricBuilder;
import com.jecinfo.spims.opentsdb.response.Response;

import java.io.IOException;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
                                ExpectResponse exceptResponse) throws IOException;
}