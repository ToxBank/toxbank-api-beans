package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DocumentTest extends AbstractToxBankResourceTest {
	
	@Before
	public void setup() {
		setToxBankResource(new Document());
	}

	@Test
	public void testConstructor() {
		Document clazz = new Document();
		Assert.assertNotNull(clazz);
	}

}
