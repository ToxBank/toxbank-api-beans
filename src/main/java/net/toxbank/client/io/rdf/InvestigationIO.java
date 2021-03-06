package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.toxbank.client.resource.*;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;

public class InvestigationIO extends AbstractIOClass<Investigation> {
  private OrganisationIO organisationIO = new OrganisationIO();
  private UserIO userIO = new UserIO();

  private static Pattern fullInvestigationUrlPattern = Pattern.compile("(.*)/([0-9\\-a-f]+)/([A-Z0-9]+)");
  
  @Override
  public List<Investigation> fromJena(Model source) {
    if (source == null) return Collections.emptyList();
    return fromJena(source, source.listResourcesWithProperty(RDF.type, TOXBANK_ISA.INVESTIGATION));
  }

  @Override
  public Investigation fromJena(Model source, Resource res)
      throws IllegalArgumentException {
    Investigation investigation = new Investigation();
    try {
      String resourceUri = res.getURI();
      if (resourceUri.endsWith("/")) {
        resourceUri = resourceUri.substring(0, resourceUri.length()-1);
      }
      Matcher matcher = fullInvestigationUrlPattern.matcher(resourceUri);
      if (matcher.matches()) {
        resourceUri = matcher.group(1) + "/" + matcher.group(2);
        investigation.setSeuratId("SEURAT-Investigation-" + matcher.group(2));
      }
      investigation.setResourceURL(new URL(resourceUri));
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(String.format(msg_InvalidURI, "investigation", res.getURI()));
    }
    
    investigation.setPublished(getBoolean(res, TOXBANK.ISPUBLISHED));
    investigation.setSearchable(getBoolean(res, TOXBANK.ISSUMMARYSEARCHABLE));
    investigation.setAccessionId(getString(res, TOXBANK_ISA.HAS_ACCESSION_ID));
    investigation.setTitle(getString(res, DCTerms.title));
    investigation.setAbstract(getString(res, DCTerms.abstract_));
    investigation.setLastModifiedDate(getTimestamp(res, DCTerms.modified));
    investigation.setIssuedDate(getTimestamp(res, DCTerms.issued));
    investigation.setSubmissionDate(getTimestamp(res, DCTerms.created));
    String dataTypeString = getString(res, TOXBANK.HASINVTYPE);
    if (dataTypeString == null) {
      investigation.setDataType(Investigation.DataType.isaTabData);
    }
    else {
      investigation.setDataType(Investigation.DataType.valueOf(dataTypeString));
    }
    
    for (StmtIterator iter = res.listProperties(DCTerms.license); iter.hasNext(); ) {
      investigation.addLicense(iter.next().getString());
    }
    
    List<Project> projects = new ArrayList<Project>();
    for (StmtIterator iter = res.listProperties(TOXBANK.HASPROJECT); iter.hasNext(); ) {
      Resource projectRes = iter.next().getResource();
      String uri = null;
      try {
        uri = projectRes.getURI();
        projects.add(new Project(new URL(uri)));
      } catch (MalformedURLException e) {
        throw new IllegalArgumentException(String.format(msg_InvalidURI,"an investigation project", uri));
      }
    }    
    investigation.setProjects(projects);
    
    List<String> downloadUrls = new ArrayList<String>();
    for (StmtIterator iter = res.listProperties(TOXBANK.HASDOWNLOAD); iter.hasNext(); ) {
      Resource urlRes = iter.next().getResource();
      downloadUrls.add(urlRes.getURI());
    }
    investigation.setDownloadUrls(downloadUrls);
    
    if (res.getProperty(TOXBANK.HASORGANISATION) != null) {
      Organisation org  = organisationIO.fromJena(source,res.getProperty(TOXBANK.HASORGANISATION).getResource());
      investigation.setOrganisation(org);
    }
    
    if (res.getProperty(TOXBANK.HASOWNER) != null) {
      String uri = null;
      try {
        uri = res.getProperty(TOXBANK.HASOWNER).getResource().getURI();   
        User owner = new User(new URL(uri));        
        investigation.setOwner(owner);
      } catch (MalformedURLException e) {
        throw new IllegalArgumentException(String.format(msg_InvalidURI,"an investigation owner", uri));
      }
    }

    if (res.getProperty(TOXBANK.HAS_SUB_TASK) != null) {
      Resource subTaskResource = res.getProperty(TOXBANK.HAS_SUB_TASK).getResource();
      investigation.setTaskUri(subTaskResource.getURI());
    }
    
    List<User> authors = new ArrayList<User>();
    for (StmtIterator iter = res.listProperties(TOXBANK.HASAUTHOR); iter.hasNext(); ) {
      Resource authorRes = iter.next().getResource();
      String uri = null;
      try {
        uri = authorRes.getURI();
        User author = new User(new URL(uri));        
        authors.add(author);
      } catch (MalformedURLException e) {
        throw new IllegalArgumentException(String.format(msg_InvalidURI,"an investigation author", uri));
      }
    }
    investigation.setAuthors(authors);
    
    List<String> keywords = new ArrayList<String>();
    for (StmtIterator iter = res.listProperties(TOXBANK.HASKEYWORD); iter.hasNext(); ) {
      Statement stmt = iter.next();
      try {
        keywords.add(stmt.getString());
      }
      catch (Exception e) {
        keywords.add(stmt.getResource().getURI());
      }
    }
    investigation.setKeywords(keywords);
    
    List<Protocol> protocols = new ArrayList<Protocol>();
    for (StmtIterator iter = res.listProperties(TOXBANK_ISA.HAS_STUDY); iter.hasNext(); ) {
      Resource studyRes = iter.next().getResource();
      for (StmtIterator studyIter = studyRes.listProperties(TOXBANK_ISA.HAS_PROTOCOL); studyIter.hasNext(); ) {
        Resource protocolRes = studyIter.next().getResource();
        if (hasType(protocolRes, TOXBANK.PROTOCOL)) {
          String uri = null;
          try {
            uri = protocolRes.getURI();   
            Protocol protocol = new Protocol(new URL(uri));        
            protocols.add(protocol);
            
            for (StmtIterator protoIter = protocolRes.listProperties(RDFS.label); protoIter.hasNext(); ) {
              String label = protoIter.next().getString();
              if (label != null && label.trim().length() > 0) {
                protocol.addInvestigationLabel(label);
              }
            }
            
          } catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format(msg_InvalidURI,"a investigation owner", uri));
          } 
        }
        else {
          for (StmtIterator protoIter = protocolRes.listProperties(RDFS.label); protoIter.hasNext(); ) {
            String label = protoIter.next().getString();
            if (label != null && label.trim().length() > 0) {
              investigation.addOtherProtocolName(label);
            }
          }
        }
      }
    }
    investigation.setProtocols(protocols);
    
    return investigation;
  }

  // this only partially represents an ISA invenstigation RDF document
  @Override
  public Resource objectToJena(Model toAddTo, Investigation investigation)
      throws IllegalArgumentException {
    if (investigation.getResourceURL() == null) {
      throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "investigations"));
    }
    Resource res = toAddTo.createResource(investigation.getResourceURL().toString());
    
    toAddTo.add(res, RDF.type, TOXBANK_ISA.INVESTIGATION);
    addString(res, TOXBANK_ISA.HAS_ACCESSION_ID, investigation.getAccessionId());
    addString(res, DCTerms.title, investigation.getTitle());
    addString(res, DCTerms.abstract_, investigation.getAbstract());
    addTimestamp(res, DCTerms.created, investigation.getSubmissionDate());
    addTimestamp(res, DCTerms.modified, investigation.getLastModifiedDate());
    addTimestamp(res, DCTerms.issued, investigation.getIssuedDate());

    if (investigation.isPublished() != null)
      res.addLiteral(TOXBANK.ISPUBLISHED, investigation.isPublished());

    if (investigation.isSearchable() != null)
      res.addLiteral(TOXBANK.ISSUMMARYSEARCHABLE, investigation.isSearchable());

    for (Project project : investigation.getProjects()) {
      Resource projectRes = toAddTo.createResource(
          project.getResourceURL().toString()
          );
      res.addProperty(TOXBANK.HASPROJECT, projectRes);
    }
    
    if (investigation.getOrganisation() != null) {
      if (investigation.getOrganisation().getResourceURL()==null)
        throw new IllegalArgumentException(String.format(msg_InvalidURI, "organisation",res.getURI()));       
      Resource org = organisationIO.objectToJena(toAddTo, investigation.getOrganisation());
      res.addProperty(TOXBANK.HASORGANISATION,org);
    }
    if (investigation.getOwner() != null) {
      if (investigation.getOwner().getResourceURL()==null)
        throw new IllegalArgumentException(String.format(msg_InvalidURI, "investigation owner",res.getURI()));      
      Resource ownerRes = userIO.objectToJena(toAddTo, investigation.getOwner());
      res.addProperty(TOXBANK.HASOWNER, ownerRes);
    }

    for (User author : investigation.getAuthors()) {
      Resource authorRes = toAddTo.createResource(
          author.getResourceURL().toString()
        );
        res.addProperty(TOXBANK_ISA.HAS_OWNER, authorRes);
    }

    return res;
  }

}
