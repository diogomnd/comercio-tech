package br.ufpb.dcx.comerciotech;

import br.ufpb.dcx.comerciotech.exceptions.EstoqueCheioException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoJaCadastradoException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoNaoEncontradoException;
import br.ufpb.dcx.comerciotech.logic.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompraTest {
    Carrinho carrinho;
    Estoque estoque;
    Compra compra;

    @BeforeEach
    public void setup() {
        this.carrinho = new Carrinho();
        this.estoque = new Estoque();
        this.compra = new Compra(carrinho.calculaTotal());
    }

    @Test
    public void processarCompra() throws ProdutoJaCadastradoException, EstoqueCheioException, ProdutoNaoEncontradoException {
        Produto produto1 = new Produto("Iphone", "123", "Apple", Departamento.CELULAR, 5000, 100);
        Produto produto2 = new Produto("Xbox", "2", "Microsoft", Departamento.CONSOLE, 2700, 100);
        estoque.adicionarProduto(produto1.getIdProduto(), produto1, produto1.getQuantidade());
        estoque.adicionarProduto(produto2.getIdProduto(), produto2, produto2.getQuantidade());
        carrinho.adicionarProdutoNoCarrinho(estoque, produto1, 2);
        carrinho.adicionarProdutoNoCarrinho(estoque, produto2, 1);
        System.out.println(estoque.getNivelAtual());
        assertEquals(3, carrinho.getProdutosNoCarrinho().size());
        compra.processarCompra(estoque, carrinho);
        assertFalse(estoque.verificarDisponibilidade("123", 100));
        assertEquals(197, estoque.getNivelAtual());

    }
}