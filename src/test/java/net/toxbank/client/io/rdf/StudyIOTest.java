package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Organisation;
import net.toxbank.client.resource.Study;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class StudyIOTest extends AbstractIOClassTest<Study> {

	public StudyIO getIOClass() {
		return new StudyIO();
	}

	@Test
	public void testRoundtripTitle() throws MalformedURLException, IOException {
		Study study = new Study();
		study.setResourceURL(new URL("http://example.org/testStudy/666"));
		study.setTitle("Title");

		Study roundtripped = roundtripSingleStudy(study);

		Assert.assertEquals("Title", roundtripped.getTitle());
	}

	@Test
	public void testRoundtripTitle_Null() throws MalformedURLException, IOException {
		Study study = new Study();
		study.setResourceURL(new URL("http://example.org/testStudy/666"));
		study.setTitle(null);

		Study roundtripped = roundtripSingleStudy(study);

		Assert.assertNull(roundtripped.getTitle());
	}

	@Test
	public void testRoundtripKeywords() throws MalformedURLException, IOException {
		Study study = new Study();
		study.setResourceURL(new URL("http://example.org/testStudy/666"));
		study.addKeyword("key");
		study.addKeyword("word");

		Study roundtripped = roundtripSingleStudy(study);

		List<String> keywords = roundtripped.getKeywords();
		Assert.assertNotNull(keywords);
		Assert.assertEquals(2, keywords.size());
		Assert.assertTrue(keywords.contains("key"));
		Assert.assertTrue(keywords.contains("word"));
	}

	@Test
	public void testRoundtripOwner() throws MalformedURLException, IOException {
		Study study = new Study();
		study.setResourceURL(new URL("http://example.org/testProtocol/666"));
		Organisation owner = new Organisation();
		owner.setResourceURL(new URL("http://example.org/testOrg/B.Bub"));
		study.setOwner(owner);

		Study roundtripped = roundtripSingleStudy(study);

		Assert.assertNotNull(roundtripped.getOwner());
		Assert.assertEquals(
			"http://example.org/testOrg/B.Bub",
			roundtripped.getOwner().getResourceURL().toString()
		);
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Study testResource = new Study();
		testResource.setResourceURL(new URL("http://example.org/studies/1"));
		Study roundTrippedResource = roundtripSingleStudy(testResource);
		Assert.assertEquals(
			"http://example.org/studies/1",
			roundTrippedResource.getResourceURL().toString()
		);
	}

	private Study roundtripSingleStudy(Study testProtocol) throws IOException {
		StudyIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<Study> roundTrippedResources = ioClass.fromJena(model);

		OutputStream out = getResourceStream(testProtocol,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Study roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
