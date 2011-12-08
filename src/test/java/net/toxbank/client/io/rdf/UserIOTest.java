package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Account;
import net.toxbank.client.resource.User;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class UserIOTest extends AbstractIOClassTest<User> {

	public UserIO getIOClass() {
		return new UserIO();
	}

	/**
	 * A full example, if only to get a rich .n3 document on Jenkins, and also
	 * see if a full example does not crash. No special assertions made.
	 */
	@Test
	public void testFullExample() throws Exception {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setTitle("Title");
		user.setHomepage(new URL("http://egonw.github.com/"));
		user.setWeblog(new URL("http://chem-bla-ics.blogspot.com/"));
		Account account = new Account();
		account.setService("http://friendfeed.com/");
		account.setAccountName("egonw");
		user.addAccount(account);
		account = new Account();
		account.setService("http://opentox.org/");
		account.setAccountName("bhardy");
		user.addAccount(account);
		user.setFirstname("Anna");
		user.setLastname("Johansson");

		// just roundtrip and hope we do not get an exception
		Assert.assertNotNull(roundtripSingleUser(user));
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		User testProtocol = new User();
		testProtocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		User roundTrippedProtocol = roundtripSingleUser(testProtocol);
		Assert.assertEquals(
			"http://example.org/testProtocol/666",
			roundTrippedProtocol.getResourceURL().toString()
		);
	}

	private User roundtripSingleUser(User testProtocol) throws IOException {
		UserIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<User> roundTrippedUsers = ioClass.fromJena(model);
		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedUsers.size());
		User roundTrippedClass = roundTrippedUsers.get(0);
		return roundTrippedClass;
	}

	@Test
	public void testRoundtripTitle() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setTitle("Title");

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("Title", roundtripped.getTitle());
	}

	@Test
	public void testRoundtripHomepage() throws MalformedURLException , IOException{
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setHomepage(new URL("http://egonw.github.com/"));

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("http://egonw.github.com/", roundtripped.getHomepage().toString());
	}

	@Test
	public void testRoundtripWeblog() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setWeblog(new URL("http://chem-bla-ics.blogspot.com/"));

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("http://chem-bla-ics.blogspot.com/", roundtripped.getWeblog().toString());
	}

	@Test
	public void testRoundtripTitle_Null() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setTitle(null);

		User roundtripped = roundtripSingleUser(user);

		Assert.assertNull(roundtripped.getTitle());
	}

	@Test
	public void testRoundtripAccount() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		Account account = new Account();
		account.setService("http://friendfeed.com/");
		account.setAccountName("egonw");
		user.addAccount(account);

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals(1, roundtripped.getAccounts().size());
		Assert.assertEquals("egonw", roundtripped.getAccounts().get(0).getAccountName());
	}
	
	@Test
	public void testRoundtripFirstName() throws MalformedURLException , IOException{
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/AJ"));
		user.setFirstname("Anna");
		user.setLastname("Johansson");
		
		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("Anna", roundtripped.getFirstname());
		Assert.assertEquals("Johansson", roundtripped.getLastname());
	}	
}
