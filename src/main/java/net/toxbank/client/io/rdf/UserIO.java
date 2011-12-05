package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Account;
import net.toxbank.client.resource.User;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;

public class UserIO implements IOClass<User> {
	private AccountIO accountIO = new AccountIO();

	public Model toJena(Model toAddTo, User... users) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (users == null) return toAddTo;

		for (User user : users) {
			if (user.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI,"user"));
			}
			Resource res = toAddTo.createResource(user.getResourceURL().toString());
			toAddTo.add(res, RDF.type, FOAF.Person);
			if (user.getTitle() != null)
				res.addLiteral(DCTerms.title, user.getTitle());
			if (user.getHomepage() != null)
				res.addLiteral(FOAF.homepage, user.getHomepage());
			if (user.getWeblog() != null)
				res.addLiteral(FOAF.weblog, user.getWeblog());
			if (user.getFirstname() != null)
				res.addLiteral(FOAF.givenname, user.getFirstname());
			if (user.getLastname() != null)
				res.addLiteral(FOAF.family_name, user.getLastname());			
			if (user.getAccounts() != null) {
				for (Account account : user.getAccounts()) {
					accountIO.toJena(toAddTo, account);
				}
			}
		}
		return toAddTo;
	}

	public List<User> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, FOAF.Person);
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
				throw new IllegalArgumentException(String.format(msg_InvalidURI,res.getURI()));
			}
			if (res.getProperty(DCTerms.title) != null)
				user.setTitle(res.getProperty(DCTerms.title).getString());
			if (res.getProperty(FOAF.givenname) != null)
				user.setFirstname(res.getProperty(FOAF.givenname).getString());
			if (res.getProperty(FOAF.family_name) != null)
				user.setLastname(res.getProperty(FOAF.family_name).getString());			
			if (res.getProperty(FOAF.weblog) != null)
				try {
					user.setWeblog(new URL(res.getProperty(FOAF.weblog).getString()));
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(
						String.format(
							msg_InvalidURI,
							res.getProperty(FOAF.weblog).getString()
						)
					);
				}
				if (res.getProperty(FOAF.homepage) != null)
					try {
						user.setHomepage(new URL(res.getProperty(FOAF.homepage).getString()));
					} catch (MalformedURLException e) {
						throw new IllegalArgumentException(
							String.format(
								msg_InvalidURI,
								res.getProperty(FOAF.homepage).getString()
							)
						);
					}
			List<Account> accounts = accountIO.fromJena(source);
			for (Account account : accounts) {
				// Important: assuming here that the account uri is an extension
				// of the user URI
				if (account.getResourceURL().toString().startsWith(
						user.getResourceURL().toString()
					)) {
					user.addAccount(account);
				}
			}
			users.add(user);
		}

		return users;
	}

}
