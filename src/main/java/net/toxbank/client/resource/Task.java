package net.toxbank.client.resource;

import java.util.Date;
import java.util.UUID;

import net.toxbank.client.error.RemoteError;

/**
 * Copy of net.idea.restnet.i.task  - or better to add the restlet:i dependency ? 
 * @author nina
 *
 * @param <REFERENCE>
 * @param <USERID>
 */
public class Task<REFERENCE,USERID> extends AbstractToxBankResource /*, PropertyChangeListener */ {
	
	public enum TaskProperty {
		PROPERTY_NAME {
			@Override
			public void update(Task task,Object value) {
				if (value!=null) task.setTitle(value.toString());
			}
		},
		PROPERTY_PERCENT {
			@Override
			public void update(Task task,Object value) {
				try {
				if (value!=null) task.setPercentCompleted(Float.parseFloat(value.toString()));
				} catch (Exception x) {}
			}			
		};
		public abstract void update(Task task,Object value);
	}
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -646087833848914553L;

	public enum TaskStatus {Running,Cancelled,Completed,Error,Queued};
	protected REFERENCE result;
	protected String name = "Default";
	protected long started = System.currentTimeMillis();
	protected long completed = -1;
	protected boolean internal = true;
	
	public Task() {
		super();
	}
	public boolean isInternal() {
		return internal;
	}
	public void setInternal(boolean internal) {
		this.internal = internal;
	}
	public long getTimeCompleted() {
		return completed;
	}
	public void setTimeCompleted(long completed) {
		this.completed = completed;
	}
	protected float percentCompleted = 0;
	protected USERID userid;
	protected UUID uuid = UUID.randomUUID();
	protected RemoteError error = null;
	protected Exception policyError = null;
	
	public Exception getPolicyError() {
		return policyError;
	}
	public void setPolicyError(Exception policyError) {
		this.policyError = policyError;
	}
	public void setError(RemoteError error) {
		this.error = error;
	}
	public RemoteError getError() {
		return error;
	}
	protected TaskStatus status= TaskStatus.Queued;
	
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public USERID getUserid() {
		return userid;
	}
	public void setUserid(USERID userid) {
		this.userid = userid;
	}
	public boolean isExpired(long lifetime) {
		return (System.currentTimeMillis()-started) > lifetime;
	}
	public TaskStatus getStatus() {
		return status;

	}
	public float getPercentCompleted() {
		return percentCompleted;
	}
	public void setPercentCompleted(float percentCompleted) {
		this.percentCompleted = percentCompleted;
	}
	public long getStarted() {
		return started;
	}
	public void setStarted(long started) {
		this.started = started;
	}

	public synchronized REFERENCE getUri() {
		return result;
	}
	public synchronized void setUri(REFERENCE uri) {
		this.result = uri;
	}
	
	public Task(USERID user) {
		
		this.userid = user;
	}


	public boolean isDone() {
		return TaskStatus.Completed.equals(status) || TaskStatus.Error.equals(status)  || TaskStatus.Cancelled.equals(status);
	}

	@Override
	public String toString() {
		try {
		return String.format("%s [%s] Started %s Completed %s [%s] [%s] %s",
				name==null?"?":name,
				getUri(),
				new Date(started),
				((completed>0)?new Date(completed):"-"),
				getStatus(),
				userid,
				error==null?"":error.getMessage()
				);
		} catch (Exception x) {
			return x.getMessage();
		}
	}

	/**
	* does nothing, should be autoupdated by ExecutableTask
	 */
	public synchronized void update()  {

	}
	/**
	 * Does nothing so far, but should register the result URI to the policy server
	 */
	public void setPolicy() throws Exception {
		
	}

	@Override
	public void setTitle(String title) {
		this.name = title;
	}
	@Override
	public String getTitle() {
		return name;
	}
}
