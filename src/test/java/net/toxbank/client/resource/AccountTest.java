package net.toxbank.client.resource;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
	
	@Test
	public void testConstructor() {
		Account account = new Account();
		Assert.assertNotNull(account);
	}

	@Test
	public void testGetSetService() {
		Account account = new Account();
		Assert.assertNull(account.getService());
		account.setService("Twitter");
		Assert.assertEquals("Twitter", account.getService());
	}

	@Test
	public void testGetSetAccountName() {
		Account account = new Account();
		Assert.assertNull(account.getAccountName());
		account.setAccountName("egonwillighagen");
		Assert.assertEquals("egonwillighagen", account.getAccountName());
	}
}
