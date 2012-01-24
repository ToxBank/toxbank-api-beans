package net.toxbank.client.io.rdf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.IToxBankResource;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public abstract class AbstractIOClass<T extends IToxBankResource> implements IOClass<T> {

	
	public Model toJena(Model toAddTo, T... resources) throws IllegalArgumentException {

		if (toAddTo == null) toAddTo = ModelFactory.createDefaultModel();
		if (resources == null) return toAddTo;

		for (T resource : resources) {
			if (resource.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, resource.getClass().getName()));
			}
			objectToJena(toAddTo,resource);
		}
		return toAddTo;
	}	
	public abstract Resource objectToJena(Model toAddTo, T object) throws IllegalArgumentException;
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
	/**
	 * to avoid "cannot encode (char)  in context XML" exception when writing to rdf/xml
	 * @return
	 */
	protected String cleanLiteral(String m) {
		for (int i=1; i < 7;i ++) {
			if (m.indexOf((char)i)>=0) m=m.replace(((char)i),' ');
		}
		return m;
	}
}
