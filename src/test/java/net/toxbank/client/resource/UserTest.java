package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest extends AbstractToxBankResourceTest {

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
	public void testGetSetToxbankAccount() throws MalformedURLException {
		User user = new User();
		Assert.assertNull(user.getUserName());
		user.setUserName("guest");
		Assert.assertEquals("guest", user.getUserName().toString());
	}

	@Test
	public void testGetSetProjects() throws MalformedURLException {
		User user = new User();
		Assert.assertNull(user.getProjects());
		Project seuratProject = new Project();
		user.addProject(seuratProject);
		Assert.assertEquals(1, user.getProjects().size());
		Assert.assertEquals(seuratProject, user.getProjects().get(0));
	}

	@Test
	public void testGetSetOrganisations() throws MalformedURLException {
		User user = new User();
		Assert.assertNull(user.getOrganisations());
		Organisation karolinska = new Organisation();
		user.addOrganisation(karolinska);
		Assert.assertEquals(1, user.getOrganisations().size());
		Assert.assertEquals(karolinska, user.getOrganisations().get(0));
	}

}
