package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrganisationTest extends AbstractToxBankResourceTest {
	
	@Before
	public void setup() {
		setToxBankResource((IToxBankResource)new Organisation());
	}

	@Test
	public void testConstructor() {
		Organisation clazz = new Organisation();
		Assert.assertNotNull(clazz);
	}

}
