package org.rageco.job;

import java.io.Serializable;

import org.rageco.engine.model.Status;
import org.rageco.engine.model.Task;


/**
 * Interface that handle the basic structure of a job.
 * */
public interface RageJob extends Serializable{
	public Status getStatus();
	public void setStatus(Status status);
	public Task getTask();
	/**
	 * Main method that execute the handlers if the thread starts.
	 * */
	public void run();
	public void start();
	public String getName();
}
