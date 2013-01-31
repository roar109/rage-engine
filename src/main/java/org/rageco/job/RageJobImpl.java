package org.rageco.job;


import javax.transaction.SystemException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rageco.model.Status;
import org.rageco.model.Task;

public class RageJobImpl extends Thread implements RageJob {
	private Task task;
	private Status status;
	private boolean isActive = true;
	private long waitTime = 60*1000 ;
	private int attempt = 0;
	private final int MAX_ATTEMPT = 5;
	private static Log log = LogFactory.getLog(RageJobImpl.class);
	
	public RageJobImpl(String id, Task task){
		super(id);
		this.task = task;
	}
	
	public void run() {
		l:while(isActive){
			try{
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
				
			}catch(Exception e){
				log.error("Something went wrong, waiting "+waitTime/(60*1000)+" minute(s) before continue.");
				e.printStackTrace();
				status = Status.FAILED;
				isActive = true;
				if(attempt >= MAX_ATTEMPT){
					isActive = false;
					log.error("Max attemps reached, closing job.");
					break l;
				}
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}	
		}

	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
