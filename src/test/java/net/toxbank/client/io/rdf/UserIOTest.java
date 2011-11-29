package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.User;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class UserIOTest extends AbstractIOClassTest<User> {

	public UserIO getIOClass() {
		return new UserIO();
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

	private User roundtripSingleUser(User testProtocol) {
		UserIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<User> roundTrippedUsers = ioClass.fromJena(model);
		Serializer.toTurtle(System.out, model);
		Assert.assertEquals(1, roundTrippedUsers.size());
		User roundTrippedClass = roundTrippedUsers.get(0);
		return roundTrippedClass;
	}

	@Test
	public void testRoundtripTitle() throws MalformedURLException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setTitle("Title");

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("Title", roundtripped.getTitle());
	}

	@Test
	public void testRoundtripTitle_Null() throws MalformedURLException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setTitle(null);

		User roundtripped = roundtripSingleUser(user);

		Assert.assertNull(roundtripped.getTitle());
	}

}
