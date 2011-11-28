package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProtocolVersionTest extends AbstractToxBankResourceTest {

	@Before
	public void setup() {
		setToxBankResource(new ProtocolVersion());
	}

	@Test
	public void testConstructor() {
		ProtocolVersion clazz = new ProtocolVersion();
		Assert.assertNotNull(clazz);
	}

	@Test
	public void testGetSetAbstract() {
		ProtocolVersion version = new ProtocolVersion();
		version.setAbstract("This is the funniest abstract ever!");
		Assert.assertEquals("This is the funniest abstract ever!", version.getAbstract());
	}

	@Test
	public void testGetSetInfo() {
		ProtocolVersion version = new ProtocolVersion();
		version.setInfo("2011-09-15");
		Assert.assertEquals("2011-09-15", version.getInfo());
	}

	@Test
	public void testGetSetSubmissionDate() {
		ProtocolVersion version = new ProtocolVersion();
		version.setSubmissionDate("2011-09-15");
		Assert.assertEquals("2011-09-15", version.getSubmissionDate());
	}

	@Test
	public void testGetSetIsSearchable() {
		ProtocolVersion version = new ProtocolVersion();
		Assert.assertFalse(version.isSearchable());
		version.setSearchable(true);
		Assert.assertTrue(version.isSearchable());
		version.setSearchable(false);
		Assert.assertFalse(version.isSearchable());
	}

}
