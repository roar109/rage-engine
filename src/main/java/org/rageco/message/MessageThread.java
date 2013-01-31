package org.rageco.message;

import org.rageco.model.Message;

public interface MessageThread {
	public void addMessage(Message message);
	public Message get(String id);
	public Message deleteMessage(String id);
	public boolean containsKey(String id);
	public boolean contains(Message message);
}
