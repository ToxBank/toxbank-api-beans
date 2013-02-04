package net.toxbank.client.error;

public class RemoteError {

	protected long code;
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	protected String message;
	protected String cause;
	@Override
	public String toString() {
		return String.format("{\ncode:%d,\nmessage:\"%s\",\ncause:\"%s\"\n}",code,message,cause);
	}
}