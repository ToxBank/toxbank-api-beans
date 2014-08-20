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
  public static final Resource CONTACT = resource("Contact");
  public static final Resource ASSAY = resource("Assay");
  public static final Resource DATA = resource("Data");
  public static final Resource MATERIAL = resource("Material");
  public static final Resource PROTOCOL_APPLICATION = resource("ProtocolApplication");
    
  public static final Resource PROCESSING = resource("Processing");
  public static final Resource DATA_PROCESSING = resource("DataProcessing");
  public static final Resource MATERIAL_PROCESSING = resource("MaterialProcessing");
  
  public static final Resource DATA_ACQUISITION = resource("DataAcquisition");
  
  public static final Resource NODE = resource("Node");
  public static final Resource DATA_NODE = resource("DataNode");
  public static final Resource MATERIAL_NODE = resource("MaterialNode");
  
  public static final Property HAS_ACCESSION_ID = property("hasAccessionID");
  public static final Property HAS_STUDY = property("hasStudy");
  public static final Property HAS_OWNER = property("hasOwner");
  
  public static final Property HAS_ASSAY = property("hasAssay");
  public static final Property HAS_PROTOCOL = property("hasProtocol");
  public static final Property APPLIES_PROTOCOL = property("appliesProtocol");  
  public static final Property HAS_PROTOCOL_APPLICATION = property("hasProtocolApplication");
  public static final Property HAS_PARAMETER_VALUE = property("hasParameterValue");
  public static final Property HAS_INPUT_NODE = property("hasInputNode");
  public static final Property HAS_OUTPUT_NODE = property("hasOutputNode");

}
