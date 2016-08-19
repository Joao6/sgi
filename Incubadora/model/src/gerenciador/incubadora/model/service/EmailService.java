package gerenciador.incubadora.model.service;

import gerenciador.incubadora.model.base.service.BaseEmailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailService implements BaseEmailService {

    @Override
    public void sendEmail(String destino, String assunto, String texto) throws Exception {
//
//        Properties props = new Properties();
//
//        //Parâmetros de conexão com servidor Gmail
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("escambov@gmail.com", "escambo123");
//                    }
//                });
//
//        //Ativa Debug para sessão
//        session.setDebug(true);
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("escambov@gmail.com")); //Remetente
//
//            Address[] toUser = InternetAddress.parse(email);//Destinatários
////            Transport trans = session.getTransport(toUser[0]);//teste
//            message.setRecipients(Message.RecipientType.TO, toUser);
//            message.setSubject(assunto);//Assunto
//            message.setText(texto);
//
////            trans.addConnectionListener((ConnectionListener) this);//teste
////            trans.addTransportListener((TransportListener) this);//teste
////            trans.connect();//teste
//            //Método para enviar a mensagem criada
//            Transport.send(message);
//           // session.getTransport().sendMessage(message, toUser);
//            System.out.println("alou");
//            //assertTrue(.getReceivedEmailSize() == 1);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }

        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        //email.setSmtpPort(587);
        email.setStartTLSRequired(true);
        email.setAuthenticator(new DefaultAuthenticator("escambov@gmail.com", "escambo123"));
        email.setSSLOnConnect(true);
        email.setSubject(assunto);
        try {
            email.setFrom("escambov@gmail.com");
            email.setMsg(texto);
            email.addTo(destino);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
