package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Assay;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class AssayIOTest extends AbstractIOClassTest<Assay> {

	public AssayIO getIOClass() {
		return new AssayIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Assay testResource = new Assay();
		testResource.setResourceURL(new URL("http://example.org/assays/1"));
		Assay roundTrippedResource = roundtripSingleAssay(testResource);
		Assert.assertEquals(
			"http://example.org/assays/1",
			roundTrippedResource.getResourceURL().toString()
		);
	}

	private Assay roundtripSingleAssay(Assay testProtocol) throws IOException {
		AssayIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Assay> roundTrippedResources = ioClass.fromJena(model);
		
		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Assay roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
