package org.rageco.model;

/**
 * Status represents ...
 *
 * @author hector.mendoza
 * @since Oct 21, 2015
 *
 */
public enum Status {

    /** Status for RUNNING */
    RUNNING(1), /** Status for FAILED */
    FAILED(2), /** Status for WAITING */
    WAITING(3), /** Status for FINISHED */
    FINISHED(4), /** Status for SUSPENDED */
    SUSPENDED(5);

    private int status = 0;

    Status() {

    }

    Status(final int status) {
	this.status = status;
    }

    /**
     * Represents getStatus
     *
     * @return int
     * @since Oct 21, 2015
     *
     */
    public int getStatus() {
	return status;
    }
}
