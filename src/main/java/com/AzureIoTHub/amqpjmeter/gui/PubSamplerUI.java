package com.AzureIoTHub.amqpjmeter.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.jmeter.gui.util.FilePanel;
import org.apache.jmeter.gui.util.JLabeledRadioI18N;
import org.apache.jmeter.gui.util.JSyntaxTextArea;
import org.apache.jmeter.gui.util.JTextScrollPane;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledPasswordField;
import org.apache.jorphan.gui.JLabeledTextField;
import org.apache.jorphan.logging.LoggingManager;

import com.AzureIoTHub.azure_amqp_jmeter.Constants;
import com.AzureIoTHub.azureamqp.sampler.PubSampler;

/**
 * This is the AMQP Publisher Sampler GUI class. All swing components of the UI
 * are included in this class.
 */
public class PubSamplerUI extends AbstractSamplerGui implements ChangeListener, ActionListener {

	private static final long serialVersionUID = 240L;
	private static final org.apache.log.Logger log = LoggingManager.getLoggerForClass();

	private static final String[] MESSAGE_INPUT_TYPE = { Constants.AMQP_MESSAGE_INPUT_TYPE_TEXT,
			Constants.AMQP_MESSAGE_INPUT_TYPE_FILE };

	private final JLabeledTextField connectionStringField = new JLabeledTextField(Constants.Connection_String);

	private final JLabeledTextField deviceID = new JLabeledTextField(Constants.DEVICEID);
	private final JLabeledTextField shareAcceskey = new JLabeledPasswordField(Constants.SHAREDACCESSKEY);
	private final JLabeledTextField shareAcceskeyName = new JLabeledPasswordField(Constants.SHAREDACCESSKEYNAME);

	private final JLabeledRadioI18N messageInputValue = new JLabeledRadioI18N(Constants.AMQP_MESSAGE_INPUT_TYPE,
			MESSAGE_INPUT_TYPE, Constants.AMQP_MESSAGE_INPUT_TYPE_TEXT);

	private final JLabel textArea = new JLabel(Constants.AMQP_TEXT_AREA);
	private final JSyntaxTextArea textMessage = new JSyntaxTextArea(10, 50);
	private final JTextScrollPane textPanel = new JTextScrollPane(textMessage);

	private final FilePanel fileChooser = new FilePanel(Constants.AMQP_FILE, "*");

	public PubSamplerUI() {
		init();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabelResource() {
		return this.getClass().getSimpleName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStaticLabel() {
		return Constants.AMQP_PUBLISHER_TITLE;
	}

	/**
	 * Creates a test element for AMQP publisher
	 * 
	 * @see org.apache.jmeter.gui.JMeterGUIComponent#createTestElement()
	 */
	@Override
	public TestElement createTestElement() {
		PubSampler sampler = new PubSampler();
		modifyTestElement(sampler);
		return sampler;
	}

	/**
	 * Modifies a given TestElement to mirror the data in the gui components.
	 *
	 * @see org.apache.jmeter.gui.JMeterGUIComponent#modifyTestElement(TestElement)
	 */
	@Override
	public void modifyTestElement(TestElement s) {
		PubSampler sampler = (PubSampler) s;
		this.configureTestElement(sampler);
		/*
		 * sampler.setConnectionstring(connectionStringField.getText());
		 * sampler.setDeviceId(deviceID.getText());
		 * //sampler.setsh(AMQPDestination.getText());
		 * sampler.setCleanSession(cleanSession.isSelected());
		 * sampler.setKeepAlive(AMQPKeepAlive.getText());
		 * 
		 * sampler.setMessageInputType(messageInputValue.getText()); if
		 * (messageInputValue.getText().equals(Constants.
		 * AMQP_MESSAGE_INPUT_TYPE_TEXT)) {
		 * sampler.setMessageValue(textMessage.getText()); } else if
		 * (messageInputValue.getText().equals(Constants.
		 * AMQP_MESSAGE_INPUT_TYPE_FILE)) {
		 * sampler.setMessageValue(fileChooser.getFilename()); }
		 */
	}

	/**
	 * Initializes all the UI elements
	 */
	private void init() {
		connectionStringField.setText(Constants.Connection_String);
		setLayout(new BorderLayout());
		setBorder(makeBorder());
		add(makeTitlePanel(), BorderLayout.NORTH);
		JPanel mainPanel = new VerticalPanel();
		add(mainPanel, BorderLayout.CENTER);
		JPanel DPanel = new JPanel();
		DPanel.setLayout(new BoxLayout(DPanel, BoxLayout.X_AXIS));
		DPanel.add(connectionStringField);
		JPanel ControlPanel = new VerticalPanel();
		ControlPanel.add(DPanel);
		ControlPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Connection Info"));
		mainPanel.add(ControlPanel);
		JPanel TPanel = new VerticalPanel();
		TPanel.add(deviceID);
		TPanel.add(shareAcceskey);
		TPanel.add(shareAcceskeyName);
		TPanel.setLayout(new BoxLayout(TPanel, BoxLayout.X_AXIS));
		TPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "IoTHub Info"));
		mainPanel.add(TPanel);

		// Input type panels
		JPanel contentPanel = new VerticalPanel();
		messageInputValue.setLayout(new BoxLayout(messageInputValue, BoxLayout.X_AXIS));
		contentPanel.add(messageInputValue);

		JPanel filePanel = new JPanel(new BorderLayout());
		filePanel.add(this.fileChooser, BorderLayout.CENTER);
		contentPanel.add(filePanel);

		// Text input panel
		JPanel messageContentPanel = new JPanel(new BorderLayout());
		messageContentPanel.add(this.textArea, BorderLayout.NORTH);
		messageContentPanel.add(this.textPanel, BorderLayout.CENTER);
		contentPanel.add(messageContentPanel);

		contentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Content"));
		mainPanel.add(contentPanel);

		// Setting default values and handlers
		messageInputValue.addChangeListener(this);
		connectionStringField.setText(Constants.DEFAULT_CONNECTION_STRING);

		this.textArea.setVisible(true);
		this.textPanel.setVisible(true);
		this.fileChooser.setVisible(false);

	}

	/**
	 * {@inheritDoc}. </br>
	 * . Loads fields from an existing sampler file.
	 *
	 * @param el
	 *            The test element
	 */
	@Override
	public void configure(TestElement el) {
		super.configure(el);
		PubSampler sampler = (PubSampler) el;
		connectionStringField.setText(PubSampler.getConnectionStirng());
		deviceID.setText(PubSampler.getDeviceId());
		shareAcceskey.setText(PubSampler.getSharedaccesskey());
		shareAcceskeyName.setText(PubSampler.getSharedaccesskeyname());
		messageInputValue.setText(sampler.getMessageInputType());

		if (sampler.getMessageInputType().equals(Constants.AMQP_MESSAGE_INPUT_TYPE_TEXT)) {
			textMessage.setText(sampler.getMessageValue());
			this.textArea.setVisible(true);
			this.textPanel.setVisible(true);
			this.fileChooser.setVisible(false);
		} else if (sampler.getMessageInputType().equals(Constants.AMQP_MESSAGE_INPUT_TYPE_FILE)) {
			fileChooser.setFilename(sampler.getMessageValue());
			this.textArea.setVisible(false);
			this.textPanel.setVisible(false);
			this.fileChooser.setVisible(true);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearGui() {
		super.clearGui();
	}

	/**
	 * Creates the topic destination panel.
	 *
	 * @return The topic destination panel.
	 */

	/**
	 * Creates the AMQP client keep alive panel.
	 *
	 * @return The AMQP client keep alive panel.
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		if (Constants.AMQP_MESSAGE_INPUT_TYPE_TEXT.equals(messageInputValue.getText())) {
			this.textArea.setVisible(true);
			this.textPanel.setVisible(true);
			this.fileChooser.setVisible(false);
		} else if (Constants.AMQP_MESSAGE_INPUT_TYPE_FILE.equals(messageInputValue.getText())) {
			this.textArea.setVisible(false);
			this.textPanel.setVisible(false);
			this.fileChooser.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}