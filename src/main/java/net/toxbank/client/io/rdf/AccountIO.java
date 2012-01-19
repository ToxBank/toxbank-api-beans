package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Account;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;

public class AccountIO extends AbstractIOClass<Account> {

	@Override
	public Resource objectToJena(Model toAddTo, Account account) throws  IllegalArgumentException  {
		if (account.getResourceURL() == null) {
			throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "accounts"));
		}
		Resource res = toAddTo.createResource(account.getResourceURL().toString());
		toAddTo.add(res, RDF.type, FOAF.OnlineAccount);
		if (account.getService() != null)
			res.addLiteral(FOAF.accountServiceHomepage, account.getService());
		if (account.getAccountName() != null)
			res.addLiteral(FOAF.accountName, account.getAccountName());
		return res;
	}
	
	public List<Account> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, FOAF.OnlineAccount));
	}
	
	public Account fromJena(Model source, Resource res) throws IllegalArgumentException {
			Account account = new Account();
			try {
				account.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"an account",res.getURI())
				);
			}
			if (res.getProperty(FOAF.accountName) != null)
				account.setAccountName(res.getProperty(FOAF.accountName).getString());
			if (res.getProperty(FOAF.accountServiceHomepage) != null)
				account.setService(res.getProperty(FOAF.accountServiceHomepage).getString());

		return account;
	}

}
