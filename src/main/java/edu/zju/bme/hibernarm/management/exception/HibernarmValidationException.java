package edu.zju.bme.hibernarm.management.exception;

public class HibernarmValidationException extends RuntimeException {
	private static final long serialVersionUID = -2145239778037939925L;

	public HibernarmValidationException() {

	}

	public HibernarmValidationException(String msg) {
		super(msg);
	}

	public HibernarmValidationException(Throwable e) {
		super(e);
	}
}
