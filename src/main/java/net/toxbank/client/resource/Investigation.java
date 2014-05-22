package net.toxbank.client.resource;

import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * An investigation as represented in the toxbank repository
 */
public class Investigation extends AbstractToxBankResource {
  private static final long serialVersionUID = -1189656198870429173L;
 
  public static enum DataType {
    noData, unformattedData, ftpData, isaTabData
  }
  
  private String accessionId;
  private String seuratId;
  private String title;
  private String abstrakt;
  
  private Long submissionDate;
  private Long issuedDate;
  private Long lastModifiedDate;
  
  private User owner;
  private Organisation organisation;
  private List<Project> projects = new ArrayList<Project>();
  
  private List<User> authors = new ArrayList<User>();
  private List<Protocol> protocols = new ArrayList<Protocol>();    
  private List<String> keywords = new ArrayList<String>();
  private List<String> downloadUrls = new ArrayList<String>();
  
  private Boolean isPublished = null;
  private Boolean isSearchable = null;
  private String taskUri = null;
  private DataType dataType = null;
  
  private Set<String> otherProtocolNames = new HashSet<String>();
  
  public Investigation() {}
  
  public Investigation(URL identifier) {
    setResourceURL(identifier);
  }

  public Boolean isPublished() {
    return isPublished;
  }

  public void setPublished(Boolean isPublished) {
    this.isPublished = isPublished;
  }

  public String getAccessionId() {
    return accessionId;
  }

  public void setAccessionId(String accessionId) {
    this.accessionId = accessionId;
  }

  public String getSeuratId() {
    return seuratId;
  }

  public void setSeuratId(String seuratId) {
    this.seuratId = seuratId;
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

  public Long getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(Long submissionDate) {
    this.submissionDate = submissionDate;
  }

  public Long getIssuedDate() {
    return issuedDate;
  }

  public void setIssuedDate(Long issuedDate) {
    this.issuedDate = issuedDate;
  }

  public Long getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Long lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Boolean isSearchable() {
    return isSearchable;
  }
  
  public void setSearchable(Boolean searchable) {
    this.isSearchable = searchable;
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

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    if (projects == null) {
      projects = new ArrayList<Project>();
    }
    this.projects = projects;
  }
  
  public List<String> getKeywords() {
    return keywords;
  }
  
  public void setKeywords(List<String> keywords) {
    if (keywords == null) {
      keywords = new ArrayList<String>();
    }
    this.keywords = keywords;
  }
  
  public List<String> getDownloadUrls() {
    return downloadUrls;
  }
  
  public void setDownloadUrls(List<String> downloadUrls) {
    if (downloadUrls == null) {
      downloadUrls = new ArrayList<String>();
    }
    this.downloadUrls = downloadUrls;
  }
  
  public List<String> getFtpFilenames() {
    List<String> filenames = new ArrayList<String>();
    for (String url : downloadUrls) {
      int ftpIdx = url.indexOf("/files/");
      if (ftpIdx > 0) {
        String filename = url.substring(ftpIdx+7);
        if (filename.length() > 0) {
          try {
            filenames.add(URLDecoder.decode(filename, "UTF-8"));
          }
          catch (Exception e) { 
            e.printStackTrace();
          }
        }
      }
    }
    return filenames;
  }
  
  public void setTaskUri(String taskUri) {
    this.taskUri = taskUri;
  }
  
  public String getTaskUri() {
    return this.taskUri;
  }
  
  public void setDataType(DataType dataType) {
    this.dataType = dataType;
  }
  
  public DataType getDataType() {
    return dataType;
  }
  
  public Set<String> getOtherProtocolNames() {
    return otherProtocolNames;
  }
  
  public void addOtherProtocolName(String name) {
    otherProtocolNames.add(name);
  }
  
  public void removeOtherProtocolName(String name) {
    otherProtocolNames.remove(name);
  }
}
