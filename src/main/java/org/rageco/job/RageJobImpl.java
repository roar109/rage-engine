package org.rageco.job;

import org.rageco.model.Status;
import org.rageco.model.Task;

import org.apache.log4j.Logger;

/**
 * RageJobImpl represents ...
 *
 * @author hector.mendoza
 * @version $Id$
 * @since Oct 21, 2015
 *
 */
public class RageJobImpl extends Thread implements RageJob {

    private static final long serialVersionUID = 1L;
    private Task task;
    private Status status;
    private boolean isActive = true;
    private final long waitTime = 60 * 1000;
    private int attempt = 0;
    private final int MAX_ATTEMPT = 5;
    private static Logger log = Logger.getLogger(RageJobImpl.class);

    /**
     * Constructs an instance of RageJobImpl object.
     *
     * @param id
     * @param task
     */
    public RageJobImpl(final String id, final Task task) {
	super(id);
	this.task = task;
    }

    /**
     * Overrides run
     *
     * @since Oct 21, 2015
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
	l: while (isActive) {
	    try {
		attempt++;
		status = Status.RUNNING;
		log.debug("preExecute");
		task.preExecute();
		log.debug("end preExecute");
		log.debug("execute");
		task.execute();
		log.debug("end execute");
		log.debug("postExecute");
		task.postExecute();
		log.debug("end postExecute");
		status = Status.FINISHED;
		isActive = false;

	    } catch (final Exception e) {
		log.error("Something went wrong, waiting "
			+ (waitTime / (60 * 1000))
			+ " minute(s) before continue.");
		e.printStackTrace();
		status = Status.FAILED;
		isActive = true;

		if (attempt >= MAX_ATTEMPT) {
		    isActive = false;
		    status = Status.SUSPENDED;
		    log.error("Max attemps reached, suspending job.");
		    break l;
		}
		try {
		    Thread.sleep(waitTime);
		} catch (final InterruptedException e1) {
		    e1.printStackTrace();
		}
	    }
	}

    }

    /**
     * Overrides getTask
     *
     * @return
     * @since Oct 21, 2015
     * @see org.rageco.job.RageJob#getTask()
     */
    @Override
    public Task getTask() {
	return task;
    }

    /**
     * Represents setTask
     *
     * @param task
     * @since Oct 21, 2015
     *
     */
    public void setTask(final Task task) {
	this.task = task;
    }

    /**
     * Overrides getStatus
     *
     * @return
     * @since Oct 21, 2015
     * @see org.rageco.job.RageJob#getStatus()
     */
    @Override
    public Status getStatus() {
	return status;
    }

    /**
     * Overrides setStatus
     *
     * @param status
     * @since Oct 21, 2015
     * @see org.rageco.job.RageJob#setStatus(org.rageco.model.Status)
     */
    @Override
    public void setStatus(final Status status) {
	this.status = status;
    }
}
