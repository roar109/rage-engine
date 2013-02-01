rage-engine
===========

This is a personal project to manage tasks and messages through a java system, I developed this tools taking a base from the JBPM Job Execution Handler from JBoss community.
Is for personal purpose, if someone want to use it, go ahead fork it or suggest changes.

##Model
	**Task** Simple task interface to implement a single unit of work that is required in a Job.  
	**Message** (In development) Simple mesage interface to implement a single message passed to a main message-controller.  
	**Status** This is a java enum to manage the Job states.  
##Main classes
	**RageJob** Is a single job unit compose by a task, properties object and a status the last one represent the state of the job in the main thead.  
	**RageThread** This is the main thread that manage the execution and lifecycle of the "jobs".  
