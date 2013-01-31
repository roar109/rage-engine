package org.rageco.message;

import java.util.Collections;
import java.util.HashMap;

import org.rageco.model.Message;

public class MessageThreadImpl implements MessageThread{

	private HashMap<String,Message> messages = new HashMap<String, Message>();
	
	public MessageThreadImpl(){Collections.synchronizedMap(messages);}
	
	public void addMessage(Message message) {
		if(message == null || message.getId() == null){return;}
		this.messages.put(message.getId(),message);
	}

	public Message get(String id) {
		if(id ==null)return null;
		return messages.get(id);
	}

	public Message deleteMessage(String id) {
		if(id == null)return null;
		Message m = messages.remove(id);
		return m;
	}

	public boolean containsKey(String id) {
		if(id == null)return false;
		return this.messages.containsKey(id);
	}

	public boolean contains(Message message) {
		if(message == null) return false;
		return messages.containsValue(message);
	}
}