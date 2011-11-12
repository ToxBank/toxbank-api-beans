package net.toxbank.client.resource;

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
	
}
