package org.rageco.job;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rageco.model.Status;

public class RageThread extends Thread {
	private Map<String, RageJob> jobs = Collections.synchronizedMap(new HashMap<String,RageJob>());
	private Map<String,RageJob> history = Collections.synchronizedMap(new HashMap<String,RageJob>());
	private boolean isActive = true;
	private long idleTime = 1000;
	private static final String THREAD_NAME = "RageThreadImpl";
	private static final String THREAD_GROUP_NAME = "RageThreadsGroup";
	private long maxtime = 6*60*1000;
	private long time = 0L;
	private static Log log = LogFactory.getLog(RageThread.class);
	
	public static RageThread instance(){
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
		for(Thread t: threadArray){
			log.debug("Thread: "+t.getName());
			if(RageThread.class.isInstance(t) && t.getName().equals(THREAD_NAME)){
				return (RageThread)t;
			}
		}
		return null;
	}
	
	public RageThread(){
		super(new ThreadGroup(THREAD_GROUP_NAME),THREAD_NAME);
	}
	
	public void run() {
		log.info("Main thread is running...");
		while(isActive){
			try {
				if(time % (2*60*1000) == 0){/**imprime cada dos segundos que esta buscando jobs activos*/
					log.info("Looking for active jobs...");
				}
				seekAndRun();
				Thread.sleep(idleTime);
				time+=idleTime;
				if(time >= maxtime){
					stopThis();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printHistory();
		log.info("Leaving the cyberspace...");
	}

	private void stopThis(){
		isActive = false;
	}
	
	private void seekAndRun(){
		for(String key:jobs.keySet()){
			if(jobs.get(key).getStatus().equals(Status.WAITING)){
				log.info("Found job in waiting status.");
				runJob(jobs.get(key));
			}else if(jobs.get(key).getStatus().equals(Status.FINISHED)){
				log.info("Found job in finished status.");
				history.put(key, jobs.get(key));
			}
		}
		for(String key:history.keySet()){
			if(jobs.containsKey(key)){
				jobs.remove(key);
				log.debug("Removing key "+key);
			}
		}
	}
	
	private void runJob(RageJob rageJob){
		log.info("Running job '"+rageJob.getName()+"'");
		rageJob.start();
	}
	
	public void newJob(RageJob rageJob) {
		if(rageJob == null || rageJob.getName() == null)return;
		log.info("Adding '"+rageJob.getName()+"' to available runnable jobs.");
		rageJob.setStatus(Status.WAITING);
		jobs.put(rageJob.getName(), rageJob);
		runJob(rageJob);
	}

	public RageJob get(String id) {
		if(id == null)return null;
		log.info("Getting job with name '"+id+"'");
		return jobs.get(id);
	}
	
	private void printHistory(){
		for(String key: history.keySet()){
			log.debug("Job Name Proccesed: "+history.get(key).getName());
		}
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
