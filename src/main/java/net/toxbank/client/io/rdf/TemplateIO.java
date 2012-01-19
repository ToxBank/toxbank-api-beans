package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.resource.Template;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class TemplateIO extends AbstractIOClass<Template> {

	@Override
	public Resource objectToJena(Model toAddTo, Template template)
			throws IllegalArgumentException {

			if (template.getResourceURL() == null) {
				throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "templates"));
			}
			Resource res = toAddTo.createResource(template.getResourceURL().toString());
			toAddTo.add(res, RDF.type, TOXBANK.TEMPLATE);
		return res;
	}

	public List<Template> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, TOXBANK.TEMPLATE));
	}
	
	@Override
	public Template fromJena(Model source, Resource res)
			throws IllegalArgumentException {
			Template template = new Template();
			try {
				template.setResourceURL(
					new URL(res.getURI())
				);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(String.format(msg_InvalidURI,"a template",res.getURI())
				);
			}

		return template;
	}

}
