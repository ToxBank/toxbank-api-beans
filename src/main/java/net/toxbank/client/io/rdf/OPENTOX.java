package net.toxbank.client.io.rdf;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class OPENTOX {
	 public static final String URI ="http://www.opentox.org/api/1.2#";
	 private static final Resource resource(String local) {
		    return ResourceFactory.createResource(URI + local);
	 }

	 private static final Property property(String local) {
		    return ResourceFactory.createProperty(URI, local);
	 }

	 public static final Resource TASK = resource("Task");
	 public static final Resource ErrorReport = resource("ErrorReport");
	 
	  public static final Property HASSTATUS = property("hasStatus");
	  public static final Property resultURI = property("resultURI");
	  public static final Property error = property("error");
	  public static final Property errorCause = property("errorCause");
	  public static final Property errorCode = property("errorCode");
	  public static final Property errorDetails = property("errorDetails");
	  public static final Property message = property("message");

	  
	  public static final Property percentageCompleted = property("percentageCompleted");
	  
}
