package br.org.serratec.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;



    public String enviarEmailTexto(String destinatario,String assunto, String mensagem) {

        try {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
             System.out.println("TESTANDO EMAIL");
                simpleMailMessage.setFrom(remetente);
                simpleMailMessage.setTo(destinatario);
                simpleMailMessage.setSubject(assunto);
                simpleMailMessage.setText(mensagem);
                javaMailSender.send(simpleMailMessage);
                System.out.println(remetente);
                return "Email enviado";
            }catch (Exception e) {
            	System.out.println("TESTANDO EMAIL 2");
                return "Error ao tentar enviar email" + e.getLocalizedMessage();

            }
    }
}
