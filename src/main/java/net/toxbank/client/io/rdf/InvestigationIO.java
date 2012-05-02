/**
 * Copyright 2011 Leadscope, Inc. All rights reserved.
 * LEADSCOPE PROPRIETARY and CONFIDENTIAL. Use is subject to license terms.
 */
package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import net.toxbank.client.resource.Investigation;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.RDF;

public class InvestigationIO extends AbstractIOClass<Investigation> {  
  @Override
  public List<Investigation> fromJena(Model source) {
    if (source == null) return Collections.emptyList();
    return fromJena(source, source.listResourcesWithProperty(RDF.type, TOXBANK.INVESTIGATION));
  }

  @Override
  public Investigation fromJena(Model source, Resource res)
      throws IllegalArgumentException {
    Investigation investigation = new Investigation();
    try {
      investigation.setResourceURL(new URL(res.getURI())
      );
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException(String.format(msg_InvalidURI, "investigation", res.getURI()));
    }
    return investigation;
  }

  @Override
  public Resource objectToJena(Model toAddTo, Investigation investigation)
      throws IllegalArgumentException {
    if (investigation.getResourceURL() == null) {
      throw new IllegalArgumentException(String.format(msg_ResourceWithoutURI, "investigations"));
    }
    Resource res = toAddTo.createResource(investigation.getResourceURL().toString());
    toAddTo.add(res, RDF.type, TOXBANK.INVESTIGATION);
    return res;
  }

}
