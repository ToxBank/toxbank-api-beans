package net.toxbank.client.resource;

import java.net.URL;

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
		Long now = System.currentTimeMillis();
		version.setSubmissionDate(now);
		Assert.assertEquals(now, version.getSubmissionDate());
	}
	
	@Test
	public void testGetSetPublishedFlag() {
		Protocol version = new Protocol();
		Long now = System.currentTimeMillis();
		version.setPublished(true);
		Assert.assertTrue(version.isPublished());
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
	

	@Test
	public void testGetSetDocument() throws Exception {
		Protocol protocol = new Protocol();
		Assert.assertNull(protocol.getDocument());
		Document doc = new Document(new URL("http://example.com/SEURAT-Protocol-1-1/document"));
		protocol.setDocument(doc);
		Assert.assertNotNull(protocol.getDocument());
		Assert.assertEquals(doc, protocol.getDocument());
	}	
	
	@Test
	public void testGetSetDataTemplate() throws Exception {
		Protocol protocol = new Protocol();
		Assert.assertNull(protocol.getDataTemplate());
		Template dataTemplate = new Template(new URL("http://example.com/SEURAT-Protocol-1-1/datatemplate"));
		protocol.setDataTemplate(dataTemplate);
		Assert.assertNotNull(protocol.getDataTemplate());
		Assert.assertEquals(dataTemplate, protocol.getDataTemplate());
	}		
	
}
