package com.ecommerce.produtoapi.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document (collection = "Produto")
public class Produto {
    @Transient
    public static final String SEQUENCE_NAME = "products_sequence";

    @ApiModelProperty(notes="Id do produto", hidden = true)
    @Id
    private long id;

    @ApiModelProperty(notes="Código do produto", name="codigo", value="COD001")
    @NonNull
    @Indexed(unique = true)
    private String codigo;

    @ApiModelProperty(notes="Nome do produto", name="nome", value="Camisa do Flamengo")
    @NonNull
    @Indexed(unique = true)
    private String nome;
    
    @ApiModelProperty(notes="Preço do produto", name="preco", value="299.99")
    @NonNull
    private Double preco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto [codigo=" + codigo + ", id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
    }
    
}
