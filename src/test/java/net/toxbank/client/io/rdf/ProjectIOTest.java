package net.toxbank.client.io.rdf;

import java.net.URL;
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
		testResource.setResourceURL(new URL("http://example.org/users/JohnDoe/account/1"));
		Project roundTrippedResource = roundtripSingleProject(testResource);
		Assert.assertEquals(
			"http://example.org/users/JohnDoe/account/1",
			roundTrippedResource.getResourceURL().toString()
		);
	}

	private Project roundtripSingleProject(Project testProtocol) {
		ProjectIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Project> roundTrippedResources = ioClass.fromJena(model);
		Serializer.toTurtle(System.out, model);
		Assert.assertEquals(1, roundTrippedResources.size());
		Project roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
