package org.rageco.model;

import java.io.Serializable;

import java.util.Properties;

/**
 * Implement me
 *
 * @author hector.mendoza
 * @since Oct 21, 2015
 *
 */
public interface Task extends Serializable {
    /**
     * Represents execute
     *
     * @since Oct 21, 2015
     *
     */
    public void execute();

    /**
     * Represents preExecute
     *
     * @since Oct 21, 2015
     *
     */
    public void preExecute();

    /**
     * Represents postExecute
     *
     * @since Oct 21, 2015
     *
     */
    public void postExecute();

    /**
     * Represents setProperties
     *
     * @param properties
     * @since Oct 21, 2015
     *
     */
    public void setProperties(Properties properties);

    /**
     * Represents getProperties
     *
     * @return Properties
     * @since Oct 21, 2015
     *
     */
    public Properties getProperties();
}
