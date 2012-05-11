package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import net.toxbank.client.resource.*;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class InvestigationIO extends AbstractIOClass<Investigation> {
  private OrganisationIO organisationIO = new OrganisationIO();
  private ProjectIO projectIO = new ProjectIO();
  private UserIO userIO = new UserIO();

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
      investigation.setResourceURL(new URL(res.getURI()));
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(String.format(msg_InvalidURI, "investigation", res.getURI()));
    }
    
    investigation.setAccessionId(getString(res, TOXBANK_ISA.HAS_ACCESSION_ID));
    investigation.setTitle(getString(res, DCTerms.title));
    investigation.setAbstract(getString(res, DCTerms.abstract_));
    investigation.setLastModifiedDate(getTimestamp(res, DCTerms.issued));
    investigation.setSubmissionDate(getTimestamp(res, DCTerms.created));
    
    if (res.getProperty(TOXBANK.HASPROJECT) != null) {
      Project project = projectIO.fromJena(source,res.getProperty(TOXBANK.HASPROJECT).getResource());
      investigation.setProject(project);
    } 
      
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
        throw new IllegalArgumentException(String.format(msg_InvalidURI,"a investigation owner", uri));
      }
    }

    /** this will get changed once ISA Tab supports the specialized comment field for authors */
    List<User> authors = new ArrayList<User>();
    for (StmtIterator iter = res.listProperties(TOXBANK_ISA.HAS_OWNER); iter.hasNext(); ) {
      Resource authorRes = iter.next().getResource();
      if (hasType(authorRes, TOXBANK.USER)) {
        String uri = null;
        try {
          uri = res.getProperty(TOXBANK.HASOWNER).getResource().getURI();   
          User author = new User(new URL(uri));        
          authors.add(author);
        } catch (MalformedURLException e) {
          throw new IllegalArgumentException(String.format(msg_InvalidURI,"a investigation owner", uri));
        }
      }
    }
    investigation.setAuthors(authors);
    
    List<String> keywords = new ArrayList<String>();
    for (StmtIterator iter = res.listProperties(TOXBANK.HASKEYWORD); iter.hasNext(); ) {
      keywords.add(iter.next().getString());
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
          } catch (MalformedURLException e) {
            throw new IllegalArgumentException(String.format(msg_InvalidURI,"a investigation owner", uri));
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
    addTimestamp(res, DCTerms.issued, investigation.getLastModifiedDate());

    if (investigation.getProject() != null) {
      if (investigation.getProject().getResourceURL()==null)
        throw new IllegalArgumentException(String.format(msg_InvalidURI, "project",res.getURI()));        
      Resource project = projectIO.objectToJena(toAddTo, investigation.getProject());
      res.addProperty(TOXBANK.HASPROJECT,project);
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
