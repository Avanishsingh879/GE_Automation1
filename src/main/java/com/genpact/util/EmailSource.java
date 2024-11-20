package com.genpact.util;

public class EmailSource {
	
	private String recipientEmail;
	private String senderEmail;
	private String senderEmailPwd;
	private String emailSMTPServerHost;
	private int emailSMTPServerPort;
	private String emailPOPServerHost;
	private int emailPOPServerPort;
	
	public EmailSource()
	{}
	
	public EmailSource(String recipientEmail, String senderEmail, String senderEmailPwd, String emailSMTPServerHost,
			int emailSMTPServerPort, String emailPOPServerHost, int emailPOPServerPort) {
		this.recipientEmail = recipientEmail;
		this.senderEmail = senderEmail;
		this.senderEmailPwd = senderEmailPwd;
		this.emailSMTPServerHost = emailSMTPServerHost;
		this.emailSMTPServerPort = emailSMTPServerPort;
		this.emailPOPServerHost = emailPOPServerHost;
		this.emailPOPServerPort = emailPOPServerPort;
	}
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getSenderEmailPwd() {
		return senderEmailPwd;
	}
	public void setSenderEmailPwd(String senderEmailPwd) {
		this.senderEmailPwd = senderEmailPwd;
	}
	public String getEmailSMTPServerHost() {
		return emailSMTPServerHost;
	}
	public void setEmailSMTPServerHost(String emailSMTPServerHost) {
		this.emailSMTPServerHost = emailSMTPServerHost;
	}
	public int getEmailSMTPServerPort() {
		return emailSMTPServerPort;
	}
	public void setEmailSMTPServerPort(int emailSMTPServerPort) {
		this.emailSMTPServerPort = emailSMTPServerPort;
	}
	public String getEmailPOPServerHost() {
		return emailPOPServerHost;
	}
	public void setEmailPOPServerHost(String emailPOPServerHost) {
		this.emailPOPServerHost = emailPOPServerHost;
	}
	public int getEmailPOPServerPort() {
		return emailPOPServerPort;
	}
	public void setEmailPOPServerPort(int emailPOPServerPort) {
		this.emailPOPServerPort = emailPOPServerPort;
	}
	
	

}
