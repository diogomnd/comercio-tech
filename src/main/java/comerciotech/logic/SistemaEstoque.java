package comerciotech.logic;

import comerciotech.recorder.GravadorDeDadosDoEstoque;
import comerciotech.exceptions.EstoqueCheioException;
import comerciotech.exceptions.ProdutoJaCadastradoException;
import comerciotech.exceptions.ProdutoNaoEncontradoException;

import java.util.*;

public class SistemaEstoque implements EstoqueInterface {

    private Map<String, Produto> produtosNoEstoque;
    private final int capacidadeMaxima;
    private int nivelAtual;
    private GravadorDeDadosDoEstoque gravador = new GravadorDeDadosDoEstoque();

    public SistemaEstoque() {
        this.produtosNoEstoque = new HashMap<>();
        this.capacidadeMaxima = 1000;
        this.nivelAtual = 0;
        recuperarDados();
    }

    public void salvarDados(){
        try {
            this.gravador.salvarDadosDoEstoque(this.produtosNoEstoque);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void recuperarDados(){
        try {
            this.produtosNoEstoque = this.gravador.recuperarDadosDoEstoque();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
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

    public void removerProduto(String id) throws ProdutoNaoEncontradoException {
        if (!produtosNoEstoque.containsKey(id))
            throw new ProdutoNaoEncontradoException(id);
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

    public List<Produto> listarProdutosPorPrecoCrescente() {
        List<Produto> produtos = new LinkedList<>(produtosNoEstoque.values());
        Collections.sort(produtos);
        return produtos;
    }

    public List<Produto> listarProdutosPorPrecoDecrescente() {
        List<Produto> produtos = listarProdutosPorPrecoCrescente();
        List<Produto> produtosDecrescente = new LinkedList<>();
        for (int i = produtos.size() - 1; i >= 0; i--)
            produtosDecrescente.add(produtos.get(i));
        produtos = produtosDecrescente;
        return produtos;
    }

    public List<Produto> listarProdutosPorOrdemAlfabetica() {
        List<String> nomesDosProdutos = new LinkedList<>();
        for (Produto p : produtosNoEstoque.values())
            nomesDosProdutos.add(p.getNomeProduto());
        Collections.sort(nomesDosProdutos);
        List<Produto> produtos = new LinkedList<>();
        for (String s : nomesDosProdutos)
            for (Produto p : produtosNoEstoque.values())
                if (p.getNomeProduto().equals(s))
                    produtos.add(p);
        return produtos;
    }

    public List<Produto> pesquisarProdutosQueCustamEntre(double precoMinimo, double precoMaximo) {
        List<Produto> produtosQueCustamMenosQue = new LinkedList<>();
        for (Produto p : produtosNoEstoque.values())
            if (p.getPreco() >= precoMinimo && p.getPreco() <= precoMaximo)
                produtosQueCustamMenosQue.add(p);
        return produtosQueCustamMenosQue;
    }

    public List<Produto> pesquisarProdutosDoFabricante(String fabricante) {
        List<Produto> produtosDoFabricante = new LinkedList<>();
        for (Produto p : produtosNoEstoque.values())
            if (p.getFabricante().equals(fabricante))
                produtosDoFabricante.add(p);
        return produtosDoFabricante;
    }

    public Produto procurarProduto(String id) throws ProdutoNaoEncontradoException {
        if (!produtosNoEstoque.containsKey(id))
            throw new ProdutoNaoEncontradoException(id);
        return produtosNoEstoque.get(id);
    }

    public List<Produto> listarProdutosDoDepartamento(Departamento departamento) {
        List<Produto> produtosDoDepartamento = new LinkedList<>();
        for (Produto p : produtosNoEstoque.values())
            if (p.getDepartamento().equals(departamento))
                produtosDoDepartamento.add(p);
        return produtosDoDepartamento;
    }

    /*public void gerarRelatorio(String nomeArquivo) {
        GravadorDeDadosDoEstoque gravador = new GravadorDeDadosDoEstoque();
        try (PrintWriter printWriter = new PrintWriter(nomeArquivo)) {
            printWriter.println("Relatório do estoque");
            printWriter.println("Data: " + LocalDate.now());
            printWriter.println();
            Estoque estoque = gravador.recuperarDadosDoEstoque();
            printWriter.println(estoque);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }*/

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