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
	
	@Test
	public void testListProtocols() throws MalformedURLException {
		User protocol = new User(new URL(TEST_SERVER + "user/ab7f235ccd"));
		List<URL> protocols = protocol.listProtocols();
		Assert.assertNotNull(protocols);
		Assert.assertNotSame(0, protocols.size());
	}
	
	@Test
	public void testGetProtocols() throws MalformedURLException {
		User protocol = new User(new URL(TEST_SERVER + "user/ab7f235ccd"));
		List<Protocol> protocols = protocol.getProtocols();
		Assert.assertNotNull(protocols);
		Assert.assertNotSame(0, protocols.size());
	}

	@Test
	public void testListStudies() throws MalformedURLException {
		User protocol = new User(new URL(TEST_SERVER + "user/ab7f235ccd"));
		List<URL> studies = protocol.listStudies();
		Assert.assertNotNull(studies);
		Assert.assertNotSame(0, studies.size());
	}
	
	@Test
	public void testGetStudies() throws MalformedURLException {
		User protocol = new User(new URL(TEST_SERVER + "user/ab7f235ccd"));
		List<Study> studies = protocol.getStudies();
		Assert.assertNotNull(studies);
		Assert.assertNotSame(0, studies.size());
	}

	@Test
	public void testListAlerts() throws MalformedURLException {
		User protocol = new User(new URL(TEST_SERVER + "user/ab7f235ccd"));
		List<URL> alerts = protocol.listAlerts();
		Assert.assertNotNull(alerts);
		Assert.assertNotSame(0, alerts.size());
	}
	
	@Test
	public void testGetAlerts() throws MalformedURLException {
		User protocol = new User(new URL(TEST_SERVER + "user/ab7f235ccd"));
		List<Alert> alerts = protocol.getAlerts();
		Assert.assertNotNull(alerts);
		Assert.assertNotSame(0, alerts.size());
	}
}
