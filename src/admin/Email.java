package admin;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

    Session newSession = null;
    MimeMessage mimeMessage = null;

    public static void main(String args[]) throws AddressException, MessagingException, IOException {
        Email mail = new Email();
        mail.setupServerProperties();
    }
    
    public void sendVerificationCode(String recipientEmail, String verificationCode) {
        try {
            setupServerProperties();
            draftVerificationEmail(recipientEmail, verificationCode);
            sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MimeMessage draftVerificationEmail(String recipientEmail, String verificationCode) throws AddressException, MessagingException {
        String emailSubject = "Password Reset";
        String emailBody = "Your verification code is: " + verificationCode;
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        mimeMessage.setSubject(emailSubject);
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/plain");
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }


    private void sendEmail() throws MessagingException {
        String fromUser = "hallsymphonymy@gmail.com";  // Sender email
        String fromUserPassword = "mdpjpncpntzupkhc";  // Sender email password
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent!!!");
    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }
}
