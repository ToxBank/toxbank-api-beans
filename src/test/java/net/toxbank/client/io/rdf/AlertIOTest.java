package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Alert;
import net.toxbank.client.resource.Alert.RecurrenceFrequency;
import net.toxbank.client.resource.Query.QueryType;
import net.toxbank.client.resource.User;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public class AlertIOTest extends AbstractIOClassTest<Alert> {

	public AlertIO getIOClass() {
		return new AlertIO();
	}

	@Test
	public void testRoundtripResourceURI() throws Exception {
		Alert testResource = new Alert();
		testResource.setTitle("title");
		testResource.setResourceURL(new URL("http://example.org/alerts/1"));
		testResource.setUser(new User(new URL("http://example.org/user/1")));
		testResource.setQueryString("cell");
		testResource.setType(QueryType.FREETEXT);
		testResource.setRecurrenceFrequency(RecurrenceFrequency.daily);
		testResource.setRecurrenceInterval(3);
		
		Alert roundTrippedResource = roundtripSingleAlert(testResource);
		Assert.assertEquals(
			"http://example.org/alerts/1",
			roundTrippedResource.getResourceURL().toString()
		);
		Assert.assertEquals(
				"http://example.org/user/1",
				roundTrippedResource.getUser().getResourceURL().toString()
			);
		Assert.assertEquals(
				"title",
				roundTrippedResource.getTitle()
			);				
		Assert.assertEquals(
				"cell",
				roundTrippedResource.getQueryString()
			);		
		Assert.assertEquals(
				QueryType.FREETEXT,
				roundTrippedResource.getType()
			);		
		Assert.assertEquals(
				RecurrenceFrequency.daily,
				roundTrippedResource.getRecurrenceFrequency()
			);			
		Assert.assertEquals(
				3,
				roundTrippedResource.getRecurrenceInterval()
			);				
	}

	private Alert roundtripSingleAlert(Alert testAlert) throws IOException {
		AlertIO ioClass = getIOClass();
		
		Model model = ioClass.toJena(
			null, // create a new class
			testAlert
		);

		List<Alert> roundTrippedResources = ioClass.fromJena(model);
		
		OutputStream out = getResourceStream(testAlert,"n3");
		Serializer.toTurtle(out, model);
		out.close();
		Assert.assertEquals(1, roundTrippedResources.size());
		Alert roundTrippedClass = roundTrippedResources.get(0);
		return roundTrippedClass;
	}

}
