package br.com.zupacademy.william.ecommerce.email;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailFakeListener {

    @Async
    @EventListener
    public void enviaEmail(Email email) {
        System.out.println("Corpo do Email: " + email.getTitulo());
        System.out.println("De: " + email.getRemetente());
        System.out.println("Para: " + email.getDestinatario());
        System.out.println("---------------------------------------------------------------------------------");
    }
}
