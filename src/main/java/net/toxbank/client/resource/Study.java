package net.toxbank.client.resource;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Study extends AbstractToxBankResource {

	private List<String> keywords;
	private String abstrakt;
	private boolean isSummarySearchable;
	private User author;
	private Assay assay;
	private Organisation owner;
	private Project project;
	private String versionInfo;

	public Study() {}
	
	public Study(URL identifier) {
		this.setResourceURL(identifier);
		this.setOwner(null);
	}

	public void setOwner(Organisation owner) {
		this.owner = owner;
	}

	public Organisation getOwner() {
		return owner;
	}

	public void addKeyword(String keyword) {
		if (keyword == null) return;

		if (keywords == null) keywords = new ArrayList<String>();
		keywords.add(keyword);
	}

	public void removeKeyword(String keyword) {
		if (keyword == null || keywords == null) return;
		
		if (keywords.contains(keyword)) keywords.remove(keyword);
	}

	public List<String> getKeywords() {
		if (keywords == null) return Collections.emptyList();
		return keywords;
	}

	public void setAbstract(String abstrakt) {
		this.abstrakt = abstrakt;
	}

	public String getAbstract() {
		return abstrakt;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getAuthor() {
		return author;
	}

	public void setSummarySearchable(boolean isSummarySearchable) {
		this.isSummarySearchable = isSummarySearchable;
	}

	public boolean isSummarySearchable() {
		return isSummarySearchable;
	}

	public void setAssay(Assay assay) {
		this.assay = assay;
	}

	public Assay getAssay() {
		return assay;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}

	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	public String getVersionInfo() {
		return versionInfo;
	}

}
