package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
	
	private final static String TEST_SERVER = "http://demo.toxbank.net/";

	@Test
	public void testConstructor() {
		User clazz = new User();
		Assert.assertNotNull(clazz);
	}

	@Test
	public void testRetrieveMetadata() throws MalformedURLException {
		User user = new User(new URL(TEST_SERVER + "user/ab7f235ccd"));
		Assert.assertNotNull(user);
		// FIXME: test loaded metadata
	}
	
	@Test
	public void testList() {
		List<URL> users = User.list(TEST_SERVER);
		Assert.assertNotNull(users);
		Assert.assertNotSame(0, users.size());
	}
	
}
