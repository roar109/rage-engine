package org.rageco.test;

import org.rageco.job.RageJobImpl;
import org.rageco.job.RageThread;
import org.rageco.model.Task;

public class Main {

	private RageThread rageThre = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread rt = new RageThread();
		rt.start();
		
		Main m = new Main();
		
		try {
			for(int i = 0;i<10;i++){
				m.addjobs("Job"+i);
			}
			Thread.sleep(40000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i = 11;i<20;i++){
			m.addjobs("Job"+i);
		}
		try {
			for(int i = 20;i<30;i++){
				m.addjobs("Job"+i);
			}
			Thread.sleep(40000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addjobs(String id_){
		if(rageThre != null){
			Task t = new MiTarea();
			t.getProperties().put("id", id_);
			rageThre.newJob(new RageJobImpl(id_,t));
			return;
		}
		rageThre = RageThread.instance();
		Task t = new MiTarea();
		t.getProperties().put("id", id_);
		rageThre.newJob(new RageJobImpl(id_,t));
	}
}
