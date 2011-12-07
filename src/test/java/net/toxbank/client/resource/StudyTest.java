package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudyTest extends AbstractToxBankResourceTest {
	
	@Before
	public void setup() {
		setToxBankResource((IToxBankResource)new Study());
	}

	@Test
	public void testConstructor() {
		Study clazz = new Study();
		Assert.assertNotNull(clazz);
	}

	@Test
	public void testGetSetKeywords() {
		Study study = new Study();
		Assert.assertEquals(0, study.getKeywords().size());
		study.addKeyword("foo");
		Assert.assertEquals(1, study.getKeywords().size());
		Assert.assertTrue(study.getKeywords().contains("foo"));
		study.removeKeyword("foo");
		Assert.assertEquals(0, study.getKeywords().size());
		Assert.assertFalse(study.getKeywords().contains("foo"));
	}

	@Test
	public void testGetSetAbstract() {
		Study study = new Study();
		Assert.assertNull(study.getAbstract());
		study.setAbstract("This is an abstract");
		Assert.assertNotNull(study.getAbstract());
		Assert.assertEquals(19, study.getAbstract().length());
	}

	@Test
	public void testGetSetAuthor() {
		Study study = new Study();
		Assert.assertNull(study.getAuthor());
		User user = new User();
		study.setAuthor(user);
		Assert.assertNotNull(study.getAuthor());
		Assert.assertEquals(user, study.getAuthor());
	}

	@Test
	public void testGetSetVersionInfo() {
		Study study = new Study();
		Assert.assertNull(study.getVersionInfo());
		study.setVersionInfo("1");
		Assert.assertNotNull(study.getVersionInfo());
		Assert.assertEquals("1", study.getVersionInfo());
	}

}
