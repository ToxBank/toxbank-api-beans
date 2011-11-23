package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest extends AbstractToxBankResourceTest {
	
	private final static String TEST_SERVER = "http://demo.toxbank.net/";

	@Before
	public void setup() {
		setToxBankResource(new User());
	}

	@Test
	public void testConstructor() {
		User clazz = new User();
		Assert.assertNotNull(clazz);
	}

	@Test
	public void testGetSetTitle() {
		User user = new User();
		Assert.assertNull(user.getTitle());
		user.setTitle("Dr");
		Assert.assertEquals("Dr", user.getTitle());
	}

	@Test
	public void testGetSetFirstname() {
		User user = new User();
		Assert.assertNull(user.getFirstname());
		user.setFirstname("Anna");
		Assert.assertEquals("Anna", user.getFirstname());
	}

	@Test
	public void testGetSetLastname() {
		User user = new User();
		Assert.assertNull(user.getLastname());
		user.setLastname("Johansson");
		Assert.assertEquals("Johansson", user.getLastname());
	}

	@Test
	public void testGetSetHomepage() throws MalformedURLException {
		User user = new User();
		Assert.assertNull(user.getHomepage());
		user.setHomepage(new URL("http://egonw.github.com/"));
		Assert.assertEquals("http://egonw.github.com/", user.getHomepage().toString());
	}

	@Test
	public void testGetSetWeblog() throws MalformedURLException {
		User user = new User();
		Assert.assertNull(user.getWeblog());
		user.setWeblog(new URL("http://chem-bla-ics.blogspot.com/"));
		Assert.assertEquals("http://chem-bla-ics.blogspot.com/", user.getWeblog().toString());
	}

	@Test
	public void testGetSetSeuratProject() throws MalformedURLException {
		User user = new User();
		Assert.assertNull(user.getSeuratProject());
		Organisation seuratProject = new Organisation();
		user.setSeuratProject(seuratProject);
		Assert.assertEquals(seuratProject, user.getSeuratProject());
	}

	@Test
	public void testGetSetInstitute() throws MalformedURLException {
		User user = new User();
		Assert.assertNull(user.getInstitute());
		Organisation karolinska = new Organisation();
		user.setInstitute(karolinska);
		Assert.assertEquals(karolinska, user.getInstitute());
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
