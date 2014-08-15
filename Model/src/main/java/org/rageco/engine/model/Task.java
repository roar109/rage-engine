package org.rageco.engine.model;


import java.io.Serializable;
import java.util.Properties;

public interface Task extends Serializable {
	public void execute();
	public void preExecute();
	public void postExecute();
	public void setProperties(Properties properties);
	public Properties getProperties();
}
