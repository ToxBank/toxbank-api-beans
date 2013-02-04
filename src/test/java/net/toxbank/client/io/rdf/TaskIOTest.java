package net.toxbank.client.io.rdf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import net.toxbank.client.resource.Task;

import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TaskIOTest extends AbstractIOClassTest<Task<URL, String>> {
	@Override
	public IOClass<Task<URL, String>> getIOClass() {
		return new TaskIO();
	}
	
	@Test
	public void testParseTaskWithError() throws MalformedURLException , IOException{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("net/toxbank/client/io/rdf/test/task_error.rdf");
		Assert.assertNotNull(in);
		Model model = null;
		try {
			model = ModelFactory.createDefaultModel();
			model.read(new InputStreamReader(in,"UTF-8"),OPENTOX.URI);
			TaskIO taskIO = new TaskIO();
			List<Task<URL,String>> tasks = taskIO.fromJena(model);
			for (Task<URL,String> task : tasks) {
				Assert.assertEquals("Apply Sleeps for 'delay' milliseconds, returns 'dataset_uri' or 'model_uri', specified on input. For testing purposes ",
							task.getTitle());
				Assert.assertNotNull(task.getError());
				Assert.assertEquals(400,task.getError().getCode());
				Assert.assertEquals("No result URI specified",task.getError().getMessage());
				Assert.assertTrue(task.getError().getCause().startsWith("Bad Request (400) - No result URI specified"));
				Assert.assertEquals("http://apps.ideaconsult.net:8080/ambit2/task/f696a017-3756-4769-bd9d-97e8afa0cf21",task.getUri().toString());
				Assert.assertEquals(1359722484666L,task.getStarted());
				Assert.assertEquals("Error",task.getStatus().name());
			}
		} finally {
			if (in !=null) in.close();
			if (model !=null) model.close();
		}
	}
	
	@Test
	public void testParseTaskSuccess() throws MalformedURLException , IOException{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("net/toxbank/client/io/rdf/test/task_ok.rdf");
		Assert.assertNotNull(in);
		Model model = null;
		try {
			model = ModelFactory.createDefaultModel();
			model.read(new InputStreamReader(in,"UTF-8"),OPENTOX.URI);
			TaskIO taskIO = new TaskIO();
			List<Task<URL,String>> tasks = taskIO.fromJena(model);
			for (Task<URL,String> task : tasks) {
				Assert.assertEquals("Apply   ",task.getTitle());
				Assert.assertNull(task.getError());
				Assert.assertEquals("http://localhost:8080/toxbank/protocol/SEURAT-Protocol-192-1",task.getUri().toString());
				Assert.assertEquals(1359971397345L,task.getStarted());
				Assert.assertEquals("Completed",task.getStatus().name());
			}
		} finally {
			if (in !=null) in.close();
			if (model !=null) model.close();
		}
	}	
}
