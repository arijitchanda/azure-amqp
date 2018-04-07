package com.AzureIoTHub.azureamqp.sampler;

import org.apache.jmeter.samplers.AbstractSampler;

import com.AzureIoTHub.azure_amqp_jmeter.Constants;

public abstract class AbstractAzureIoTHubSampler extends AbstractSampler implements Constants {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7163793218595455807L;

	public String getconnectionString() {
		return getPropertyAsString(Connection_String, DEFAULT_CONNECTION_STRING);
	}

	public void setconnectionString(String connectionString) {
		setProperty(Connection_String, connectionString);
	}

	public boolean isConnectionShare() {
		return getPropertyAsBoolean(CONN_SHARE_CONNECTION, DEFAULT_CONNECTION_SHARE);
	}

	public void setConnectionShare(boolean shared) {
		setProperty(CONN_SHARE_CONNECTION, shared);
	}

	public String getConnTimeout() {
		return getPropertyAsString(CONN_TIMEOUT, DEFAULT_CONN_TIME_OUT);
	}

	public void setConnTimeout(String connTimeout) {
		setProperty(CONN_TIMEOUT, connTimeout);
	}

	public String getProtocol() {
		return getPropertyAsString(PROTOCOL, DEFAULT_PROTOCOL);
	}

	public void setProtocol(String protocol) {
		setProperty(PROTOCOL, protocol);
	}

	public boolean isDualSSLAuth() {
		return getPropertyAsBoolean(DUAL_AUTH, false);
	}

	public void setDualSSLAuth(boolean dualSSLAuth) {
		setProperty(DUAL_AUTH, dualSSLAuth);
	}

	public String getKeyStoreFilePath() {
		return getPropertyAsString(CERT_FILE_PATH1, "");
	}

	public void setKeyStoreFilePath(String certFile1) {
		setProperty(CERT_FILE_PATH1, certFile1);
	}

	public String getClientCertFilePath() {
		return getPropertyAsString(CERT_FILE_PATH2, "");
	}

	public void setClientCertFilePath(String certFile2) {
		setProperty(CERT_FILE_PATH2, certFile2);
	}

	public String getKeyStorePassword() {
		return getPropertyAsString(KEY_FILE_PWD1, "");
	}

	public void setKeyStorePassword(String keyStorePassword) {
		this.setProperty(KEY_FILE_PWD1, keyStorePassword);
	}

	public String getClientCertPassword() {
		return getPropertyAsString(KEY_FILE_PWD2, "");
	}

	public void setClientCertPassword(String clientCertPassword) {
		this.setProperty(KEY_FILE_PWD2, clientCertPassword);
	}

	public String getDeviceID() {
		return getPropertyAsString(DEVICEID, DEFAULT_DEVICEID);
	}

	public void setDeviceID(String deviceId) {
		setProperty(DEVICEID, deviceId);
	}

	public String getConnKeepAlive() {
		return getPropertyAsString(CONN_KEEP_ALIVE, DEFAULT_CONN_KEEP_ALIVE);
	}

	public void setConnKeepAlive(String connKeepAlive) {
		setProperty(CONN_KEEP_ALIVE, connKeepAlive);
	}

	public String getConnKeepTime() {
		return getPropertyAsString(CONN_KEEP_TIME, DEFAULT_CONN_KEEP_TIME);
	}

	public void setConnKeepTime(String connKeepTime) {
		setProperty(CONN_KEEP_TIME, connKeepTime);
	}

	public String getConnReconnAttamptMax() {
		return getPropertyAsString(CONN_RECONN_ATTAMPT_MAX, DEFAULT_CONN_RECONN_ATTAMPT_MAX);
	}

	public void setConnReconnAttamptMax(String connReconnAttamptMax) {
		setProperty(CONN_RECONN_ATTAMPT_MAX, connReconnAttamptMax);
	}

	public boolean isKeepTimeShow() {
		return false;
	}

	public boolean isConnectionShareShow() {
		return false;
	}

}
