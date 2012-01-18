package net.toxbank.client.resource;

import java.net.URL;

public class Document extends AbstractToxBankResource {

	private static final long serialVersionUID = 7166009221679269640L;
	protected String mediaType = "application/pdf";

	public Document() {
		this(null);
	}
	public Document(URL resourceURL) {
		super(resourceURL);
		setMediaType(guessMediaType());
	}

	protected String guessMediaType() {
		if (getResourceURL()==null) return null;
		int dot = getResourceURL().toString().lastIndexOf(".");
		String ext = dot<0?null:getResourceURL().toString().substring(dot+1).toLowerCase();
		if ("pdf".equals(ext)) return "application/pdf";
		else if ("pdf".equals(ext)) return "application/pdf";
		else if ("doc".equals(ext)) return "application/msword";
		else if ("docx".equals(ext)) return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		else if ("pptx".equals(ext)) return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
		else if ("ppt".equals(ext)) return "application/vnd.ms-powerpoint";
		else if ("xls".equals(ext)) return "application/vnd.ms-excel";
		else if ("xlsx".equals(ext)) return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		else if ("tex".equals(ext)) return "application/x-latex";
		else if ("odt".equals(ext)) return "application/vnd.oasis.opendocument.text";
		else if ("txt".equals(ext)) return "text/plain";
		else if ("tar".equals(ext)) return "application/x-tar";
		else if ("zip".equals(ext)) return "application/x-zip";
		else return "application/octet-stream";
	}
	public Document(URL resourceURL,String mediaType) {
		super(resourceURL);
		setMediaType(mediaType);
	}	
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
}
