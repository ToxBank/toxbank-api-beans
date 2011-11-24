package net.toxbank.client.io.rdf;

import java.util.List;

import net.toxbank.client.resource.IToxBankResource;

import com.hp.hpl.jena.rdf.model.Model;

public interface IOClass<T extends IToxBankResource> {

	public Model toJena(Model toAddTo, T... resources);

	public List<T> fromJena(Model source);
}
