package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest extends AbstractToxBankResourceTest {

	@Before
	public void setup() {
		setToxBankResource((IToxBankResource)new User());
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

}
