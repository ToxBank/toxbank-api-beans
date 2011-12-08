package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Template;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class TemplateIOTest extends AbstractIOClassTest<Template> {

	public TemplateIO getIOClass() {
		return new TemplateIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Template testResource = new Template();
		testResource.setResourceURL(new URL("http://example.org/templates/1"));
		Template roundTrippedResource = roundtripSingleTemplate(testResource);
		Assert.assertEquals(
			"http://example.org/templates/1",
			roundTrippedResource.getResourceURL().toString()
		);
	}

	private Template roundtripSingleTemplate(Template testProtocol) throws IOException {
		TemplateIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Template> roundTrippedResources = ioClass.fromJena(model);

		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Template roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
