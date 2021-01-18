package org.opentsdb;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

public class BrokerDescriptor {

    private List<String> serverURIs = null;
	private String clientId = null;
	private int mqttVersion = MqttConnectOptions.MQTT_VERSION_3_1;
	private String username = null;
	private String password = null;

	/***
	 * @see BrokerDescriptor#BrokerDescriptor(List)
	 */
	@ConstructorProperties( {"serverURI","username","password"}  )
	public BrokerDescriptor(String serverURI,String username,String password) {
    	super();
    	serverURIs = new ArrayList<>();
    	serverURIs.add(serverURI);
		this.username=username;
		this.password=password;
    }

    /***
     * Set a list of one or more serverURIs the client may connect to.
     *
     * <p>Each <code>serverURI</code> specifies the address of a server that
     * the client may connect to. Two types of connection are supported
     * <code>tcp://</code> for a TCP connection and <code>ssl://</code> for a
     * TCP connection secured by SSL/TLS.
     * For example:
     * <ul>
     *  <li><code>tcp://localhost:1883</code></li>
     *  <li><code>ssl://localhost:8883</code></li>
     * </ul>
     * If the port is not specified, it will
     * default to 1883 for <code>tcp://</code> URIs, and 8883 for
     * <code>ssl://</code> URIs.
     * </p>
     *
     * <p>
     * When an attempt to connect is initiated the client will start with the
     * first serverURI in the list and work through the list until a connection
     * is established with a server. If a connection cannot be made to any of
     * the servers then the connect attempt fails.<br>
     * Specifying a list of servers that a client may connect to has several
     * uses:
     * <ul>
     *  <li>High Availability and reliable message delivery
     *   <br>Some MQTT servers support a high availability feature where two or
     *    more "equal" MQTT servers share state. An MQTT client can connect to
     *    any of the "equal" servers and be assured that messages are reliably
     *    delivered and durable subscriptions are maintained no matter which
     *    server the client connects to.
     *  </li>
     *  <li>Hunt List
     *    <br>A set of servers may be specified that are not "equal" (as in the
     *    high availability option). As no state is shared across the servers
     *    reliable message delivery and durable subscriptions are not valid.
     *  </li>
     * </ul>
     * </p>
     *
     * @param serverURIs list of serverURIs
     *
     */
    @ConstructorProperties( {"serverURIs"} )
    public BrokerDescriptor(List<String> serverURIs) {
        super();
        this.serverURIs = new ArrayList<String>(serverURIs);
    }

	public List<String> getUris() { return serverURIs; }
	public void addServerURI(String uri) { serverURIs.add(uri); }
    public void addServerURIs(List<String> serverURIs) { this.serverURIs.addAll(serverURIs); }

	public String getClientId() { return clientId; }
	public void setClientId(String clientId) { this.clientId = clientId; }

	public int getMqttVersion() { return mqttVersion; }
	public void setMqttVersion(int mqttVersion) { this.mqttVersion = mqttVersion; }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
