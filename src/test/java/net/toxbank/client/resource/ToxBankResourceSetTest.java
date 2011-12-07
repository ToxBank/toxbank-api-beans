package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToxBankResourceSetTest extends AbstractToxBankResourceTest {

	@Before
	public void setup() {
		setToxBankResource(new ToxBankResourceSet<Integer>());
	}

	@Test
	public void testConstructor() {
		ToxBankResourceSet<Integer> clazz = new ToxBankResourceSet<Integer>();
		Assert.assertNotNull(clazz);
		Assert.assertEquals(0, clazz.size());
	}

	@Test
	public void testConstructor_InitialSize() {
		ToxBankResourceSet<Integer> clazz = new ToxBankResourceSet<Integer>(9999999);
		Assert.assertNotNull(clazz);
		Assert.assertEquals(0, clazz.size());
	}

	@Test
	public void testConstructor2() {
		ToxBankResourceSet<Integer> clazz = new ToxBankResourceSet<Integer>();
		clazz.add(5);
		clazz.add(6);
		clazz.add(7);
		ToxBankResourceSet<Integer> clazz2 = new ToxBankResourceSet<Integer>(clazz);
		Assert.assertEquals(3, clazz2.size());
	}

}
