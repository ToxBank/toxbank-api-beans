package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ProtocolTest {

	private final static String TEST_SERVER = "http://demo.toxbank.net/";

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
}
