package net.toxbank.client.io.rdf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.IToxBankResource;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public abstract class AbstractIOClass<T extends IToxBankResource> implements IOClass<T> {

	/**
	 * To handle {@link listResourcesWithProperty}
	 * @param source
	 * @param iter
	 * @return
	 */
	protected List<T> fromJena(Model source, ResIterator iter) {
		if (source == null) return Collections.emptyList();

		if (!iter.hasNext()) return Collections.emptyList();

		List<T> items = new ArrayList<T>();
		while (iter.hasNext()) {
			T item = fromJena(source,iter.next());
			items.add(item);
		}

		return items;
	}
	
	/**
	 * To handle {@link listObjectsOfProperty}
	 * @param source
	 * @param iter
	 * @return
	 */
	protected List<T> fromJena(Model source, NodeIterator iter) {
		if (source == null) return Collections.emptyList();

		if (!iter.hasNext()) return Collections.emptyList();

		List<T> items = new ArrayList<T>();
		while (iter.hasNext()) {
			RDFNode node = iter.next();
			if (node.isResource())  {
				T item = fromJena(source,(Resource) node);
				items.add(item);
			}
		}

		return items;
	}	

}