package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Document;
import net.toxbank.client.resource.Organisation;
import net.toxbank.client.resource.Project;
import net.toxbank.client.resource.Protocol;
import net.toxbank.client.resource.Template;
import net.toxbank.client.resource.ToxBankResourceSet;
import net.toxbank.client.resource.User;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class ProtocolIO implements IOClass<Protocol> {
	private OrganisationIO organisationIO = new OrganisationIO();
	private OrganisationIO projectIO = new OrganisationIO();

	public Model toJena(Model toAddTo, Protocol... protocols) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (protocols == null) return toAddTo;

		for (Protocol protocol : protocols) {
			if (protocol.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "protocols"));
			}
			Resource res = toAddTo.createResource(protocol.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.PROTOCOL);
			if (protocol.getTitle() != null)
				res.addLiteral(DCTerms.title, protocol.getTitle());
			if (protocol.getIdentifier() != null)
				res.addLiteral(DCTerms.identifier, protocol.getIdentifier());
			if (protocol.getAbstract() != null)
				res.addLiteral(TOXBANK.HASABSTRACT, protocol.getAbstract());
			List<String> keywords = protocol.getKeywords();
			if (keywords != null) {
				for (String keyword : keywords)
					res.addLiteral(TOXBANK.HASKEYWORD, keyword);
			}
			if (protocol.getProject() != null) {
				if (protocol.getProject().getResourceURL()==null)
					throw new IllegalArgumentException(String.format(msg_InvalidURI, "project",res.getURI()));				
				res.addProperty(TOXBANK.HASPROJECT,
					toAddTo.createResource(
						protocol.getProject().getResourceURL().toString()
					)
				);
				projectIO.toJena(toAddTo, protocol.getOrganisation());
			}			
			if (protocol.getOrganisation() != null) {
				if (protocol.getOrganisation().getResourceURL()==null)
					throw new IllegalArgumentException(String.format(msg_InvalidURI, "organisation",res.getURI()));				
				res.addProperty(TOXBANK.HASORGANISATION,
					toAddTo.createResource(
						protocol.getOrganisation().getResourceURL().toString()
					)
				);
				organisationIO.toJena(toAddTo, protocol.getOrganisation());
			}

			if (protocol.getOwner() != null) {
				if (protocol.getOwner().getResourceURL()==null)
					throw new IllegalArgumentException(String.format(msg_InvalidURI, "protocol owner",res.getURI()));
					
				Resource ownerRes = toAddTo.createResource(
					protocol.getOwner().getResourceURL().toString()
				);
				res.addProperty(TOXBANK.HASOWNER, ownerRes);
			}
			ToxBankResourceSet<User> authors = protocol.getAuthors();
			if (authors != null)
				for (User author : authors) {
					if (author.getResourceURL()==null)
						throw new IllegalArgumentException(String.format(msg_InvalidURI, "author",res.getURI()));
					res.addProperty(TOXBANK.HASAUTHOR,
						toAddTo.createResource(
							author.getResourceURL().toString()
						)
					);
				}
			
			if (protocol.getDocument() != null) {
				if (protocol.getDocument().getResourceURL()==null)
					throw new IllegalArgumentException(String.format(msg_InvalidURI, "document",res.getURI()));
					
				Resource documentRes = toAddTo.createResource(
					protocol.getDocument().getResourceURL().toString()
				);
				res.addProperty(TOXBANK.HASDOCUMENT, documentRes);
			}
			
			if (protocol.getDataTemplate() != null) {
				if (protocol.getDataTemplate().getResourceURL()==null)
					throw new IllegalArgumentException(String.format(msg_InvalidURI, "data template",res.getURI()));
					
				Resource templateRes = toAddTo.createResource(
					protocol.getDataTemplate().getResourceURL().toString()
				);
				res.addProperty(TOXBANK.HASTEMPLATE, templateRes);
			}			
		}
		return toAddTo;
	}

	public List<Protocol> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.PROTOCOL);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Protocol> protocols = new ArrayList<Protocol>();
		while (iter.hasNext()) {
			Protocol protocol = new Protocol();
			Resource res = iter.next();
			try {
				protocol.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI, "protocol",res.getURI()));
			}
			if (res.getProperty(DCTerms.title) != null)
				protocol.setTitle(res.getProperty(DCTerms.title).getString());
			if (res.getProperty(DCTerms.identifier) != null)
				protocol.setIdentifier(res.getProperty(DCTerms.identifier).getString());
			if (res.getProperty(TOXBANK.HASABSTRACT) != null)
				protocol.setAbstract(res.getProperty(TOXBANK.HASABSTRACT).getString());
			StmtIterator keywords = res.listProperties(TOXBANK.HASKEYWORD);
			while (keywords.hasNext()) {
				protocol.addKeyword(keywords.next().getString());
			}
			
			String uri = null;
			StmtIterator authors = res.listProperties(TOXBANK.HASAUTHOR);
			while (authors.hasNext()) {
				Resource authorRes = authors.next().getResource();
				User author = new User();
				try {
					uri = authorRes.getURI();
					author.setResourceURL(new URL(uri));
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(String.format(msg_InvalidURI,"an author",uri));
				}
				protocol.addAuthor(author);
			}
			if (res.getProperty(TOXBANK.HASPROJECT) != null)
				try {
					Project project = new Project();
					uri = res.getProperty(TOXBANK.HASPROJECT).getResource().getURI();
					project.setResourceURL(new URL(uri));
					protocol.setProject(project);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(String.format(msg_InvalidURI,"a project",uri));
				}
				
			if (res.getProperty(TOXBANK.HASORGANISATION) != null)
				try {
					Organisation org = new Organisation();
					uri = res.getProperty(TOXBANK.HASORGANISATION).getResource().getURI();
					org.setResourceURL(new URL(uri));
					protocol.setOrganisation(org);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(String.format(msg_InvalidURI,"an organisation",uri));
				}
			
		
			if (res.getProperty(TOXBANK.HASOWNER) != null)
				try {
					uri = res.getProperty(TOXBANK.HASOWNER).getResource().getURI();		
					User author = new User();
					
					author.setResourceURL(
						new URL(uri)
					);
					protocol.setOwner(author);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(String.format(msg_InvalidURI,"a protocol owner",uri));
				}
				
 
			if (res.getProperty(TOXBANK.HASDOCUMENT) != null)
				try {
					uri = res.getProperty(TOXBANK.HASDOCUMENT).getResource().getURI();		
					Document document = new Document(new URL(uri));
					
					protocol.setDocument(document);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(String.format(msg_InvalidURI,"a document",uri));
				}	
				
			if (res.getProperty(TOXBANK.HASTEMPLATE) != null)
				try {
					uri = res.getProperty(TOXBANK.HASTEMPLATE).getResource().getURI();		
					Template dataTemplate = new Template(new URL(uri));
					
					protocol.setDataTemplate(dataTemplate);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(String.format(msg_InvalidURI,"data template",uri));
				}						 
	
			protocols.add(protocol);
		}

		return protocols;
	}

}
