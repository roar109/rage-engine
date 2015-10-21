package org.rageco.job;

import org.rageco.model.Status;
import org.rageco.model.Task;

import java.io.Serializable;

/**
 * Interface that handle the basic structure of a job.
 * */
public interface RageJob extends Serializable {

    /**
     * Represents getStatus
     *
     * @return
     * @since Oct 21, 2015
     *
     */
    public Status getStatus();

    /**
     * Represents setStatus
     *
     * @param status
     * @since Oct 21, 2015
     *
     */
    public void setStatus(Status status);

    /**
     * Represents getTask
     *
     * @return
     * @since Oct 21, 2015
     *
     */
    public Task getTask();

    /**
     * Main method that execute the handlers if the thread starts.
     * */
    public void run();

    /**
     * Represents start
     *
     * @since Oct 21, 2015
     *
     */
    public void start();

    /**
     * Represents getName
     *
     * @return
     * @since Oct 21, 2015
     *
     */
    public String getName();
}
