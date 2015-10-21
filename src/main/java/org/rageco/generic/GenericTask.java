package org.rageco.generic;

import org.rageco.model.Task;

import java.lang.reflect.Method;

import java.util.Properties;

/**
 * GenericTask represents ...
 *
 * @author hector.mendoza
 * @version $Id$
 * @since Oct 21, 2015
 *
 */
public class GenericTask implements Task {

    private static final long serialVersionUID = 1L;
    private Properties properties = new Properties();
    private Object instance = null;
    private final String[] methodNames = new String[3];

    /**
     * Constructor para inicializar una tarea generica, pasandole una instancia
     * serializable, y lso nombres de los metodos a ejecutar.
     *
     * @param instance
     * @param methodExecute
     *            nombre del metodo a ejecutar como primario.
     * @param methodPreExecute
     *            nombre del metodo a ejecutar como precesor al execute.
     * @param methodPostExecute
     *            nombre del metodo a ejecutar despues del metodo execute.
     * */
    public GenericTask(final Object instance, final String methodExecute,
	    final String methodPreExecute, final String methodPostExecute) {
	this.instance = instance;
	methodNames[0] = methodExecute;
	methodNames[1] = methodPreExecute;
	methodNames[2] = methodPostExecute;
    }

    @Override
    public void execute() {
	executeMethod(methodNames[0]);
    }

    @Override
    public void preExecute() {
	executeMethod(methodNames[1]);
    }

    @Override
    public void postExecute() {
	executeMethod(methodNames[2]);
    }

    private void executeMethod(final String methodName) {
	if ((instance != null) && (methodName != null)) {
	    System.out.println("Executing method " + methodName);

	    final Method[] methods = instance.getClass().getMethods();
	    Method method = null;
	    for (final Method m : methods) {
		if (m.getName().equalsIgnoreCase(methodName)) {
		    method = m;
		    break;
		}
	    }
	    if (method != null) {
		try {
		    method.invoke(instance);
		} catch (final Exception e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public void setProperties(final Properties properties) {
	this.properties = properties;
    }

    public Properties getProperties() {
	return properties;
    }
}
