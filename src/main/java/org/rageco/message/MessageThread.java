package org.rageco.message;

import org.rageco.model.Message;

/**
 * MessageThread represents ...
 *
 * @author hector.mendoza
 * @since Oct 21, 2015
 *
 */
public interface MessageThread {
    /**
     * Represents addMessage
     *
     * @param message
     * @since Oct 21, 2015
     *
     */
    public void addMessage(Message message);

    /**
     * Represents get
     *
     * @param id
     * @return Message
     * @since Oct 21, 2015
     *
     */
    public Message get(String id);

    /**
     * Represents deleteMessage
     *
     * @param id
     * @return Message
     * @since Oct 21, 2015
     *
     */
    public Message deleteMessage(String id);

    /**
     * Represents containsKey
     *
     * @param id
     * @return boolean
     * @since Oct 21, 2015
     *
     */
    public boolean containsKey(String id);

    /**
     * Represents contains
     *
     * @param message
     * @return boolean
     * @since Oct 21, 2015
     *
     */
    public boolean contains(Message message);
}
