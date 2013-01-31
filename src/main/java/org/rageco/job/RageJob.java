package org.rageco.job;

import org.rageco.model.Status;
import org.rageco.model.Task;

public interface RageJob{
	public Status getStatus();
	public void setStatus(Status status);
	public Task getTask();
	public void run();
	public void start();
	public String getName();
}
