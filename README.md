rage-engine
===========

This is a personal project to manage tasks and messages through a java system, I developed this tool taking base from the JBPM Job Execution Handler from JBoss community.
It's for personal purpose, if someone want to use it, go ahead fork it or suggest changes.

##Model
* **Task** Simple task interface to implement a single unit of work that is required for a Job.  
* **Message** (In development) Simple message interface to implement,to passed it into the main message-controller.  
* **Status** This is a java enum to manage job states.  

##Main classes
* **RageJob** Is a single job unit, compose by a task, properties object and a status, the last one represent the state of the job in the main thread.  
* **RageThread** This is the main thread that manages the execution and lifecycle of the "jobs".  
