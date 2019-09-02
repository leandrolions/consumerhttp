package ar.com.nat.consumerhttp.exceptions;

@SuppressWarnings("serial")
public class ProcessException extends Exception {

	public ProcessException(String message) {
		super(message);
	}
	public ProcessException(String message,Throwable cause) {
		super(message,cause);
	}
}
