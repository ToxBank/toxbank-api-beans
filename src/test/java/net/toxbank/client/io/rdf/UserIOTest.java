package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Account;
import net.toxbank.client.resource.Organisation;
import net.toxbank.client.resource.Project;
import net.toxbank.client.resource.User;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class UserIOTest extends AbstractIOClassTest<User> {

	public UserIO getIOClass() {
		return new UserIO();
	}

	/**
	 * A full example, if only to get a rich .n3 document on Jenkins, and also
	 * see if a full example does not crash. No special assertions made.
	 */
	@Test
	public void testFullExample() throws Exception {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setTitle("Title");
		user.setHomepage(new URL("http://egonw.github.com/"));
		user.setWeblog(new URL("http://chem-bla-ics.blogspot.com/"));
		Account account = new Account();
		account.setService("http://friendfeed.com/");
		account.setAccountName("egonw");
		user.addAccount(account);
		account = new Account();
		account.setService("http://opentox.org/");
		account.setAccountName("bhardy");
		user.addAccount(account);
		user.setFirstname("Anna");
		user.setLastname("Johansson");

		// just roundtrip and hope we do not get an exception
		Assert.assertNotNull(roundtripSingleUser(user, true));
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		User testProtocol = new User();
		testProtocol.setResourceURL(new URL("http://example.org/testProtocol/666"));
		User roundTrippedProtocol = roundtripSingleUser(testProtocol);
		Assert.assertEquals(
			"http://example.org/testProtocol/666",
			roundTrippedProtocol.getResourceURL().toString()
		);
	}

	private User roundtripSingleUser(User testProtocol) throws IOException {
		return roundtripSingleUser(testProtocol, false);
	}

	private User roundtripSingleUser(User testProtocol, boolean full) throws IOException {
		UserIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testProtocol
		);

		List<User> roundTrippedUsers = ioClass.fromJena(model);
		OutputStream out = getResourceStream(testProtocol,
			full ? "full.n3" : "n3"
		);
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedUsers.size());
		User roundTrippedClass = roundTrippedUsers.get(0);
		return roundTrippedClass;
	}

	private List<User> roundtripUsers(User... users) throws IOException {
		UserIO ioClass = getIOClass();
		
		Model model = null;
		for (User user:users)
			 model = ioClass.toJena(model,user);

		List<User> roundTrippedUsers = ioClass.fromJena(model);
		Assert.assertEquals(users.length, roundTrippedUsers.size());
		return roundTrippedUsers;
	}	
	@Test
	public void testRoundtripTitle() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setTitle("Title");

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("Title", roundtripped.getTitle());
	}

	@Test
	public void testRoundtripHomepage() throws MalformedURLException , IOException{
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setHomepage(new URL("http://egonw.github.com/"));

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("http://egonw.github.com/", roundtripped.getHomepage().toString());
	}

	@Test
	public void testRoundtripWeblog() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setWeblog(new URL("http://chem-bla-ics.blogspot.com/"));

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("http://chem-bla-ics.blogspot.com/", roundtripped.getWeblog().toString());
	}

	@Test
	public void testRoundtripTitle_Null() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		user.setTitle(null);

		User roundtripped = roundtripSingleUser(user);

		Assert.assertNull(roundtripped.getTitle());
	}

	@Test
	public void testRoundtripAccount() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		Account account = new Account();
		account.setService("http://friendfeed.com/");
		account.setAccountName("egonw");
		user.addAccount(account);

		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals(1, roundtripped.getAccounts().size());
		Assert.assertEquals("egonw", roundtripped.getAccounts().get(0).getAccountName());
	}
	
	@Test
	public void testRoundtripFirstName() throws MalformedURLException , IOException{
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/AJ"));
		user.setFirstname("Anna");
		user.setLastname("Johansson");
		
		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals("Anna", roundtripped.getFirstname());
		Assert.assertEquals("Johansson", roundtripped.getLastname());
	}	
	
	@Test
	public void testRoundtripProject() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		
		String prefix = "http://example.org/testProject";
		Project project = new Project(new URL(String.format("%s/ABC",prefix)));
		project.setTitle("ABC");
		user.addProject(project);
		project = new Project(new URL(String.format("%s/XYZ",prefix)));
		user.addProject(project);
		project.setTitle("XYZ");
		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals(2, roundtripped.getProjects().size());
		for (Project p : roundtripped.getProjects()) {
			Assert.assertTrue(p.getResourceURL().toString().startsWith(prefix));
			Assert.assertEquals(String.format("%s/%s",prefix,p.getTitle()),p.getResourceURL().toString());
		}
	}	
	
	@Test
	public void testRoundtripOrganisation() throws MalformedURLException, IOException {
		User user = new User();
		user.setResourceURL(new URL("http://example.org/testUser/JohnDoe"));
		
		String prefix = "http://example.org/testOrganisation";
		Organisation org = new Organisation(new URL(String.format("%s/ABC",prefix)));
		org.setTitle("ABC");
		user.addOrganisation(org);
		org = new Organisation(new URL(String.format("%s/XYZ",prefix)));
		user.addOrganisation(org);
		org.setTitle("XYZ");
		
		Project project = new Project(new URL("http://example.org/testProject/XYZ"));
		user.addProject(project);
		project.setTitle("XYZ");
		
		User roundtripped = roundtripSingleUser(user);

		Assert.assertEquals(2, roundtripped.getOrganisations().size());
		for (Organisation p : roundtripped.getOrganisations()) {
			Assert.assertTrue(p.getResourceURL().toString().startsWith(prefix));
			Assert.assertEquals(String.format("%s/%s",prefix,p.getTitle()),p.getResourceURL().toString());
		}
	}	
	
	@Test
	public void testRoundtripMultiUsers() throws MalformedURLException, IOException {
		String prefixUser = "http://example.org/testUser";
		User user1 = new User();
		user1.setResourceURL(new URL(String.format("%s/%s",prefixUser,"JohnDoe")));
		
		String prefixOrg = "http://example.org/testOrganisation";
		String prefixProject = "http://example.org/testProject";
		Organisation org = new Organisation(new URL(String.format("%s/ABC",prefixOrg)));
		org.setTitle("ABC");
		user1.addOrganisation(org);
		org = new Organisation(new URL(String.format("%s/XYZ",prefixOrg)));
		user1.addOrganisation(org);
		org.setTitle("XYZ");
		
		Project project = new Project(new URL(String.format("%s/%s",prefixProject,"XYZ")));
		user1.addProject(project);
		project.setTitle("XYZ");
		//next user
		User user2 = new User();
		user2.setResourceURL(new URL(String.format("%s/%s",prefixUser,"Alice")));
		
		org = new Organisation(new URL(String.format("%s/ABC",prefixOrg)));
		org.setTitle("ABC");
		user2.addOrganisation(org);
		
		project = new Project(new URL(String.format("%s/%s",prefixProject,"QPR")));
		project.setTitle("QPR");	
		user2.addProject(project);
		
		List<User> roundtripped = roundtripUsers(new User[] {user1,user2});

		Assert.assertEquals(2, roundtripped.size());
		for (User user : roundtripped) {
			for (Organisation p : user.getOrganisations()) {
				Assert.assertTrue(p.getResourceURL().toString().startsWith(prefixOrg));
				Assert.assertEquals(String.format("%s/%s",prefixOrg,p.getTitle()),p.getResourceURL().toString());
			}
			Assert.assertEquals(1,user.getProjects().size());
			for (Project p : user.getProjects()) {
				Assert.assertTrue(p.getResourceURL().toString().startsWith(prefixProject));
				Assert.assertEquals(String.format("%s/%s",prefixProject,p.getTitle()),p.getResourceURL().toString());
				
			}	
			if (user.getResourceURL().toString().endsWith("Alice"))
				Assert.assertEquals(1,user.getOrganisations().size());
			else
				Assert.assertEquals(2,user.getOrganisations().size());
		}
	}		
}
