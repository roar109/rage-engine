package org.rageco.generic;

import java.lang.reflect.Method;
import java.util.Properties;

import org.rageco.engine.model.Task;


public class GenericTask implements Task {

	private static final long serialVersionUID = 1L;
	private Properties properties = new Properties();
	private Object instance = null;
	private String[] methodNames = new String[3];
	
	/**
	 * Constructor para inicializar una tarea generica, pasandole una instancia serializable, y lso nombres
	 * de los metodos a ejecutar.
	 * @param instance
	 * @param methodExecute nombre del metodo a ejecutar como primario.
	 * @param methodPreExecutenombre del metodo a ejecutar como precesor al execute.
	 * @param methodPostExecute nombre del metodo a ejecutar despues del metodo execute.
	 * */
	public GenericTask(Object instance, String methodExecute, String methodPreExecute, String methodPostExecute){
		this.instance = instance;
		methodNames[0] = methodExecute;
		methodNames[1] = methodPreExecute;
		methodNames[2] = methodPostExecute;
	}
	
	public void execute() {
		executeMethod(methodNames[0]);
	}

	public void preExecute() {
		executeMethod(methodNames[1]);
	}

	public void postExecute() {
		executeMethod(methodNames[2]);
	}

	private void executeMethod(String methodName){
		if(instance != null && methodName != null){
			System.out.println("Executing method "+methodName);
			Method[] methods = instance.getClass().getMethods();
			Method method = null;
			for(Method m: methods){
				if(m.getName().equalsIgnoreCase(methodName)){
					method = m;
					break;
				}
			}
			if(method != null){
				try {
					method.invoke(instance);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}
}
