package org.rageco.engine.model;


public enum Status {
	RUNNING(1), FAILED(2), WAITING(3), FINISHED(4), SUSPENDED(5);
	private int status = 0;
	Status(){}
	Status(int status){this.status = status;}
	public int getStatus(){return status;}
}
