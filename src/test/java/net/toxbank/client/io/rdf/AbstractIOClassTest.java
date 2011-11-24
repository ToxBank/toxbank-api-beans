package net.toxbank.client.io.rdf;

import java.util.List;

import net.toxbank.client.resource.IToxBankResource;

import org.junit.Assert;
import org.junit.Test;

import com.hp.hpl.jena.rdf.model.Model;

public abstract class AbstractIOClassTest<T extends IToxBankResource> {

	public IOClass<T> getIOClass() {
		Assert.fail("This method must be overwritten");
		return null;
	}

	@Test
	public void testFromJena_AcceptNullModel() {
		IOClass<T> ioClass = getIOClass();
		List<T> results = ioClass.fromJena(null);
		Assert.assertNotNull(results);
		Assert.assertEquals(0, results.size());
	}

	@Test
	public void testToJena_AcceptNullModel() {
		IOClass<T> ioClass = getIOClass();
		Model results = ioClass.toJena(null, (T[])null);
		Assert.assertNotNull(results);
	}

}
