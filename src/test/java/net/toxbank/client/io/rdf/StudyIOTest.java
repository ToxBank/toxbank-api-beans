package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Study;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class StudyIOTest extends AbstractIOClassTest<Study> {

	public StudyIO getIOClass() {
		return new StudyIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Study testResource = new Study();
		testResource.setResourceURL(new URL("http://example.org/studies/1"));
		Study roundTrippedResource = roundtripSingleStudy(testResource);
		Assert.assertEquals(
			"http://example.org/studies/1",
			roundTrippedResource.getResourceURL().toString()
		);
	}

	private Study roundtripSingleStudy(Study testProtocol) throws IOException {
		StudyIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Study> roundTrippedResources = ioClass.fromJena(model);

		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Study roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
