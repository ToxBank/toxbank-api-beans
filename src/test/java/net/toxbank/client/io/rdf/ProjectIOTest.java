package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Project;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class ProjectIOTest extends AbstractIOClassTest<Project> {

	public ProjectIO getIOClass() {
		return new ProjectIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Project testResource = new Project();
		testResource.setResourceURL(new URL("http://example.org/project/ToxBank"));
		testResource.setTitle("ToxBank");
		Project roundTrippedResource = roundtripSingleProject(testResource);
		Assert.assertEquals(
			"http://example.org/project/ToxBank",
			roundTrippedResource.getResourceURL().toString()
		);
		Assert.assertEquals(
				"ToxBank",
				roundTrippedResource.getTitle()
			);		
	}

	@Test
	public void testRoundtripWithSuborganisation() throws Exception {
		Project testResource = new Project();
		testResource.setResourceURL(new URL("http://example.org/project/ToxBank"));
		testResource.setTitle("ToxBank");
		String cluster = String.format("%s%s",TOXBANK.URI,URLEncoder.encode(TOXBANK.SEURAT1));
		testResource.setCluster(new URL(cluster));
		Project roundTrippedResource = roundtripSingleProject(testResource);
		Assert.assertEquals(
			"http://example.org/project/ToxBank",
			roundTrippedResource.getResourceURL().toString()
		);
		Assert.assertEquals(
				"ToxBank",
				roundTrippedResource.getTitle()
			);		
		Assert.assertEquals(
				cluster,
				roundTrippedResource.getCluster().toExternalForm()
			);		
	}
	
	private Project roundtripSingleProject(Project testProject) throws IOException {
		ProjectIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProject
		);

		List<Project> roundTrippedResources = ioClass.fromJena(model);
		OutputStream out = getResourceStream(testProject,"n3");
		Serializer.toRDFXML(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Project roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}
	

}
