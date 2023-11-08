package br.ufpb.dcx.comerciotech.logic;

import br.ufpb.dcx.comerciotech.exceptions.EstoqueCheioException;
import br.ufpb.dcx.comerciotech.exceptions.EstoqueVazioException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoJaCadastradoException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoNaoEncontradoException;

import java.io.Serializable;

public interface EstoqueInterface extends Serializable {
    void adicionarProduto(String id, Produto produto, int quantidade) throws ProdutoJaCadastradoException, EstoqueCheioException;

    void removerProduto(String id) throws ProdutoNaoEncontradoException, EstoqueVazioException;

    double consultarPrecoDoProduto(String id) throws ProdutoNaoEncontradoException;

    boolean verificarDisponibilidade(String id, int quantidade) throws ProdutoNaoEncontradoException;

    Produto procurarProduto(String id) throws ProdutoNaoEncontradoException;

    void gerarRelatorio(String nomeDoArquivo);

}
