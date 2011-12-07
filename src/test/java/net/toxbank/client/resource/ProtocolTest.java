package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProtocolTest extends AbstractToxBankResourceTest {

	@Before
	public void setup() {
		setToxBankResource(new Protocol());
	}

	@Test
	public void testConstructor() {
		Protocol clazz = new Protocol();
		Assert.assertNotNull(clazz);
	}

	@Test
	public void testGetSetKeywords() {
		Protocol protocol = new Protocol();
		Assert.assertEquals(0, protocol.getKeywords().size());
		protocol.addKeyword("foo");
		Assert.assertEquals(1, protocol.getKeywords().size());
		Assert.assertTrue(protocol.getKeywords().contains("foo"));
		protocol.removeKeyword("foo");
		Assert.assertEquals(0, protocol.getKeywords().size());
		Assert.assertFalse(protocol.getKeywords().contains("foo"));
	}

	@Test
	public void testGetSetAuthors() {
		
		Protocol protocol = new Protocol();
		Assert.assertEquals(0, protocol.getAuthors().size());
		User user = new User();
		protocol.addAuthor(user);
		Assert.assertEquals(1, protocol.getAuthors().size());
		Assert.assertTrue(protocol.getAuthors().contains(user));
		protocol.removeAuthor(user);
		Assert.assertEquals(0, protocol.getAuthors().size());
		Assert.assertFalse(protocol.getAuthors().contains(user));
	}

	@Test
	public void testGetSetTitle() {
		Protocol protocol = new Protocol();
		protocol.setTitle("Title");
		Assert.assertEquals("Title", protocol.getTitle());
	}

	@Test
	public void testGetSetIdentifier() {
		Protocol protocol = new Protocol();
		protocol.setIdentifier("Title");
		Assert.assertEquals("Title", protocol.getIdentifier());
	}

	@Test
	public void testGetSetAbstract() {
		Protocol protocol = new Protocol();
		protocol.setAbstract("This is the funniest abstract ever!");
		Assert.assertEquals("This is the funniest abstract ever!", protocol.getAbstract());
	}

	@Test
	public void testGetSetOwner() {
		Protocol protocol = new Protocol();
		Assert.assertNull(protocol.getOwner());
		User user = new User();
		protocol.setOwner(user);
		Assert.assertNotNull(protocol.getOwner());
		Assert.assertEquals(user, protocol.getOwner());
	}

	@Test
	public void testGetSetInfo() {
		Protocol version = new Protocol();
		version.setInfo("2011-09-15");
		Assert.assertEquals("2011-09-15", version.getInfo());
	}

	@Test
	public void testGetSetSubmissionDate() {
		Protocol version = new Protocol();
		version.setSubmissionDate("2011-09-15");
		Assert.assertEquals("2011-09-15", version.getSubmissionDate());
	}

	@Test
	public void testGetSetIsSearchable() {
		Protocol version = new Protocol();
		Assert.assertFalse(version.isSearchable());
		version.setSearchable(true);
		Assert.assertTrue(version.isSearchable());
		version.setSearchable(false);
		Assert.assertFalse(version.isSearchable());
	}
}
