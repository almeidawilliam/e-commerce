package br.com.zupacademy.william.ecommerce.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProdutoImagemInputDto {

    @Size(min = 1, max = 7)
    @NotNull
    private List<MultipartFile> imagens;

    public ProdutoImagemInputDto(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
