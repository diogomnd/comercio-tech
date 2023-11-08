package br.ufpb.dcx.comerciotech.Test;

import br.ufpb.dcx.comerciotech.exceptions.EstoqueCheioException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoJaCadastradoException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoNaoEncontradoException;
import br.ufpb.dcx.comerciotech.logic.Carrinho;
import br.ufpb.dcx.comerciotech.logic.Departamento;
import br.ufpb.dcx.comerciotech.logic.Estoque;
import br.ufpb.dcx.comerciotech.logic.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {
    Carrinho carrinho;
    Estoque estoque;
    @BeforeEach
    public void setup(){
       this.carrinho = new Carrinho();
       this.estoque = new Estoque();
    }
    @Test
    public void adicionarRemoverEImprimir() throws ProdutoNaoEncontradoException, ProdutoJaCadastradoException, EstoqueCheioException {

        assertEquals(0,carrinho.getProdutosNoCarrinho().size());
        Produto produto1 = new Produto("Iphone","123","Apple",Departamento.CELULAR,5000,100);
        Produto produto2 = new Produto("Xbox","2","Microsoft",Departamento.CONSOLE,2700,100);
        estoque.adicionarProduto(produto1.getIdProduto(),produto1,produto1.getQuantidade());
        estoque.adicionarProduto(produto2.getIdProduto(),produto2,produto2.getQuantidade());
        carrinho.adicionarProdutoNoCarrinho(estoque,produto1,1);
        carrinho.adicionarProdutoNoCarrinho(estoque,produto2,1);
        assertEquals(2,carrinho.getProdutosNoCarrinho().size());
        System.out.println(carrinho.toString());
        carrinho.removerProdutoNoCarrinho(produto2,1);
        assertEquals(1,carrinho.getProdutosNoCarrinho().size());
        System.out.println(carrinho.toString());
    }
}