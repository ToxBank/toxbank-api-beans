package net.toxbank.client.io.rdf;

import java.util.List;

import net.toxbank.client.resource.IToxBankResource;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

public interface IOClass<T extends IToxBankResource> {
	public static final String msg_ResourceWithoutURI = "All %s must have resource URIs.";
	public static final String msg_InvalidURI = "Found %s with an invalid URI: %s";
	public Model toJena(Model toAddTo, T... resources) throws IllegalArgumentException;

	public List<T> fromJena(Model source);
	public T fromJena(Model source, Resource res) throws IllegalArgumentException;
	//public List<T> fromJena(Model source, ExtendedIterator<Resource>  iter);
}
