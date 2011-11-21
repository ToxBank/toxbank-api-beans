package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AssayTest extends AbstractToxBankResourceTest {
	
	@Before
	public void setup() {
		setToxBankResource(new Template());
	}

	@Test
	public void testConstructor() {
		Assay assay = new Assay();
		Assert.assertNotNull(assay);
	}

}
