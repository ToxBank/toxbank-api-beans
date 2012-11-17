package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Document;
import net.toxbank.client.resource.Organisation;
import net.toxbank.client.resource.Project;
import net.toxbank.client.resource.Protocol;
import net.toxbank.client.resource.Protocol.STATUS;
import net.toxbank.client.resource.Template;
import net.toxbank.client.resource.ToxBankResourceSet;
import net.toxbank.client.resource.User;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class ProtocolIOTest extends AbstractIOClassTest<Protocol> {
	protected static final String exampleProtocol = "http://example.org/testProtocol/666";
	
	public ProtocolIO getIOClass() {
		return new ProtocolIO();
	}

	/**
	 * A full example, if only to get a rich .n3 document on Jenkins, and also
	 * see if a full example does not crash. No special assertions made.
	 */
	@Test
	public void testFullExample() throws Exception {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/openwetware/DNA_Quantification"));
		Document doc = new Document();
		doc.setResourceURL(new URL("http://openwetware.org/wiki/DNA_Quantification"));
		protocol.setDocument(doc);
		protocol.setTitle("DNA Quantification");
		protocol.setIdentifier("DNA_Quantification");
		protocol.setAbstract("This protocol uses a spectrophotometer to quantify the amount " +
				"(μg/mL or ng/μL) of DNA and then uses a simple equation to convert this " +
				"mass concentration into a molar concentration. The molar concentration is " +
				"much more useful for most enzymatic processes.");
		protocol.addKeyword("DNA");
		protocol.addKeyword("quantification");
		protocol.addKeyword("spectrophotometer");
		Organisation org = new Organisation();
		org.setResourceURL(new URL("http://openwetware.org/"));
		protocol.setOrganisation(org);
		User michael = new User();
		michael.setResourceURL(new URL("http://openwetware.org/wiki/User:Michael_A._Speer"));
		michael.setFirstname("Michael");
		michael.setLastname("Speer");
		protocol.addAuthor(michael);
		protocol.setLicense(new URL("http://creativecommons.org/licenses/by-sa/3.0/"));

		// just roundtrip and hope we do not get an exception
		Assert.assertNotNull(roundtripSingleResource(protocol, true));
	}

	@Test
	public void testRoundtripLicense() throws MalformedURLException,IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL("http://example.org/doc/1"));
		protocol.setLicense(new URL("http://creativecommons.org/publicdomain/zero/1.0/"));

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals(
			"http://creativecommons.org/publicdomain/zero/1.0/",
			roundtripped.getLicense().toString()
		);
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Protocol testProtocol = new Protocol();
		testProtocol.setResourceURL(new URL(exampleProtocol));
		Protocol roundTrippedProtocol = roundtripSingleResource(testProtocol);
		Assert.assertEquals(
			exampleProtocol,
			roundTrippedProtocol.getResourceURL().toString()
		);
	}

	private Protocol roundtripSingleResource(Protocol testProtocol) throws IOException {
		return roundtripSingleResource(testProtocol, false);
	}

	private Protocol roundtripSingleResource(Protocol testProtocol, boolean full) throws IOException {
		ProtocolIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);
		OutputStream out = getResourceStream(testProtocol,
			full ? "full.n3" : "n3"
		);
		Serializer.toTurtle(out, model);
		out.close();
		List<Protocol> roundTrippedProtocols = ioClass.fromJena(model);
		Assert.assertEquals(1, roundTrippedProtocols.size());
		Protocol roundTrippedProtocol = roundTrippedProtocols.get(0);
		return roundTrippedProtocol;
	}

	@Test
	public void testRoundtripTitle() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setTitle("Title");

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals("Title", roundtripped.getTitle());
	}


	@Test
	public void testRoundtripTimestamp() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setTimeModified(1326788365L);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals(new Long(1326788365L),roundtripped.getTimeModified());
	}
	
	@Test
	public void testRoundtripSubmissionDate() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setSubmissionDate(1326788365L);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals(new Long(1326788365L),roundtripped.getSubmissionDate());
	}
	
	@Test
	public void testRoundtripStatus() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setStatus(STATUS.SOP);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals(STATUS.SOP, roundtripped.getStatus());
	}
	
	@Test
	public void testRoundtripTitle_Null() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setTitle(null);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertNull(roundtripped.getTitle());
	}

	@Test
	public void testRoundtripIdentifier() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setIdentifier("Title");

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals("Title", roundtripped.getIdentifier());
	}

	@Test
	public void testRoundtripIdentifier_Null() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setIdentifier(null);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals(null, roundtripped.getIdentifier());
	}

	@Test
	public void testRoundtripAbstract() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setAbstract("This\u2122 is the funniest abstract ever!");

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals("This\u2122 is the funniest abstract ever!", roundtripped.getAbstract());
	}

	@Test
	public void testRoundtripKeywords() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.addKeyword("key");
		protocol.addKeyword("word");

		Protocol roundtripped = roundtripSingleResource(protocol);

		List<String> keywords = roundtripped.getKeywords();
		Assert.assertNotNull(keywords);
		Assert.assertEquals(2, keywords.size());
		Assert.assertTrue(keywords.contains("key"));
		Assert.assertTrue(keywords.contains("word"));
	}

	@Test
	public void testRoundtripOrganization() throws MalformedURLException , IOException{
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		Organisation org = new Organisation();
		org.setTitle("TEST");
		org.setGroupName("test");		
		org.setResourceURL(new URL("http://www.toxbank.net/"));
		protocol.setOrganisation(org);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertNotNull(roundtripped.getOrganisation());
		Assert.assertEquals(
			"http://www.toxbank.net/",
			roundtripped.getOrganisation().getResourceURL().toString()
		);
		Assert.assertEquals(
				"test",
				roundtripped.getOrganisation().getGroupName()		
			);
			Assert.assertEquals(
					"TEST",
					roundtripped.getOrganisation().getTitle()		
			);			
	}

	@Test
	public void testRoundtripProject() throws MalformedURLException , IOException{
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		Project prj = new Project();
		prj.setTitle("Project");
		prj.setGroupName("project");
		prj.setResourceURL(new URL("http://www.toxbank.net/"));
		protocol.addProject(prj);
		
		prj = new Project();
		prj.setTitle("Project 2");
		prj.setGroupName("project");
		prj.setResourceURL(new URL("http://www.opentox.org/"));
		protocol.addProject(prj);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertNotNull(roundtripped.getProjects());
		Assert.assertEquals(2,roundtripped.getProjects().size());
		int count = 0;
		for (Project project : roundtripped.getProjects()) {
			Assert.assertNotNull(project);
			Assert.assertEquals("project",project.getGroupName());
			if ("http://www.toxbank.net/".equals(project.getResourceURL().toExternalForm())) {
				Assert.assertEquals("Project",project.getTitle());
				count ++;
			} else if ("http://www.opentox.org/".equals(project.getResourceURL().toExternalForm())) { 
				Assert.assertEquals("Project 2",project.getTitle());
				count++;	
			} else Assert.fail("Unknown URL " + project.getResourceURL());	
		}
		Assert.assertEquals(2,count);
	}	
	@Test
	public void testRoundtripOwner() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		User owner = new User();
		owner.setResourceURL(new URL("http://example.org/testUser/B.Bub"));
		protocol.setOwner(owner);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertNotNull(roundtripped.getOwner());
		Assert.assertEquals(
			"http://example.org/testUser/B.Bub",
			roundtripped.getOwner().getResourceURL().toString()
		);
	}

	@Test
	public void testRoundtripAuthors() throws MalformedURLException , IOException{
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		User bub = new User();
		bub.setFirstname("B");
		bub.setLastname("Bub");
		bub.setUserName("a");
		bub.setResourceURL(new URL("http://example.org/testUser/B.Bub"));
		protocol.addAuthor(bub);
		User adder = new User();
		adder.setFirstname("B");
		adder.setLastname("Adder");
		adder.setUserName("b");
		adder.setResourceURL(new URL("http://example.org/testUser/B.Adder"));
		protocol.addAuthor(adder);

		Protocol roundtripped = roundtripSingleResource(protocol);

		ToxBankResourceSet<User> authors = roundtripped.getAuthors();
		Assert.assertNotNull(authors);
		Assert.assertEquals(2, authors.size());
		
		for (User author: authors) {
			Assert.assertNotNull(author.getFirstname());
			Assert.assertNotNull(author.getLastname());
			Assert.assertNotNull(author.getUserName());
		}
		
		// we don't know the order
		Assert.assertTrue(
			roundtripped.getAuthors().get(0).getResourceURL().toString().equals("http://example.org/testUser/B.Bub") ||
			roundtripped.getAuthors().get(1).getResourceURL().toString().equals("http://example.org/testUser/B.Bub")
		);
		Assert.assertTrue(
				roundtripped.getAuthors().get(0).getResourceURL().toString().equals("http://example.org/testUser/B.Adder") ||
				roundtripped.getAuthors().get(1).getResourceURL().toString().equals("http://example.org/testUser/B.Adder")
		);
	}
	
	@Test
	public void testRoundtripDocument() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		Document doc = new Document();
		doc.setResourceURL(new URL("http://example.org/testDocument"));
		protocol.setDocument(doc);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertNotNull(roundtripped.getDocument());
		Assert.assertEquals(
			"http://example.org/testDocument",
			roundtripped.getDocument().getResourceURL().toString()
		);
	}	
	
	@Test
	public void testRoundtripDataTemplate() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		Template dataTemplate = new Template(new URL("http://example.org/testDataTemplate"));
		protocol.setDataTemplate(dataTemplate);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertNotNull(roundtripped.getDataTemplate());
		Assert.assertEquals(
			"http://example.org/testDataTemplate",
			roundtripped.getDataTemplate().getResourceURL().toString()
		);
	}		
	
	@Test
	public void testRoundtripSearchableFlag() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setResourceURL(new URL(exampleProtocol));
		protocol.setSearchable(true);

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertTrue(roundtripped.isSearchable());
	}	
	
	@Test
	public void testRoundtripVersion() throws MalformedURLException, IOException {
		Protocol protocol = new Protocol();
		protocol.setVersion(78);
		protocol.setResourceURL(new URL(exampleProtocol));

		Protocol roundtripped = roundtripSingleResource(protocol);

		Assert.assertEquals(78,roundtripped.getVersion());
	}	
}
