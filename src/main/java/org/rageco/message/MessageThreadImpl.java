package org.rageco.message;

import org.rageco.model.Message;

import java.util.Collections;
import java.util.HashMap;

/**
 * MessageThreadImpl represents ...
 *
 * @author hector.mendoza
 * @version $Id$
 * @since Oct 21, 2015
 *
 */
public class MessageThreadImpl implements MessageThread {

    private final HashMap<String, Message> messages = new HashMap<>();

    /**
     * Constructs an instance of MessageThreadImpl object.
     */
    public MessageThreadImpl() {
	Collections.synchronizedMap(messages);
    }

    /**
     * Overrides addMessage
     *
     * @param message
     * @since Oct 21, 2015
     * @see org.rageco.message.MessageThread#addMessage(org.rageco.model.Message)
     */
    @Override
    public void addMessage(final Message message) {
	if ((message == null) || (message.getId() == null)) {
	    return;
	}
	this.messages.put(message.getId(), message);
    }

    /**
     * Overrides get
     *
     * @param id
     * @return MEssage
     * @since Oct 21, 2015
     * @see org.rageco.message.MessageThread#get(java.lang.String)
     */
    @Override
    public Message get(final String id) {
	if (id == null) {
	    return null;
	}
	return messages.get(id);
    }

    /**
     * Overrides deleteMessage
     *
     * @param id
     * @return Message
     * @since Oct 21, 2015
     * @see org.rageco.message.MessageThread#deleteMessage(java.lang.String)
     */
    @Override
    public Message deleteMessage(final String id) {
	if (id == null) {
	    return null;
	}
	final Message m = messages.remove(id);
	return m;
    }

    /**
     * Overrides containsKey
     *
     * @param id
     * @return boolean
     * @since Oct 21, 2015
     * @see org.rageco.message.MessageThread#containsKey(java.lang.String)
     */
    @Override
    public boolean containsKey(final String id) {
	if (id == null) {
	    return false;
	}
	return this.messages.containsKey(id);
    }

    /**
     * Overrides contains
     *
     * @param message
     * @return boolean
     * @since Oct 21, 2015
     * @see org.rageco.message.MessageThread#contains(org.rageco.model.Message)
     */
    @Override
    public boolean contains(final Message message) {
	if (message == null) {
	    return false;
	}
	return messages.containsValue(message);
    }
}