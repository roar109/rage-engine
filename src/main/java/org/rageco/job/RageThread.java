package org.rageco.job;

import org.rageco.model.Status;

import org.apache.log4j.Logger;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * RageThread represents ...
 *
 * @author hector.mendoza
 * @version $Id$
 * @since Oct 21, 2015
 *
 */
public class RageThread extends Thread implements Serializable {
    private static final long serialVersionUID = 1L;
    private final static float LOAD_FACTOR = 0.75f;
    private final static int INITIAL_SIZE = 20;
    private final Map<String, RageJob> jobs = Collections
	    .synchronizedMap(new HashMap<String, RageJob>(INITIAL_SIZE,
		    LOAD_FACTOR));
    private final Map<String, RageJob> history = Collections
	    .synchronizedMap(new HashMap<String, RageJob>(INITIAL_SIZE,
		    LOAD_FACTOR));
    private boolean isActive = true;
    private final long idleTime = 1000;
    private static final String THREAD_NAME = "RageThreadImpl";
    private static final String THREAD_GROUP_NAME = "RageThreadsGroup";
    private final long maxtime = 3 * 60 * 1000;
    private boolean noMaxTime = false;
    private long time = 0L;
    private final boolean printHistory = false;
    private static Logger log = Logger.getLogger(RageThread.class);

    /**
     * Return the rage thread if it's running.
     *
     * @return RageThread
     * */
    public static RageThread instance() {
	final Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
	final Thread[] threadArray = threadSet.toArray(new Thread[threadSet
		.size()]);
	for (final Thread t : threadArray) {
	    log.debug("Thread: " + t.getName());
	    if (RageThread.class.isInstance(t)
		    && t.getName().equals(THREAD_NAME)) {
		return (RageThread) t;
	    }
	}
	return null;
    }

    /**
     * Constructs an instance of RageThread object.
     */
    public RageThread() {
	super(new ThreadGroup(THREAD_GROUP_NAME), THREAD_NAME);
    }

    /**
     * Overrides run
     *
     * @since Oct 21, 2015
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
	log.info("Main thread is running...");
	while (isActive) {
	    try {
		if ((time % (2 * 60 * 1000)) == 0) {
		    /** Imprime cada dos segundos que esta buscando jobs activos */
		    log.debug("Looking for active jobs...");
		}
		seekAndRun();
		Thread.sleep(idleTime);
		if (!noMaxTime) {
		    time += idleTime;
		    if (time >= maxtime) {
			stopThis();
		    }
		}
	    } catch (final InterruptedException e) {
		e.printStackTrace();
	    }
	}
	log.info("Job size: " + jobs.size());
	log.info("History size: " + history.size());
	printHistory();
	log.info("Leaving the cyberspace...");
    }

    /**
     * Represents stopThis
     *
     * @since Oct 21, 2015
     *
     */
    public void stopThis() {
	isActive = false;
    }

    private void seekAndRun() {
	for (final String key : jobs.keySet()) {
	    if (jobs.get(key).getStatus().equals(Status.WAITING)) {
		log.info("Found job in waiting status.");
		runJob(jobs.get(key));
	    } else if (jobs.get(key).getStatus().equals(Status.FINISHED)) {
		log.info("Found job in finished status.");
		history.put(key, jobs.get(key));
	    }
	}
	for (final String key : history.keySet()) {
	    if (jobs.containsKey(key)) {
		jobs.remove(key);
		log.debug("Removing key " + key);
	    }
	}
    }

    private void runJob(final RageJob rageJob) {
	log.info("Running job '" + rageJob.getName() + "'");
	rageJob.start();
    }

    /**
     * Represents newJob
     *
     * @param rageJob
     * @throws IllegalArgumentException
     * @since Oct 21, 2015
     *
     */
    public void newJob(final RageJob rageJob) throws IllegalArgumentException {
	if ((rageJob == null) || (rageJob.getName() == null)) {
	    return;
	}
	if (jobs.containsKey(rageJob)) {
	    throw new IllegalArgumentException("The key cannot be repeated.");
	}
	log.info("Adding '" + rageJob.getName()
		+ "' to available runnable jobs.");
	rageJob.setStatus(Status.WAITING);
	jobs.put(rageJob.getName(), rageJob);
	runJob(rageJob);
    }

    /**
     * Represents get
     *
     * @param id
     * @return RageJob
     * @since Oct 21, 2015
     *
     */
    public RageJob get(final String id) {
	if (id == null) {
	    return null;
	}
	log.info("Getting job with name '" + id + "'");
	return jobs.get(id);
    }

    private void printHistory() {
	if (!printHistory) {
	    return;
	}
	log.info("-----------------------------------------------------");
	for (final String key : history.keySet()) {
	    log.info("Job Name Proccesed: " + history.get(key).getName());
	}
    }

    /**
     * Represents isActive
     *
     * @return
     * @since Oct 21, 2015
     *
     */
    public boolean isActive() {
	return isActive;
    }

    /**
     * Represents setActive
     *
     * @param isActive
     * @since Oct 21, 2015
     *
     */
    public void setActive(final boolean isActive) {
	this.isActive = isActive;
    }

    /**
     * Represents setNoMaxTime
     *
     * @param noMaxTime
     * @since Oct 21, 2015
     *
     */
    public void setNoMaxTime(final boolean noMaxTime) {
	this.noMaxTime = noMaxTime;
    }

    /**
     * Represents isNoMaxTime
     *
     * @return
     * @since Oct 21, 2015
     *
     */
    public boolean isNoMaxTime() {
	return noMaxTime;
    }
}
