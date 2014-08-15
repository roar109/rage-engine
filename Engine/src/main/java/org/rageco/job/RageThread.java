package org.rageco.job;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rageco.engine.model.Status;

public class RageThread extends Thread implements Serializable{
	private static final long serialVersionUID = 1L;
	private final static float LOAD_FACTOR =  0.75f; 
	private final static int INITIAL_SIZE =  20;
	private Map<String, RageJob> jobs = Collections.synchronizedMap(new HashMap<String,RageJob>(INITIAL_SIZE, LOAD_FACTOR));
	private Map<String,RageJob> history = Collections.synchronizedMap(new HashMap<String,RageJob>(INITIAL_SIZE, LOAD_FACTOR));
	private boolean isActive = true;
	private long idleTime = 1000;
	private static final String THREAD_NAME = "RageThreadImpl";
	private static final String THREAD_GROUP_NAME = "RageThreadsGroup";
	private long maxtime = 3*60*1000;
	private boolean noMaxTime = false;
	private long time = 0L;
	private boolean printHistory = false;
	private static Log log = LogFactory.getLog(RageThread.class);
	
	/**
	 * Return the rage thread if it's running.
	 * @return RageThread
	 * */
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
				if(time % (2*60*1000) == 0){/**Imprime cada dos segundos que esta buscando jobs activos*/
					log.debug("Looking for active jobs...");
				}
				seekAndRun();
				Thread.sleep(idleTime);
				if(!noMaxTime){
					time+=idleTime;
					if(time >= maxtime){
						stopThis();
					}					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("Job size: "+jobs.size());
		log.info("History size: "+history.size());
		printHistory();
		log.info("Leaving the cyberspace...");
	}

	public void stopThis(){
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
	
	public void newJob(RageJob rageJob) throws IllegalArgumentException{
		if(rageJob == null || rageJob.getName() == null)return;
		if(jobs.containsKey(rageJob)){throw new IllegalArgumentException("The key cannot be repeated.");}
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
		if(!printHistory)return;
		log.info("-----------------------------------------------------");
		for(String key: history.keySet()){
			log.info("Job Name Proccesed: "+history.get(key).getName());
		}
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setNoMaxTime(boolean noMaxTime) {
		this.noMaxTime = noMaxTime;
	}

	public boolean isNoMaxTime() {
		return noMaxTime;
	}
}
