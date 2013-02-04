package net.toxbank.client.io.rdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import net.toxbank.client.error.RemoteError;
import net.toxbank.client.resource.Task;
import net.toxbank.client.resource.Task.TaskStatus;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;


public class TaskIO extends AbstractIOClass<Task<URL, String>> {

	@Override
	public List<Task<URL, String>> fromJena(Model source) {
		if (source == null) return Collections.emptyList();
		return fromJena(source,source.listResourcesWithProperty(RDF.type, OPENTOX.TASK));
	}	

	@Override
	public Task<URL, String> fromJena(Model source, Resource res)
			throws IllegalArgumentException {
		Task<URL, String> task = new Task<URL, String>();
		try {
			task.setResourceURL(new URL(res.getURI()));
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(String.format(msg_InvalidURI,"OpenTox task",res.getURI())
			);
		}				
		if (res.getProperty(DC.title) != null)
			task.setTitle(res.getProperty(DC.title).getString());
		
		if (res.getProperty(DC.date) != null)
			task.setStarted(Long.parseLong(res.getProperty(DC.date).getLiteral().getString()));		

		if (res.getProperty(OPENTOX.HASSTATUS) != null) try {
			task.setStatus(TaskStatus.valueOf(res.getProperty(OPENTOX.HASSTATUS).getString()));
		} catch (Exception x) { throw new IllegalArgumentException(x);}

		if (res.getProperty(OPENTOX.resultURI) != null) try {
			task.setUri(new URL(res.getProperty(OPENTOX.resultURI).getString()));
		} catch (Exception x) { throw new IllegalArgumentException(x);}

		StmtIterator errreports = res.listProperties(OPENTOX.error);
		while (errreports.hasNext()) {
			Resource report = errreports.next().getResource();
			RemoteError error = new RemoteError();
			task.setError(error);
			if (report.getProperty(OPENTOX.errorCause) != null) try {
				error.setCause(report.getProperty(OPENTOX.errorCause).getString());
			} catch (Exception x) { throw new IllegalArgumentException(x);}

			if (report.getProperty(OPENTOX.errorCode) != null) try {
				error.setCode(report.getProperty(OPENTOX.errorCode).getLong());
			} catch (Exception x) { throw new IllegalArgumentException(x);}

			if (report.getProperty(OPENTOX.errorDetails) != null) try {
				error.setMessage(report.getProperty(OPENTOX.errorDetails).getString());
			} catch (Exception x) { throw new IllegalArgumentException(x);}

		}
		
		return task;
	}

	@Override
	public Resource objectToJena(Model toAddTo, Task<URL, String> object)
			throws IllegalArgumentException {
		return null;
	}


}
