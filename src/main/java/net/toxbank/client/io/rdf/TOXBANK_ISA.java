package net.toxbank.client.io.rdf;

import com.hp.hpl.jena.rdf.model.*;

public class TOXBANK_ISA {
  public static final String URI = "http://onto.toxbank.net/isa/";
  
  private static final Resource resource(String local) {
    return ResourceFactory.createResource(URI + local);
  }

  private static final Property property(String local) {
    return ResourceFactory.createProperty(URI, local);
  }
  
  public static final Resource INVESTIGATION = resource("Investigation");
  public static final Resource STUDY = resource("Study");
}
