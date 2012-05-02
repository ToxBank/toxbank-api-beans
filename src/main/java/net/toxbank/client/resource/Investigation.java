package net.toxbank.client.resource;

import java.net.URL;

/**
 * An investigation as represented in the toxbank repository
 */
public class Investigation extends AbstractToxBankResource {
  private static final long serialVersionUID = -1189656198870429173L;
 
  public Investigation() {}
  
  public Investigation(URL identifier) {
    setResourceURL(identifier);
  }

}
