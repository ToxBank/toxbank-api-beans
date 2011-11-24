package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
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
		Protocol testProtocol = new Protocol();
		testProtocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		Protocol roundTrippedProtocol = roundtripSingleProtocol(testProtocol);
		Assert.assertEquals(
			"http://example.org/testProtocol/666",
			roundTrippedProtocol.getResourceURL().toString()
		);
	}

	private Protocol roundtripSingleProtocol(Protocol testProtocol) {
		ProtocolIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Protocol> roundTrippedProtocols = ioClass.fromJena(model);
//		Serializer.toRDFXML(System.out, model);
		Assert.assertEquals(1, roundTrippedProtocols.size());
		Protocol roundTrippedProtocol = roundTrippedProtocols.get(0);
		return roundTrippedProtocol;
	}

	@Test
	public void testRoundtripTitle() throws MalformedURLException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setTitle("Title");

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertEquals("Title", roundtripped.getTitle());
	}

	@Test
	public void testRoundtripTitle_Null() throws MalformedURLException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setTitle(null);

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertNull(roundtripped.getTitle());
	}

	@Test
	public void testRoundtripIdentifier() throws MalformedURLException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setIdentifier("Title");

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertEquals("Title", roundtripped.getIdentifier());
	}

	@Test
	public void testRoundtripIdentifier_Null() throws MalformedURLException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setIdentifier(null);

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertEquals(null, roundtripped.getIdentifier());
	}

	@Test
	public void testRoundtripAbstract() throws MalformedURLException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setAbstract("This is the funniest abstract ever!");

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertEquals("This is the funniest abstract ever!", roundtripped.getAbstract());
	}

	@Test
	public void testRoundtripKeywords() throws MalformedURLException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.addKeyword("key");
		protocol.addKeyword("word");

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		List<String> keywords = roundtripped.getKeywords();
		Assert.assertNotNull(keywords);
		Assert.assertEquals(2, keywords.size());
		Assert.assertTrue(keywords.contains("key"));
		Assert.assertTrue(keywords.contains("word"));
	}
}
