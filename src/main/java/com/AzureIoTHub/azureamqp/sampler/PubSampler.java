package com.AzureIoTHub.azureamqp.sampler;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import com.AzureIoTHub.azure_amqp_jmeter.Constants;
import com.microsoft.azure.sdk.iot.service.DeliveryAcknowledgement;
import com.microsoft.azure.sdk.iot.service.FeedbackBatch;
import com.microsoft.azure.sdk.iot.service.FeedbackReceiver;
import com.microsoft.azure.sdk.iot.service.IotHubServiceClientProtocol;
import com.microsoft.azure.sdk.iot.service.Message;
import com.microsoft.azure.sdk.iot.service.ServiceClient;

/**
 * This is AMQP Publisher sample class. The implementation includes publishing
 * of AMQP messages with the sample processing.
 */
public class PubSampler extends AbstractSampler implements TestStateListener {

	// private transient BaseClient client;

	private byte[] publishMessage;
	private AtomicInteger publishedMessageCount = new AtomicInteger(0);
	private static final String nameLabel = "Azure IoTHub Publisher";
	private static final String lineSeparator = System.getProperty("line.separator");

	private static final long serialVersionUID = 233L;
	private static final Logger log = LoggingManager.getLoggerForClass();

	private static final String CONNECTION_STIRNG = "azure.device.net";
	private static final String DEVICE_ID = "deviceid";
	private static final String SHAREDACCESSKEY = "azure.device";
	private static final String SHAREDACCESSKEYNAME = "azure.device";

	private static final String KEEP_ALIVE = "amqp.keep.alive";
	private static final String CLIENT_TYPE = "AMQP";
	private static final String MESSAGE_INPUT_TYPE = "amqp.message.input.type";
	private static final String MESSAGE_VALUE = "amqp.message.input.value";
	private static final IotHubServiceClientProtocol protocol = IotHubServiceClientProtocol.AMQPS;
	private static ServiceClient serviceClient = null;
	private static FeedbackReceiver feedbackReceiver = null;
	// Getters Setters

	public String getClientType() {
		return getPropertyAsString(CLIENT_TYPE);
	}

	public String getMessageInputType() {
		return getPropertyAsString(MESSAGE_INPUT_TYPE);
	}

	public String getMessageValue() {
		return getPropertyAsString(MESSAGE_VALUE);
	}

	public String getNameLabel() {
		return nameLabel;
	}

	public void setClientType(String clientType) {
		setProperty(CLIENT_TYPE, clientType.trim());
	}

	public void setMessageInputType(String messageInputType) {
		setProperty(MESSAGE_INPUT_TYPE, messageInputType.trim());
	}

	public void setMessageValue(String messageValue) {
		setProperty(MESSAGE_VALUE, messageValue.trim());
	}

	public static String getConnectionStirng() {
		return CONNECTION_STIRNG;
	}

	public static String getDeviceId() {
		return DEVICE_ID;
	}

	public static String getSharedaccesskey() {
		return SHAREDACCESSKEY;
	}

	public static String getKeepAlive() {
		return KEEP_ALIVE;
	}

	/**
	 * @return the sharedaccesskeyname
	 */
	public static String getSharedaccesskeyname() {
		return SHAREDACCESSKEYNAME;
	}

	public PubSampler() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void testEnded() {
		log.debug("Thread ended " + new Date());
		try {
			// ClientPool.clearClient();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void testEnded(String arg0) {
		testEnded();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void testStarted() {
		if (log.isDebugEnabled()) {
			log.debug("Thread started " + new Date());
			log.debug("AMQP PublishSampler: [" + Thread.currentThread().getName() + "], hashCode=[" + hashCode() + "]");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void testStarted(String arg0) {
		testStarted();
	}

	/**
	 * Initializes the AMQP client for publishing.
	 *
	 * @throws AMQPException
	 */
	private void initClient() throws IOException {
		try {
			String connectionString = getConnectionStirng();

			String sharedAccesskey = getSharedaccesskey();
			String keepAlive = getKeepAlive();
			String shareAccesskeyName = getSharedaccesskeyname();
			String messageInputType = getMessageInputType();
			String clientType = getClientType();

			if (Constants.AMQP_MESSAGE_INPUT_TYPE_TEXT.equals(messageInputType)) {
				publishMessage = getMessageValue().getBytes();
			} else if (Constants.AMQP_MESSAGE_INPUT_TYPE_FILE.equals(messageInputType)) {
				publishMessage = FileUtils.readFileToByteArray(new File(getMessageValue()));
			}

			if (Constants.AMQP_BLOCKING_CLIENT.equals(clientType)) {

				serviceClient = ServiceClient.createFromConnectionString(connectionString, protocol);

				if (serviceClient != null) {
					serviceClient.open();
					feedbackReceiver = serviceClient.getFeedbackReceiver();
					if (feedbackReceiver != null) {
						feedbackReceiver.open();
					}
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw e;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SampleResult sample(Entry entry) {
		String deviceId = getDeviceId();
		SampleResult result = new SampleResult();
		result.setSampleLabel(getNameLabel());
		result.sampleStart();
		if (serviceClient == null) {
			try {
				initClient();
			} catch (IOException e) {
				result.sampleEnd(); // stop stopwatch
				result.setSuccessful(false);
				// get stack trace as a String to return as document data
				java.io.StringWriter stringWriter = new java.io.StringWriter();
				e.printStackTrace(new java.io.PrintWriter(stringWriter));
				result.setResponseData(stringWriter.toString(), null);
				result.setResponseMessage("Unable publish messages." + lineSeparator + "Exception: " + e.toString());
				result.setDataType(org.apache.jmeter.samplers.SampleResult.TEXT);
				result.setResponseCode("FAILED");
				return result;
			} catch (Exception e) {
				result.sampleEnd(); // stop stopwatch
				result.setSuccessful(false);
				// get stack trace as a String to return as document data
				java.io.StringWriter stringWriter = new java.io.StringWriter();
				e.printStackTrace(new java.io.PrintWriter(stringWriter));
				result.setResponseData(stringWriter.toString(), null);
				result.setResponseMessage("Unable publish messages." + lineSeparator + "Exception: " + e.toString());
				result.setDataType(org.apache.jmeter.samplers.SampleResult.TEXT);
				result.setResponseCode("FAILED");
				return result;
			}
		}
		try {
			Message message = new Message(publishMessage);
			message.setDeliveryAcknowledgement(DeliveryAcknowledgement.Full);
			serviceClient.send(deviceId, message);
			FeedbackBatch feedbackBatch;
			try {
				feedbackBatch = feedbackReceiver.receive(10000);
				if (feedbackBatch != null) {
					result.setResponseMessage(feedbackBatch.getEnqueuedTimeUtc().toString());
				} else {
					result.setResponseMessage("Message Feedback Not Recieved");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (feedbackReceiver != null) {
				feedbackReceiver.close();
				serviceClient.close();
			}
			result.setSuccessful(true);
			result.sampleEnd(); // stop stopwatch
			result.setResponseMessage("Sent " + publishedMessageCount.incrementAndGet() + " messages total");
			result.setResponseCode("OK");
			return result;
		} catch (IOException e) {
			result.sampleEnd(); // stop stopwatch
			result.setSuccessful(false);
			// get stack trace as a String to return as document data
			java.io.StringWriter stringWriter = new java.io.StringWriter();
			e.printStackTrace(new java.io.PrintWriter(stringWriter));
			result.setResponseData(stringWriter.toString(), null);
			result.setResponseMessage("Unable publish messages." + lineSeparator + "Exception: " + e.toString());
			result.setDataType(org.apache.jmeter.samplers.SampleResult.TEXT);
			result.setResponseCode("FAILED");
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}