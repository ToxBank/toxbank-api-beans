package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Organisation;
import net.toxbank.client.resource.Protocol;
import net.toxbank.client.resource.ToxBankResourceSet;
import net.toxbank.client.resource.User;

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

	private Protocol roundtripSingleProtocol(Protocol testProtocol) throws IOException {
		ProtocolIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);
		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		List<Protocol> roundTrippedProtocols = ioClass.fromJena(model);
		Assert.assertEquals(1, roundTrippedProtocols.size());
		Protocol roundTrippedProtocol = roundTrippedProtocols.get(0);
		return roundTrippedProtocol;
	}

	@Test
	public void testRoundtripTitle() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setTitle("Title");

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertEquals("Title", roundtripped.getTitle());
	}

	@Test
	public void testRoundtripTitle_Null() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setTitle(null);

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertNull(roundtripped.getTitle());
	}

	@Test
	public void testRoundtripIdentifier() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setIdentifier("Title");

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertEquals("Title", roundtripped.getIdentifier());
	}

	@Test
	public void testRoundtripIdentifier_Null() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setIdentifier(null);

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertEquals(null, roundtripped.getIdentifier());
	}

	@Test
	public void testRoundtripAbstract() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		protocol.setAbstract("This is the funniest abstract ever!");

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertEquals("This is the funniest abstract ever!", roundtripped.getAbstract());
	}

	@Test
	public void testRoundtripKeywords() throws MalformedURLException, IOException {
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

	@Test
	public void testRoundtripOrganization() throws MalformedURLException , IOException{
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		Organisation org = new Organisation();
		org.setResourceURL(new URL("http://www.toxbank.net/"));
		protocol.setOrganisation(org);

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertNotNull(roundtripped.getOrganisation());
		Assert.assertEquals(
			"http://www.toxbank.net/",
			roundtripped.getOrganisation().getResourceURL().toString()
		);
	}

	@Test
	public void testRoundtripOwner() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		User owner = new User();
		owner.setResourceURL(new URL("http://example.org/testUser/B.Bub"));
		protocol.setOwner(owner);

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		Assert.assertNotNull(roundtripped.getOwner());
		Assert.assertEquals(
			"http://example.org/testUser/B.Bub",
			roundtripped.getOwner().getResourceURL().toString()
		);
	}

	@Test
	public void testRoundtripAuthors() throws MalformedURLException , IOException{
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		User bub = new User();
		bub.setResourceURL(new URL("http://example.org/testUser/B.Bub"));
		protocol.addAuthor(bub);
		User adder = new User();
		adder.setResourceURL(new URL("http://example.org/testUser/B.Adder"));
		protocol.addAuthor(adder);

		Protocol roundtripped = roundtripSingleProtocol(protocol);

		ToxBankResourceSet<User> authors = roundtripped.getAuthors();
		Assert.assertNotNull(authors);
		Assert.assertEquals(2, authors.size());
		
		// we don't know the order
		Assert.assertTrue(
			roundtripped.getAuthors().get(0).getResourceURL().toString().equals("http://example.org/testUser/B.Bub") ||
			roundtripped.getAuthors().get(1).getResourceURL().toString().equals("http://example.org/testUser/B.Bub")
		);
		Assert.assertTrue(
				roundtripped.getAuthors().get(0).getResourceURL().toString().equals("http://example.org/testUser/B.Adder") ||
				roundtripped.getAuthors().get(1).getResourceURL().toString().equals("http://example.org/testUser/B.Adder")
		);
	}
}
