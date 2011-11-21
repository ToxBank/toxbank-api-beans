package net.toxbank.client.resource;

import java.net.URL;

public class ProtocolVersion extends AbstractToxBankResource {

	private String abstrakt;
	private String info;
	private String submissionDate;
	private boolean isSearchable = false;
	private ProtocolVersion previousVersion;

	public ProtocolVersion() {}
	
	public ProtocolVersion(URL identifier) {
	}
	
	/**
	 * Described in this <a href="http://api.toxbank.net/index.php/API_Protocol:Uploadt">API documentation</a>.
	 */
	public URL upload(String server) {
		// FIXME: implement uploading this protocol to the server
		return null;
	}

	public void setAbstract(String abstrakt) {
		this.abstrakt = abstrakt;
	}

	public String getAbstract() {
		return abstrakt;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}

	public boolean isSearchable() {
		return isSearchable;
	}

	public void setPreviousVersion(ProtocolVersion previousVersion) {
		this.previousVersion = previousVersion;
	}

	public ProtocolVersion getPreviousVersion() {
		return previousVersion;
	}
}
