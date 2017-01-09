package by.pvt.pintusov.courses.managers;

import by.pvt.pintusov.courses.constants.ConfigConstant;

import java.util.ResourceBundle;

/**
 * Message manager
 * @author pintusov
 * @version 1.0
 */

public class MessageManager {
	private final ResourceBundle bundle = ResourceBundle.getBundle(ConfigConstant.MESSAGES_SOURCE);
	private static MessageManager instance;

	private MessageManager () {}
	/**
	 * Singleton for message manager
	 * @return instance of Message manager
	 */
	public static synchronized MessageManager getInstance () {
		if (instance == null) {
			instance = new MessageManager();
		}
		return instance;
	}

	/**
	 * Get property of message
	 * @param key - message to call
	 * @return message
	 */
	public String getProperty (String key) { return bundle.getString(key); }
}
