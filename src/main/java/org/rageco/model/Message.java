package org.rageco.model;

import java.io.Serializable;
import java.util.Properties;

public interface Message extends Serializable{
	public Object getBody();
	public void setBody(Object body);
	public void setProperties(Properties properties);
	public Properties getProperties();
	public void setId(String id);
	public String getId();
}
