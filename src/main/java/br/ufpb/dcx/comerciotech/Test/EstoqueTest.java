package br.ufpb.dcx.comerciotech.Test;

import br.ufpb.dcx.comerciotech.exceptions.EstoqueCheioException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoJaCadastradoException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoNaoEncontradoException;
import br.ufpb.dcx.comerciotech.logic.Departamento;
import br.ufpb.dcx.comerciotech.logic.Estoque;
import br.ufpb.dcx.comerciotech.logic.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueTest {
    Estoque estoque;
    @BeforeEach
    public void setup(){
        this.estoque =  new Estoque();
    }
    @Test
    public void adicionarRemoverEImprimir() throws ProdutoJaCadastradoException, EstoqueCheioException {
        assertEquals(0,estoque.getProdutosNoEstoque().size());
        Produto produto1 = new Produto("Iphone","123","Apple", Departamento.CELULAR,5000,100);
        Produto produto2 = new Produto("Xbox","2","Microsoft",Departamento.CONSOLE,2700,100);
        estoque.adicionarProduto(produto1.getIdProduto(),produto1,produto1.getQuantidade());
        assertEquals(1,estoque.getProdutosNoEstoque().size());
        System.out.println(estoque.toString());
        estoque.adicionarProduto(produto2.getIdProduto(),produto2,produto2.getQuantidade());
        assertEquals(2,estoque.getProdutosNoEstoque().size());
        System.out.println(estoque.toString());
    }

    @Test
    public void verfificarDisponibilidade() throws ProdutoJaCadastradoException, EstoqueCheioException, ProdutoNaoEncontradoException {
        Produto produto1 = new Produto("Iphone","123","Apple", Departamento.CELULAR,5000,100);
        estoque.adicionarProduto(produto1.getIdProduto(),produto1,produto1.getQuantidade());
        assertFalse(estoque.verificarDisponibilidade("123",1000));
        assertTrue(estoque.verificarDisponibilidade("123",1));
    }
    @Test
    public void consultarPreco() throws ProdutoJaCadastradoException, EstoqueCheioException, ProdutoNaoEncontradoException {
        Produto produto1 = new Produto("Iphone","123","Apple", Departamento.CELULAR,5000,100);
        Produto produto2 = new Produto("Xbox","2","Microsoft",Departamento.CONSOLE,2700,100);
        estoque.adicionarProduto(produto1.getIdProduto(),produto1,produto1.getQuantidade());
        estoque.adicionarProduto(produto2.getIdProduto(),produto2,produto2.getQuantidade());
        assertEquals(5000,estoque.consultarPrecoDoProduto("123"));
        assertEquals(2700,estoque.consultarPrecoDoProduto("2"));
    }

    @Test
    public void procurarProduto() throws ProdutoJaCadastradoException, EstoqueCheioException, ProdutoNaoEncontradoException {
        Produto produto1 = new Produto("Iphone","123","Apple", Departamento.CELULAR,5000,100);
        Produto produto2 = new Produto("Xbox","2","Microsoft",Departamento.CONSOLE,2700,100);
        estoque.adicionarProduto(produto1.getIdProduto(),produto1,produto1.getQuantidade());
        estoque.adicionarProduto(produto2.getIdProduto(),produto2,produto2.getQuantidade());
        assertEquals(produto1,estoque.procurarProduto("123"));
        assertEquals(produto2,estoque.procurarProduto("2"));
    }
}