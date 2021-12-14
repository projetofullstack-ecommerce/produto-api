package com.ecommerce.produtoapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document (collection = "Produto")
public class Produto {
    @Transient
    public static final String SEQUENCE_NAME = "products_sequence";

    @Id
    private long id;

    @NonNull
    @Indexed(unique = true)
    private String codigo;

    @NonNull
    @Indexed(unique = true)
    private String nome;
    
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
