package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Account;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;

public class AccountIO implements IOClass<Account> {

	public Model toJena(Model toAddTo, Account... accounts) {
		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (accounts == null) return toAddTo;

		for (Account account : accounts) {
			if (account.getResourceURL() == null) {
				throw new IllegalArgumentException("All protocols must have resource URIs.");
			}
			Resource res = toAddTo.createResource(account.getResourceURL().toString());
			toAddTo.add(res, RDF.type, FOAF.OnlineAccount);
			if (account.getService() != null)
				res.addLiteral(FOAF.accountServiceHomepage, account.getService());
			if (account.getAccountName() != null)
				res.addLiteral(FOAF.accountName, account.getAccountName());
		}
		return toAddTo;
	}

	public List<Account> fromJena(Model source) {
		if (source == null) return Collections.emptyList();

		ResIterator iter = source.listResourcesWithProperty(RDF.type, FOAF.OnlineAccount);
		if (!iter.hasNext()) return Collections.emptyList();

		List<Account> accounts = new ArrayList<Account>();
		while (iter.hasNext()) {
			Account account = new Account();
			Resource res = iter.next();
			System.out.println(res);
			try {
				account.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(
					"Found resource with an invalid URI:" + res.getURI()
				);
			}
			if (res.getProperty(FOAF.accountName) != null)
				account.setAccountName(res.getProperty(FOAF.accountName).getString());
			if (res.getProperty(FOAF.accountServiceHomepage) != null)
				account.setService(res.getProperty(FOAF.accountServiceHomepage).getString());
			accounts.add(account);
		}

		return accounts;
	}

}
