package br.ufpb.dcx.comerciotech.logic;

import br.ufpb.dcx.comerciotech.exceptions.ProdutoNaoEncontradoException;
import br.ufpb.dcx.comerciotech.recorder.GravadorDeDadosDoEstoque;

import java.text.DecimalFormat;
import java.util.*;

public class Carrinho {

    private List<Produto> produtosNoCarrinho;

    public Carrinho() {
        this.produtosNoCarrinho = new ArrayList<>();
    }

    public void adicionarProdutoNoCarrinho(Estoque estoque, Produto produto, int quantidade)
            throws ProdutoNaoEncontradoException {
        boolean disponivel = estoque.verificarDisponibilidade(produto.getIdProduto(), quantidade);
        if (disponivel)
            for (int i = 0; i < quantidade; i++)
                produtosNoCarrinho.add(produto);
    }

    public void removerProdutoNoCarrinho(String id, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (produtosNoCarrinho.get(i).getIdProduto().equals(id))
                produtosNoCarrinho.remove(produtosNoCarrinho.get(i));
        }
    }

    public void limparCarrinho() {
        produtosNoCarrinho.clear();
    }

    public List<Produto> getProdutosNoCarrinho() {
        return produtosNoCarrinho;
    }

    public int getQuantidadeTotal() {
        return produtosNoCarrinho.size();
    }

    public double calculaTotal() {
        double total = 0;
        for (Produto p : produtosNoCarrinho)
            total += p.getPreco();
        return total;
    }

    private double calculaTotalDoProduto(Produto produto, int quantidade) {
        double total = 0;
        total += produto.getPreco();
        return total * quantidade;
    }

    public Set<Produto> retornarProdutosSemDuplicatas() {
        return new HashSet<>(produtosNoCarrinho);
    }

    public int contarOcorrenciasNoCarrinho(Produto produtoParaContar) {
        if (produtosNoCarrinho.contains(produtoParaContar))
            return Collections.frequency(produtosNoCarrinho, produtoParaContar);
        return 0;
    }

    private String valorFormatado(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(valor);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<Produto> produtosSemDuplicatas = retornarProdutosSemDuplicatas();
        builder.append("Produtos no carrinho: \n");
        for (Produto p : produtosSemDuplicatas) {
            int quantidade = contarOcorrenciasNoCarrinho(p);
            double precoTotalDoProduto = calculaTotalDoProduto(p, quantidade);
            builder.append(quantidade).append("x ").append(p.getNomeProduto());
            builder.append(" -> R$ ").append(valorFormatado(precoTotalDoProduto)).append("\n");
        }
        builder.append("Preço total do carrinho: R$ ").append(valorFormatado(calculaTotal()));
        return builder.toString();
    }


}