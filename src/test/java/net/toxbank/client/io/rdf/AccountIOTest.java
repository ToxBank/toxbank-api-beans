package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Account;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class AccountIOTest extends AbstractIOClassTest<Account> {

	public AccountIO getIOClass() {
		return new AccountIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Account testResource = new Account();
		testResource.setResourceURL(new URL("http://example.org/users/JohnDoe/account/1"));
		Account roundTrippedResource = roundtripSingleAccount(testResource);
		Assert.assertEquals(
			"http://example.org/users/JohnDoe/account/1",
			roundTrippedResource.getResourceURL().toString()
		);
	}

	private Account roundtripSingleAccount(Account testProtocol) throws IOException {
		AccountIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Account> roundTrippedResources = ioClass.fromJena(model);
		
		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Account roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}


	@Test
	public void testRoundtripAccountName() throws MalformedURLException,IOException {
		Account testResource = new Account();
		testResource.setResourceURL(new URL("http://example.org/users/JohnDoe/account/1"));
		testResource.setAccountName("johndoe");

		Account roundtripped = roundtripSingleAccount(testResource);

		Assert.assertEquals("johndoe", roundtripped.getAccountName());
	}

	@Test
	public void testRoundtripAccountName_Null() throws MalformedURLException,IOException {
		Account testResource = new Account();
		testResource.setResourceURL(new URL("http://example.org/users/JohnDoe/account/1"));
		testResource.setAccountName(null);

		Account roundtripped = roundtripSingleAccount(testResource);

		Assert.assertNull(roundtripped.getAccountName());
	}

	@Test
	public void testRoundtripService() throws MalformedURLException,IOException {
		Account testResource = new Account();
		testResource.setResourceURL(new URL("http://example.org/users/JohnDoe/account/1"));
		testResource.setService("http://www.twitter.com/");

		Account roundtripped = roundtripSingleAccount(testResource);

		Assert.assertEquals("http://www.twitter.com/", roundtripped.getService());
	}

	@Test
	public void testRoundtripService_Null() throws MalformedURLException,IOException {
		Account testResource = new Account();
		testResource.setResourceURL(new URL("http://example.org/users/JohnDoe/account/1"));
		testResource.setService(null);

		Account roundtripped = roundtripSingleAccount(testResource);

		Assert.assertNull(roundtripped.getService());
	}
}
