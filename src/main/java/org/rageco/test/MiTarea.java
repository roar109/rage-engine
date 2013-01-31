package org.rageco.test;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rageco.model.Task;

public class MiTarea implements Task {
	private static Log log = LogFactory.getLog(MiTarea.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Properties p = new Properties();
	public void execute() {
		log.info("\tHeavy sh1th is happening here...  "+p.getProperty("id"));
		if(p.get("id").equals("Job3")){
			p.get("d").equals("");
		}

	}

	public void preExecute() {
		log.info("\tHappy preexecute- "+p.getProperty("id"));

	}

	public void postExecute() {
		log.info("\tHappy post execute- "+p.getProperty("id"));
	}

	public void setProperties(Properties properties) {
		p = properties;
	}

	public Properties getProperties() {
		return p;
	}

}
