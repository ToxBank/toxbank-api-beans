package net.toxbank.client.io.rdf;

import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import net.toxbank.client.resource.Protocol;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class ProtocolIOTest extends AbstractIOClassTest<Protocol> {

	public ProtocolIO getIOClass() {
		return new ProtocolIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		ProtocolIO ioClass = getIOClass();
		
		Protocol testProtocol = new Protocol();
		testProtocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Protocol> roundTrippedProtocols = ioClass.fromJena(model);
		model.write(System.out, "RDF/XML");
		Assert.assertEquals(1, roundTrippedProtocols.size());
		Assert.assertEquals(
			"http://example.org/testProtocol/666",
			roundTrippedProtocols.get(0).getResourceURL().toString()
		);
	}

}
