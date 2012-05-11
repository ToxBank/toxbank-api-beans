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
  public void testReadExample() throws Throwable {
    InputStream is = getClass().getResourceAsStream("/bii-i-1_investigation.n3");
    if (is == null) {
      throw new RuntimeException("Could not get test file: bii-i-1_investigation.n3");
    }
        
    Model model = parseModel(is);
    List<Investigation> investigations = getIOClass().fromJena(model);
    TestCase.assertEquals("Should have 1 investigation", 1, investigations.size());
    Investigation investigation = investigations.get(0);
    verifyBiiInvestigation(investigation);
        
    Model newModel = ModelFactory.createDefaultModel();
    newModel = getIOClass().toJena(newModel, investigation);
    File outputFile = getOutputFile(investigation, "full.n3");
    OutputStream out = new FileOutputStream(outputFile);
    try {
      Serializer.toTurtle(out, newModel);
    }
    finally {
      try { out.close(); } catch (Exception e) {}
    }
  }
  
  @Test
  public void testMetaData() throws Throwable {
    InputStream is = getClass().getResourceAsStream("/metadata-11.n3");
    if (is == null) {
      throw new RuntimeException("Could not get test file: metadata-11.n3");
    }
        
    Model model = parseModel(is);
    List<Investigation> investigations = getIOClass().fromJena(model);
    TestCase.assertEquals("Should have 1 investigation", 1, investigations.size());
    Investigation investigation = investigations.get(0);
    verifyMetadata11Investigation(investigation);
  }
  
  private void verifyBiiInvestigation(Investigation i) throws Throwable {
    TestCase.assertEquals("BII-I-1", i.getAccessionId());
    TestCase.assertEquals("Growth control of the eukaryote cell: a systems biology study in yeast", i.getTitle());
    TestCase.assertEquals("Background Cell growth underlies many key cellular and developmental processes, yet a limited number of studies have been carried out on cell-growth regulation. Comprehensive studies at the transcriptional, proteomic and metabolic levels under defined controlled conditions are currently lacking. Results Metabolic control analysis is being exploited in a systems biology study of the eukaryotic cell. Using chemostat culture, we have measured the impact of changes in flux (growth rate) on the transcriptome, proteome, endometabolome and exometabolome of the yeast Saccharomyces cerevisiae. Each functional genomic level shows clear growth-rate-associated trends and discriminates between carbon-sufficient and carbon-limited conditions. Genes consistently and significantly upregulated with increasing growth rate are frequently essential and encode evolutionarily conserved proteins of known function that participate in many protein-protein interactions. In contrast, more unknown, and fewer essential, genes are downregulated with increasing growth rate; their protein products rarely interact with one another. A large proportion of yeast genes under positive growth-rate control share orthologs with other eukaryotes, including humans. Significantly, transcription of genes encoding components of the TOR complex (a major controller of eukaryotic cell growth) is not subject to growth-rate regulation. Moreover, integrative studies reveal the extent and importance of post-transcriptional control, patterns of control of metabolic fluxes at the level of enzyme synthesis, and the relevance of specific enzymatic reactions in the control of metabolic fluxes during cell growth. Conclusion This work constitutes a first comprehensive systems biology study on growth-rate control in the eukaryotic cell. The results have direct implications for advanced studies on cell growth, in vivo regulation of metabolic fluxes for comprehensive metabolic engineering, and for the design of genome-scale systems biology models of the eukaryotic cell.",
        i.getAbstract());
    TestCase.assertEquals(new Long(1177905600000l), i.getSubmissionDate());
    TestCase.assertEquals(new Long(1236657600000l), i.getLastModifiedDate());
    TestCase.assertNotNull("Should have owner", i.getOwner());
    TestCase.assertEquals("http://toxbanktest1.opentox.org:8080/toxbank/user/U115", i.getOwner().getResourceURL().toString());
    TestCase.assertNotNull("Should have organisation", i.getOrganisation());
    TestCase.assertEquals("http://toxbanktest1.opentox.org:8080/toxbank/organisation/G176", i.getOrganisation().getResourceURL().toString());
    TestCase.assertNotNull("Should have project", i.getProject());
    TestCase.assertEquals("http://toxbanktest1.opentox.org:8080/toxbank/project/G2", i.getProject().getResourceURL().toString());
    TestCase.assertEquals("Should have one toxbank protocol", 1, i.getProtocols().size());
    TestCase.assertEquals("http://toxbanktest1.opentox.org:8080/toxbank/protocol/SEURAT-Protocol-245-1", i.getProtocols().get(0).getResourceURL().toString());
    TestCase.assertEquals("Should have 3 keywords", 3, i.getKeywords().size());
    TestCase.assertTrue("Should contain the cell migrations keyword", 
        i.getKeywords().contains("http://www.owl-ontologies.com/toxbank.owl#CellMigrationAssays"));
  }
  
  private void verifyMetadata11Investigation(Investigation i) throws Throwable {
    TestCase.assertEquals("BII-I-1", i.getAccessionId());
  }
}
