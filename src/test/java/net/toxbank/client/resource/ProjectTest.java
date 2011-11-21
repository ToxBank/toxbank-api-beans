package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectTest extends AbstractToxBankResourceTest {
	
	@Before
	public void setup() {
		setToxBankResource(new Template());
	}

	@Test
	public void testConstructor() {
		Project clazz = new Project();
		Assert.assertNotNull(clazz);
	}

}
