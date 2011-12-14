package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Document;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class DocumentIOTest extends AbstractIOClassTest<Document> {

	public DocumentIO getIOClass() {
		return new DocumentIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Document testResource = new Document();
		testResource.setResourceURL(new URL("http://example.org/doc/1"));
		Document roundTrippedResource = roundtripSingleResource(testResource);
		Assert.assertEquals(
			"http://example.org/doc/1",
			roundTrippedResource.getResourceURL().toString()
		);
	}

	private Document roundtripSingleResource(Document testResource) throws IOException {
		DocumentIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testResource
		);

		OutputStream out = getResourceStream(testResource,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Serializer.toTurtle(System.out, model);

		List<Document> roundTrippedResources = ioClass.fromJena(model);
		
		Assert.assertEquals(1, roundTrippedResources.size());
		Document roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
