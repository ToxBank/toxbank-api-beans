package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractToxBankResourceTest {

	private AbstractToxBankResource resource;

	public void setToxBankResource(AbstractToxBankResource resourz) {
		this.resource = resourz;
	}

	@Test
	public void testGetSetResourceURL() throws MalformedURLException {
		Assert.assertNotNull(
			"The unit test must use setToxBankResource() to set the resource in a @BeforeClass method",
			resource
		);
		resource.setResourceURL(new URL("http://example.org/test"));
		Assert.assertNotNull(resource.getResourceURL());
		Assert.assertEquals("http://example.org/test", resource.getResourceURL().toString());
	}

}
