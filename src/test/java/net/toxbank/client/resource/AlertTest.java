package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AlertTest extends AbstractToxBankResourceTest {
	
	@Before
	public void setup() {
		setToxBankResource(new Alert());
	}

	@Test
	public void testConstructor() {
		Alert alert = new Alert();
		Assert.assertNotNull(alert);
	}

}
