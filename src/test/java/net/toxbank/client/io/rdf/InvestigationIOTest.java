/**
 * Copyright 2011 Leadscope, Inc. All rights reserved.
 * LEADSCOPE PROPRIETARY and CONFIDENTIAL. Use is subject to license terms.
 */
package net.toxbank.client.io.rdf;

import java.io.*;
import java.util.List;

import net.toxbank.client.resource.Investigation;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class InvestigationIOTest extends AbstractIOClassTest<Investigation> {
  public InvestigationIO getIOClass() {
    return new InvestigationIO();
  }

  @Test
  public void testReadExample() throws Exception {
    InputStream is = getClass().getResourceAsStream("/bii-i-1_investigation.n3");
    if (is == null) {
      throw new RuntimeException("Could not get test file: bii-i-1_investigation.n3");
    }
    
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    for (String line = br.readLine(); line != null; line = br.readLine()) {
      sb.append(line);
      sb.append("\n");
    }
    br.close();

    byte[] docBytes = sb.toString().getBytes("UTF-8");
     is = new ByteArrayInputStream(docBytes);
    // is = getClass().getResourceAsStream("/bii-i-1_investigation.n3");
    try {
      Model model = ModelFactory.createDefaultModel();
      model.read(is, null, "N3");
      List<Investigation> investigations = getIOClass().fromJena(model);
      System.out.println(investigations.size());
    }
    finally {
      is.close();
    }
  }
}
