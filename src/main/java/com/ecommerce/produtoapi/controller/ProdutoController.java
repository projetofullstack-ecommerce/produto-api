package com.ecommerce.produtoapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ecommerce.produtoapi.exception.ResourceNotFoundException;
import com.ecommerce.produtoapi.model.Produto;
import com.ecommerce.produtoapi.repository.ProdutoRepository;
import com.ecommerce.produtoapi.service.SequenceGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/produtos")
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> getAllProdutoById(@PathVariable(value = "id") long produtoId)
            throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado! Id: " + produtoId));

        return ResponseEntity.ok(produto);
    }

    @PostMapping("/produtos")
    public Produto createProduto(@Valid @RequestBody Produto produto) {
        produto.setId(sequenceGeneratorService.generateSequence(Produto.SEQUENCE_NAME));
        return produtoRepository.save(produto);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> updateProdutos(@PathVariable(value = "id") Long produtoId,
            @Valid @RequestBody Produto produtoDetails) throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado! Id: " + produtoId));

        produto.setCodigo(Optional.ofNullable(produtoDetails.getCodigo()).orElse(produto.getCodigo()));
        produto.setNome(Optional.ofNullable(produtoDetails.getNome()).orElse(produto.getNome()));
        produto.setPreco(Optional.ofNullable(produtoDetails.getPreco()).orElse(produto.getPreco()));

        Produto updateProduto = produtoRepository.save(produto);

        return ResponseEntity.ok(updateProduto);
    }

    @DeleteMapping("/produtos/{id}")
    public Map<String, Boolean> deleteProduto(@PathVariable(value = "id") Long produtoId)
            throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado! Id: " + produtoId));

        produtoRepository.delete(produto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
