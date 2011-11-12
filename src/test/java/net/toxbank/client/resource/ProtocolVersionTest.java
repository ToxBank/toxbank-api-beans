package net.toxbank.client.resource;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class ProtocolVersionTest {

	private final static String TEST_SERVER = "http://demo.toxbank.net/";

	@Test
	public void testConstructor() {
		ProtocolVersion clazz = new ProtocolVersion();
		Assert.assertNotNull(clazz);
	}

	@Test
	public void testUpload() {
		ProtocolVersion protocol = new ProtocolVersion();
		URL url = protocol.upload(TEST_SERVER);
		Assert.assertNotNull(url);
		Assert.assertTrue(url.toExternalForm().startsWith(TEST_SERVER));
	}

}
