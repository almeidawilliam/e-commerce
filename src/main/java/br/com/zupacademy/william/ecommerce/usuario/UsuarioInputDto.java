package br.com.zupacademy.william.ecommerce.usuario;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioInputDto {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Length(min = 8, max = 50)
    private String senha;

    public UsuarioInputDto(@NotBlank String login, @NotBlank @Email String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, new SenhaLimpa(this.senha));
    }
}
