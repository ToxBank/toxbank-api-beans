package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractToxBankResourceTest {

	private IToxBankResource resource;

	public void setToxBankResource(IToxBankResource resourz) {
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

	@Test
	public void testGetSetTitle() throws MalformedURLException {
		Assert.assertNotNull(
			"The unit test must use setToxBankResource() to set the resource in a @BeforeClass method",
			resource
		);
		resource.setTitle("Some Test Object");
		Assert.assertNotNull(resource.getTitle());
		Assert.assertEquals("Some Test Object", resource.getTitle());
	}
}
