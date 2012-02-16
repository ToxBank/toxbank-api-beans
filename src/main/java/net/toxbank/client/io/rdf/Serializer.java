package net.toxbank.client.io.rdf;

import java.io.OutputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.XSD;

/**
 * Helper class to create RDF/XML and other serializations.
 * 
 * @author egonw
 */
public class Serializer {

	public static void toRDFXML(OutputStream output, Model model) {
		model.setNsPrefix("tb", TOXBANK.URI);
		model.setNsPrefix("dcterms", DCTerms.getURI());
		model.setNsPrefix("xsd", XSD.getURI());
		model.setNsPrefix("ncal",NCAL.URI);
		model.write(output, "RDF/XML-ABBREV");
	}

	public static void toTurtle(OutputStream output, Model model) {
		model.setNsPrefix("tb", TOXBANK.URI);
		model.setNsPrefix("dcterms", DCTerms.getURI());
		model.setNsPrefix("xsd", XSD.getURI());
		model.setNsPrefix("ncal",NCAL.URI);
		model.write(output, "TURTLE");
	}
}
