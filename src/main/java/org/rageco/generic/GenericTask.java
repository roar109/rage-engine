package org.rageco.generic;

import java.util.Properties;

import org.rageco.model.Task;

public class GenericTask implements Task {

	private static final long serialVersionUID = 1L;
	private Properties properties = new Properties();
	
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	public void preExecute() {
		// TODO Auto-generated method stub
		
	}

	public void postExecute() {
		// TODO Auto-generated method stub
		
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}

}
