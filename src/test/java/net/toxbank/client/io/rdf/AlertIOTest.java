package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Alert;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class AlertIOTest extends AbstractIOClassTest<Alert> {

	public AlertIO getIOClass() {
		return new AlertIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Alert testResource = new Alert();
		testResource.setResourceURL(new URL("http://example.org/alerts/1"));
		Alert roundTrippedResource = roundtripSingleAlert(testResource);
		Assert.assertEquals(
			"http://example.org/alerts/1",
			roundTrippedResource.getResourceURL().toString()
		);
	}

	private Alert roundtripSingleAlert(Alert testProtocol) throws IOException {
		AlertIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Alert> roundTrippedResources = ioClass.fromJena(model);
		
		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Alert roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
