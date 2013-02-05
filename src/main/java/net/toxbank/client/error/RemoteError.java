package net.toxbank.client.error;

public class RemoteError {
	protected String message;
	protected String cause;
	protected String code;
	protected String details;
	protected String actor;
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
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
	@Override
	public String toString() {
		return String.format("{\ncode:%s,\nmessage:\"%s\",\ndetails:\"%s\"\ncause:\"%s\"\n}",
				code==null?"":code,message==null?"":message,
				details==null?"":details,cause==null?"":cause);
	}
}