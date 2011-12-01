package net.toxbank.client.io.rdf;

import java.util.List;

import net.toxbank.client.resource.IToxBankResource;

import com.hp.hpl.jena.rdf.model.Model;

public interface IOClass<T extends IToxBankResource> {
	public static final String msg_ResourceWithoutURI = "All %s must have resource URIs.";
	public static final String msg_InvalidURI = "Found %s with an invalid URI: %s";
	public Model toJena(Model toAddTo, T... resources);

	public List<T> fromJena(Model source);
}
