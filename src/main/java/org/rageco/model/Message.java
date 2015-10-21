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
public interface Message extends Serializable {
    /**
     * Represents getBody
     *
     * @return Object
     * @since Oct 21, 2015
     *
     */
    public Object getBody();

    /**
     * Represents setBody
     *
     * @param body
     * @since Oct 21, 2015
     *
     */
    public void setBody(Object body);

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

    /**
     * Represents setId
     *
     * @param id
     * @since Oct 21, 2015
     *
     */
    public void setId(String id);

    /**
     * Represents getId
     *
     * @return String
     * @since Oct 21, 2015
     *
     */
    public String getId();
}
