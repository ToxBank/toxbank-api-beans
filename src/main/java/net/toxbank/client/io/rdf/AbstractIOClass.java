package net.toxbank.client.io.rdf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.IToxBankResource;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public abstract class AbstractIOClass<T extends IToxBankResource> implements IOClass<T> {

	public List<T> fromJena(Model source, ExtendedIterator<Resource> iter) {
		if (source == null) return Collections.emptyList();

		if (!iter.hasNext()) return Collections.emptyList();

		List<T> items = new ArrayList<T>();
		while (iter.hasNext()) {
			T item = fromJena(source,iter.next());
			items.add(item);
		}

		return items;
	}

}
