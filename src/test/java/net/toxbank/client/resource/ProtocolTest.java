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
	public void testGetSetAuthor() {
		Protocol protocol = new Protocol();
		Assert.assertNull(protocol.getAuthor());
		User user = new User();
		protocol.setAuthor(user);
		Assert.assertNotNull(protocol.getAuthor());
		Assert.assertEquals(user, protocol.getAuthor());
	}
}
