/**
 * Copyright 2011 Leadscope, Inc. All rights reserved.
 * LEADSCOPE PROPRIETARY and CONFIDENTIAL. Use is subject to license terms.
 */
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
