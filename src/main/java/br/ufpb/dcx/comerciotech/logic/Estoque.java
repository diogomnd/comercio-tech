package br.ufpb.dcx.comerciotech.logic;

import br.ufpb.dcx.comerciotech.exceptions.EstoqueCheioException;
import br.ufpb.dcx.comerciotech.exceptions.EstoqueVazioException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoJaCadastradoException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoNaoEncontradoException;
import br.ufpb.dcx.comerciotech.recorder.GravadorDeDadosDoEstoque;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Estoque implements EstoqueInterface {

    private final Map<String, Produto> produtosNoEstoque;
    private final int capacidadeMaxima;
    private int nivelAtual;

    public Estoque() {
        this.produtosNoEstoque = new HashMap<>();
        this.capacidadeMaxima = 1000;
        this.nivelAtual = 0;
    }

    public Map<String, Produto> getProdutosNoEstoque() {
        return produtosNoEstoque;
    }

    public int getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(int nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public void atualizarNivelDoEstoque(int quantAMais) {
        this.nivelAtual += quantAMais;
    }

    public void adicionarProduto(String id, Produto produto, int quantidade)
            throws ProdutoJaCadastradoException, EstoqueCheioException {
        if (produtosNoEstoque.containsKey(id))
            throw new ProdutoJaCadastradoException(id);
        if (nivelAtual == capacidadeMaxima)
            throw new EstoqueCheioException(capacidadeMaxima);
        produtosNoEstoque.put(id, produto);
        atualizarNivelDoEstoque(quantidade);
    }

    public void removerProduto(String id) throws ProdutoNaoEncontradoException, EstoqueVazioException {
        if (!produtosNoEstoque.containsKey(id))
            throw new ProdutoNaoEncontradoException(id);
        if (nivelAtual == 0)
            throw new EstoqueVazioException();
        Produto produto = produtosNoEstoque.get(id);
        atualizarNivelDoEstoque(-produto.getQuantidade());
        produtosNoEstoque.remove(id);
    }

    public double consultarPrecoDoProduto(String id) throws ProdutoNaoEncontradoException {
        if (!produtosNoEstoque.containsKey(id))
            throw new ProdutoNaoEncontradoException(id);
        return produtosNoEstoque.get(id).getPreco();
    }

    public boolean verificarDisponibilidade(String id, int quantidade) throws ProdutoNaoEncontradoException {
        if (!produtosNoEstoque.containsKey(id))
            throw new ProdutoNaoEncontradoException(id);
        Produto produto = produtosNoEstoque.get(id);
        int quantidadeNoEstoque = produto.getQuantidade();
        return quantidade <= quantidadeNoEstoque;
    }

    public Produto procurarProduto(String id) throws ProdutoNaoEncontradoException {
        if (!produtosNoEstoque.containsKey(id))
            throw new ProdutoNaoEncontradoException(id);
        return produtosNoEstoque.get(id);
    }

    public void gerarRelatorio(String nomeArquivo) {
        GravadorDeDadosDoEstoque gravador = new GravadorDeDadosDoEstoque();
        try (PrintWriter printWriter = new PrintWriter(nomeArquivo)) {
            printWriter.println("Relatório do estoque");
            printWriter.println("Data: " + LocalDate.now());
            printWriter.println();
            Estoque estoque = gravador.recuperarDadosDoEstoque();
            if (estoque == null) estoque = this;
            printWriter.println(estoque);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Nível atual do estoque: ").append(nivelAtual).append("\n");
        builder.append("Produtos no estoque: ").append("\n");
        int t = produtosNoEstoque.size();
        int i = 0;
        for (Produto p : produtosNoEstoque.values()) {
            builder.append(p);
            if (i != t - 1)
                builder.append("\n\n");
            i++;
        }
        return builder.toString();
    }
}