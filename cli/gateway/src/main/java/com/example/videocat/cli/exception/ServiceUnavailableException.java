package com.example.videocat.cli.exception;

public class ServiceUnavailableException extends RuntimeException {
	private static final long serialVersionUID = 7146376131173208456L;

	public ServiceUnavailableException() {
		super();
	}

	public ServiceUnavailableException(String arg0) {
		super(arg0);
	}

	public ServiceUnavailableException(Throwable arg0) {
		super(arg0);
	}

	public ServiceUnavailableException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceUnavailableException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
