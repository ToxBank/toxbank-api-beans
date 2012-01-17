package net.toxbank.client.io.rdf;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

/**
 * Java API to the ToxBank API OWL.
 */
public class TOXBANK {

	public static final String URI ="http://onto.toxbank.net/api/";

    private static final Resource resource(String local) {
        return ResourceFactory.createResource(URI + local);
    }

    private static final Property property(String local) {
        return ResourceFactory.createProperty(URI, local);
    }

    public static final Resource ORGANIZATION = resource("Organization");
    public static final Resource PARAMETER = resource("Parameter");
    public static final Resource PROJECT = resource("Project");
    public static final Resource PROTOCOL = resource("Protocol");
    public static final Resource STUDY = resource("Study");
    public static final Resource USER = resource("User");
    public static final Resource ALERT = resource("Alert");
    public static final Resource ASSAY = resource("Assay");
    public static final Resource TEMPLATE = resource("Template");
    public static final Resource DOCUMENT = resource("Document");
    
    public static final Property HASABSTRACT = property("hasAbstract");
    public static final Property HASAUTHOR = property("hasAuthor");
    public static final Property HASKEYWORD = property("hasKeyword");
    public static final Property HASOWNER = property("hasOwner");
    public static final Property HASTEMPLATE = property("hasTemplate");
    public static final Property ISSUMMARYSEARCHABLE = property("isSummarySearchable");
    public static final Property HASPROJECT = property("hasProject");
    public static final Property HASORGANISATION = property("hasOrganisation");
    public static final Property HASDOCUMENT = property("hasDocument");
    public static final Property HASVERSIONINFO = property("hasVersionInfo");
    public static final Property HASSTATUS = property("hasStatus");
    public static final Property HASPROJECTMEMBER = property("hasProjectMember");
    public static final Property HASMEMBER = property("hasMember");
}
