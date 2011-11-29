package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.User;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class UserIO implements IOClass<User> {

	public Model toJena(Model toAddTo, User... users) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (users == null) return toAddTo;

		for (User user : users) {
			if (user.getResourceURL() == null) {
				throw new IllegalArgumentException("All protocols must have resource URIs.");
			}
			Resource res = toAddTo.createResource(user.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.PROTOCOL);
			if (user.getTitle() != null)
				res.addLiteral(DCTerms.title, user.getTitle());
		}
		return toAddTo;
	}

	public List<User> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, TOXBANK.PROTOCOL);
		if (!iter.hasNext()) return Collections.emptyList();

		List<User> users = new ArrayList<User>();
		while (iter.hasNext()) {
			User user = new User();
			Resource res = iter.next();
			System.out.println(res);
			try {
				user.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(
					"Found resource with an invalid URI:" + res.getURI()
				);
			}
			if (res.getProperty(DCTerms.title) != null)
				user.setTitle(res.getProperty(DCTerms.title).getString());
			users.add(user);
		}

		return users;
	}

}
