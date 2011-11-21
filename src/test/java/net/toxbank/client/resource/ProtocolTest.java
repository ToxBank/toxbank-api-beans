package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProtocolTest extends AbstractToxBankResourceTest {

	private final static String TEST_SERVER = "http://demo.toxbank.net/";

	@Before
	public void setup() {
		setToxBankResource(new Template());
	}

	@Test
	public void testConstructor() {
		Protocol clazz = new Protocol();
		Assert.assertNotNull(clazz);
	}

	@Test
	public void testListProtocols() {
		List<URL> protocols = Protocol.listProtocols(TEST_SERVER);
		Assert.assertNotNull(protocols);
		Assert.assertNotSame(0, protocols.size());
	}
	
	@Test
	public void testUpload() {
		Protocol protocol = new Protocol();
		URL url = protocol.upload(TEST_SERVER);
		Assert.assertNotNull(url);
		Assert.assertTrue(url.toExternalForm().startsWith(TEST_SERVER));
	}
	
	@Test
	public void testRetrieveMetadata() throws MalformedURLException {
		Protocol protocol = new Protocol(new URL(TEST_SERVER + "protocol/1"));
		Assert.assertNotNull(protocol);
		Assert.assertEquals(
			TEST_SERVER + "organization/1",
			protocol.getOrganisation()
		);
	}
	
	@Test
	public void testListFiles() throws MalformedURLException {
		Protocol protocol = new Protocol(new URL(TEST_SERVER + "protocol/1"));
		List<URL> files = protocol.listFiles();
		Assert.assertNotNull(files);
		Assert.assertNotSame(0, files.size());
	}
	
	@Test
	public void testListTemplates() throws MalformedURLException {
		Protocol protocol = new Protocol(new URL(TEST_SERVER + "protocol/1"));
		List<URL> templates = protocol.listTemplates();
		Assert.assertNotNull(templates);
		Assert.assertNotSame(0, templates.size());
	}
	
	@Test
	public void testGetTemplates() throws MalformedURLException {
		Protocol protocol = new Protocol(new URL(TEST_SERVER + "protocol/1"));
		List<Template> templates = protocol.getTemplates();
		Assert.assertNotNull(templates);
		Assert.assertNotSame(0, templates.size());
	}
	
	@Test
	public void testListVersions() throws MalformedURLException {
		Protocol protocol = new Protocol(new URL(TEST_SERVER + "protocol/1"));
		List<URL> versions = protocol.listVersions();
		Assert.assertNotNull(versions);
		Assert.assertNotSame(0, versions.size());
	}
	
	@Test
	public void testGetVersions() throws MalformedURLException {
		Protocol protocol = new Protocol(new URL(TEST_SERVER + "protocol/1"));
		List<ProtocolVersion> versions = protocol.getVersions();
		Assert.assertNotNull(versions);
		Assert.assertNotSame(0, versions.size());
	}

	@Test
	public void testUploadingAndRetrieving() {
		Protocol protocol = new Protocol();
		protocol.addKeyword("cytotoxicity");
		URL identifier = protocol.upload(TEST_SERVER);
		
		// now download it again, and compare
		Protocol dlProtocol = new Protocol(identifier);
		Assert.assertTrue(dlProtocol.getKeywords().contains("cytotoxicity"));
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
	public void testRoundtripTitle() {
		Protocol protocol = new Protocol();
		protocol.setTitle("Title");
		URL resource = protocol.upload(TEST_SERVER);

		Protocol roundtripped = new Protocol(resource);
		Assert.assertEquals("Title", roundtripped.getTitle());
	}

	@Test
	public void testGetSetIdentifier() {
		Protocol protocol = new Protocol();
		protocol.setIdentifier("Title");
		Assert.assertEquals("Title", protocol.getIdentifier());
	}

	@Test
	public void testRoundtripIdentifier() {
		Protocol protocol = new Protocol();
		protocol.setIdentifier("Title");
		URL resource = protocol.upload(TEST_SERVER);

		Protocol roundtripped = new Protocol(resource);
		Assert.assertEquals("Title", roundtripped.getIdentifier());
	}

	@Test
	public void testGetSetAbstract() {
		Protocol protocol = new Protocol();
		protocol.setAbstract("This is the funniest abstract ever!");
		Assert.assertEquals("This is the funniest abstract ever!", protocol.getAbstract());
	}

	@Test
	public void testRoundtripAbstract() {
		Protocol protocol = new Protocol();
		protocol.setAbstract("This is the funniest abstract ever!");
		URL resource = protocol.upload(TEST_SERVER);

		Protocol roundtripped = new Protocol(resource);
		Assert.assertEquals("This is the funniest abstract ever!", roundtripped.getAbstract());
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
