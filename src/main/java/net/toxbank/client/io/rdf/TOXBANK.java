package net.toxbank.client.io.rdf;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class TOXBANK {

	public static final String URI ="http://onto.toxbank.net/api/";

    private static final Resource resource(String local) {
        return ResourceFactory.createResource(URI + local);
    }

    private static final Property property(String local) {
        return ResourceFactory.createProperty(URI, local);
    }

    public static final Resource PROTOCOL = resource("Protocol");
    
//    public static final Property HASATOM = property("hasAtom");
}
