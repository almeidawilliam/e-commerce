package br.com.zupacademy.william.ecommerce.categoria;

import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_categoria_mae")
    private Categoria categoriaMae;

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Categoria() {
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
