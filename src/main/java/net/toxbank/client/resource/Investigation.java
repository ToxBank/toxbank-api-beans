package net.toxbank.client.resource;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * An investigation as represented in the toxbank repository
 */
public class Investigation extends AbstractToxBankResource {
  private static final long serialVersionUID = -1189656198870429173L;
 
  private String accessionId;
  private String title;
  private String abstrakt;
  
  private Long createdDate;
  private Long issuedDate;
  
  private User owner;
  private Organisation organisation;
  private Project project;
  
  private List<User> authors;
  private List<Protocol> protocols;
    
  public Investigation() {}
  
  public Investigation(URL identifier) {
    setResourceURL(identifier);
  }

  public String getAccessionId() {
    return accessionId;
  }

  public void setAccessionId(String accessionId) {
    this.accessionId = accessionId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAbstract() {
    return abstrakt;
  }

  public void setAbstract(String abstrakt) {
    this.abstrakt = abstrakt;
  }

  public Long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Long createdDate) {
    this.createdDate = createdDate;
  }

  public Long getIssuedDate() {
    return issuedDate;
  }

  public void setIssuedDate(Long issuedDate) {
    this.issuedDate = issuedDate;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public List<User> getAuthors() {
    return authors;
  }

  public void setAuthors(List<User> authors) {
    if (authors == null) {
      authors = new ArrayList<User>();
    }
    this.authors = authors;
  }

  public List<Protocol> getProtocols() {
    return protocols;
  }

  public void setProtocols(List<Protocol> protocols) {
    if (protocols == null) {
      protocols = new ArrayList<Protocol>();
    }
    this.protocols = protocols;
  }
  
  public Organisation getOrganisation() {
    return organisation;
  }

  public void setOrganisation(Organisation organisation) {
    this.organisation = organisation;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }
}
