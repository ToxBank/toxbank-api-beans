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
			Assert.assertEquals(1,tasks.size());
			for (Task<URL,String> task : tasks) {
				Assert.assertEquals("Apply ",
							task.getTitle());
				Assert.assertNotNull(task.getError());
				Assert.assertEquals("500",task.getError().getCode());
				Assert.assertEquals("The server encountered an unexpected condition which prevented it from fulfilling the request",task.getError().getMessage());
				Assert.assertTrue(task.getError().getCause().startsWith("java.lang.NullPointerException"));
				Assert.assertEquals("http://localhost:8080/toxbank/task/f8726f32-36c7-4c35-8a0d-42b6fed70e80",task.getUri().toString());
				Assert.assertEquals(1360092101859L,task.getStarted());
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
			Assert.assertEquals(1,tasks.size());
			for (Task<URL,String> task : tasks) {
				Assert.assertEquals("Apply ",task.getTitle());
				Assert.assertNull(task.getError());
				Assert.assertEquals("http://localhost:8080/toxbank/protocol/SEURAT-Protocol-193-1",task.getUri().toString());
				Assert.assertEquals(1360089459796L,task.getStarted());
				Assert.assertEquals("Completed",task.getStatus().name());
			}
		} finally {
			if (in !=null) in.close();
			if (model !=null) model.close();
		}
		
	}	
	
	@Test
	public void testParseTaskInvestigation() throws MalformedURLException , IOException{
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("net/toxbank/client/io/rdf/test/task_errorinv.rdf");
		Assert.assertNotNull(in);
		Model model = null;
		try {
			model = ModelFactory.createDefaultModel();
			model.read(new InputStreamReader(in,"UTF-8"),OPENTOX.URI);
			TaskIO taskIO = new TaskIO();
			List<Task<URL,String>> tasks = taskIO.fromJena(model);
			Assert.assertEquals(1,tasks.size());
			for (Task<URL,String> task : tasks) {
				System.out.println(task.getTitle());
				System.out.println(task.getError().getCause());
				System.out.println(task.getError().getMessage());
				Assert.assertEquals("Default",task.getTitle());
				Assert.assertNotNull(task.getError());
				Assert.assertNotNull(task.getError().getCode());
				Assert.assertNotNull(task.getError().getMessage());
				Assert.assertNotNull(task.getError().getCause());
			}
		} finally {
			if (in !=null) in.close();
			if (model !=null) model.close();
		}
		
	}		
}
