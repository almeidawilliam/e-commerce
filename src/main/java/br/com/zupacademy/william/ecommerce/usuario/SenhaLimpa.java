package br.com.zupacademy.william.ecommerce.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Representa uma senha limpa no sistema
 */
public class SenhaLimpa {

    private String senha;

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
