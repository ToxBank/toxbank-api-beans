package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TemplateTest extends AbstractToxBankResourceTest {

	@Before
	public void setup() {
		setToxBankResource(new Template());
	}

	@Test
	public void testConstructor() {
		Template clazz = new Template();
		Assert.assertNotNull(clazz);
	}

}
