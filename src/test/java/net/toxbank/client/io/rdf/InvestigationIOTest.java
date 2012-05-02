/**
 * Copyright 2011 Leadscope, Inc. All rights reserved.
 * LEADSCOPE PROPRIETARY and CONFIDENTIAL. Use is subject to license terms.
 */
package net.toxbank.client.io.rdf;

import java.io.*;
import java.util.List;

import junit.framework.TestCase;
import net.toxbank.client.resource.Investigation;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class InvestigationIOTest extends AbstractIOClassTest<Investigation> {
  public InvestigationIO getIOClass() {
    return new InvestigationIO();
  }

  // parsing directly from the class resource was encountering an unexpected
  // eof - using buffered reader solves the issue but this should eventually
  // be resolved in a different way
  private Model parseModel(InputStream is) throws Exception {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      StringBuilder sb = new StringBuilder();
      for (String line = br.readLine(); line != null; line = br.readLine()) {
        sb.append(line);
        sb.append("\n");
      }
      br.close();

      byte[] docBytes = sb.toString().getBytes("UTF-8");
      is = new ByteArrayInputStream(docBytes);
       
      Model model = ModelFactory.createDefaultModel();
      model.read(is, null, "N3");
       
      return model;
    }
    finally {
      try { is.close(); } catch (Exception e) { }
    }
  }
  
  @Test
  public void testRoundTripExample() throws Exception {
    InputStream is = getClass().getResourceAsStream("/bii-i-1_investigation.n3");
    if (is == null) {
      throw new RuntimeException("Could not get test file: bii-i-1_investigation.n3");
    }
        
    Model model = parseModel(is);
    List<Investigation> investigations = getIOClass().fromJena(model);
    TestCase.assertEquals("Should have 1 investigation", 1, investigations.size());
    Investigation investigation = investigations.get(0);
    
    File outputFile = getOutputFile(investigation, "full.n3");
    OutputStream out = new FileOutputStream(outputFile);
    try {
      Serializer.toTurtle(out, model);
    }
    finally {
      try { out.close(); } catch (Exception e) {}
    }
      
    InputStream reparsedIs = new FileInputStream(outputFile);
    Model reparsedModel = parseModel(reparsedIs);
    List<Investigation> reparsedInvestigations = getIOClass().fromJena(reparsedModel);
    TestCase.assertEquals("Should have 1 investigation", 1, reparsedInvestigations.size());
    Investigation reparsedInvestigation = reparsedInvestigations.get(0);
    TestCase.assertEquals("Should have same url after reparsing", 
        reparsedInvestigation.getResourceURL(), investigation.getResourceURL());
  }
}
