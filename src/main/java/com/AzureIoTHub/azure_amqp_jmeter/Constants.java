package com.AzureIoTHub.azure_amqp_jmeter;

public interface Constants {
	public static final String Connection_String = "Connection String";
	public static final String HOSTNAME = "azuredevice.net";
	public static final String DEVICEID = "DeviceID";
	public static final String SHAREDACCESSKEY = "SharedAccessKey";
	public static final String SHAREDACCESSKEYNAME = "SharedAccessKeyName";
	public static final String CONN_TIMEOUT = "amqp.conn_timeout";

	public static final String PROTOCOL = "amqp.protocol";
	public static final String DUAL_AUTH = "amqp.dual_ssl_authentication";
	public static final String CERT_FILE_PATH1 = "amqp.keystore_file_path";
	public static final String CERT_FILE_PATH2 = "amqp.clientcert_file_path";
	public static final String KEY_FILE_PWD1 = "amqp.keystore_password";
	public static final String KEY_FILE_PWD2 = "amqp.clientcert_password";

	public static final String CONN_KEEP_TIME = "amqp.keep_time";

	public static final String CONN_ELAPSED_TIME = "amqp.conn_elapsed_time";
	public static final String CONN_KEEP_ALIVE = "amqp.conn_keep_alive";
	public static final String CONN_RECONN_ATTAMPT_MAX = "amqp.reconn_attampt_max";
	public static final String CONN_SHARE_CONNECTION = "amqp.conn_share";

	public static final String MESSAGE_TYPE = "amqp.message_type";
	public static final String MESSAGE_FIX_LENGTH = "amqp.message_type_fixed_length";
	public static final String MESSAGE_TO_BE_SENT = "amqp.message_to_sent";

	public static final String ADD_TIMESTAMP = "amqp.add_timestamp";
	public static final String PAYLOAD_SIZE = "amqp.payload_size";

	public static final String SAMPLE_CONDITION_VALUE = "amqp.sample_condition_value";
	public static final String SAMPLE_CONDITION = "amqp.sample_condition";
	public static final String AMQP_PUBLISHER_TITLE = "Azure IoTHub Publisher";
	public static final String TIME_STAMP = "amqp.time_stamp";
	public static final String TIME_STAMP_SEP_FLAG = "ts_sep_flag";
	public static final String AMQP_TEXT_AREA = "Text Message";
	public static final String DEBUG_RESPONSE = "amqp.debug_response";
	public static final String AMQP_FILE = "File";
	public static final String AMQP_MESSAGE_INPUT_TYPE = "amqp_message_input_type";
	public static final String AMQP_MESSAGE_INPUT_TYPE_FILE = "amqp_message_input_type_file";
	public static final String AMQP_MESSAGE_INPUT_TYPE_TEXT = "amqp_message_input_type_text";

	public static final String SAMPLE_ON_CONDITION_OPTION1 = "specified elapsed time (ms)";
	public static final String SAMPLE_ON_CONDITION_OPTION2 = "number of received messages";

	public static final int MAX_CLIENT_ID_LENGTH = 23;
	public static final String DEFAULT_DEVICEID = "deviceid";
	public static final String DEFAULT_CONN_TIME_OUT = "10";
	public static final String DEFAULT_PROTOCOL = "TCP";
	public static final boolean DEFAULT_CONNECTION_SHARE = false;

	public static final String JMETER_VARIABLE_PREFIX = "${";

	public static final String DEFAULT_TOPIC_NAME = "test_topic";

	public static final String DEFAULT_CONN_PREFIX_FOR_CONN = "conn_";
	public static final String DEFAULT_CONN_PREFIX_FOR_PUB = "pub_";
	public static final String DEFAULT_CONN_PREFIX_FOR_SUB = "sub_";
	public static final String DEFAULT_CONNECTION_STRING = "azureiothub.device.net";
	public static final String DEFAULT_CONN_KEEP_ALIVE = "300";
	public static final String DEFAULT_CONN_KEEP_TIME = "1800";
	public static final String DEFAULT_CONN_ATTAMPT_MAX = "0";
	public static final String DEFAULT_CONN_RECONN_ATTAMPT_MAX = "0";

	public static final String DEFAULT_SAMPLE_VALUE_COUNT = "1";
	public static final String DEFAULT_SAMPLE_VALUE_ELAPSED_TIME_SEC = "1000";

	public static final boolean DEFAULT_ADD_TIMESTAMP = false;
	public static final String DEFAULT_MESSAGE_FIX_LENGTH = "1024";

	public static final boolean DEFAULT_ADD_CLIENT_ID_SUFFIX = true;
	public static final String AMQP_BLOCKING_CLIENT = "AMQP";

}
