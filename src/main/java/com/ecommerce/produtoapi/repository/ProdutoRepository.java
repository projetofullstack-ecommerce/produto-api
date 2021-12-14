package com.ecommerce.produtoapi.repository;

import com.ecommerce.produtoapi.model.Produto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto,Long> {
    
}
