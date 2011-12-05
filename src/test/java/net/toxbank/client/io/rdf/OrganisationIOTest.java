package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Organisation;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class OrganisationIOTest extends AbstractIOClassTest<Organisation> {

	public OrganisationIO getIOClass() {
		return new OrganisationIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Organisation testResource = new Organisation();
		testResource.setResourceURL(new URL("http://example.org/organisation/ToxBank"));
		testResource.setTitle("ToxBank");
		Organisation roundTrippedResource = roundtripSingleOrganisation(testResource);
		Assert.assertEquals(
			"http://example.org/organisation/ToxBank",
			roundTrippedResource.getResourceURL().toString()
		);
		Assert.assertEquals(
				"ToxBank",
				roundTrippedResource.getTitle()
			);
	}

	private Organisation roundtripSingleOrganisation(Organisation testProtocol) throws IOException {
		OrganisationIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Organisation> roundTrippedResources = ioClass.fromJena(model);
		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Organisation roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
