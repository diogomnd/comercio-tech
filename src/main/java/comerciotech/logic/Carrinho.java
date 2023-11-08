package comerciotech.logic;

import comerciotech.exceptions.ProdutoNaoEncontradoException;

import java.text.DecimalFormat;
import java.util.*;

public class Carrinho {

    private List<Produto> produtosNoCarrinho;

    public Carrinho() {
        this.produtosNoCarrinho = new ArrayList<>();
    }

    public void adicionarProdutoNoCarrinho(SistemaEstoque sistemaEstoque, Produto produto, int quantidade)
            throws ProdutoNaoEncontradoException {
        boolean disponivel = sistemaEstoque.verificarDisponibilidade(produto.getIdProduto(), quantidade);
        if (disponivel)
            for (int i = 0; i < quantidade; i++)
                produtosNoCarrinho.add(produto);
    }

    public void removerProdutoNoCarrinho(Produto produto, int quantidade) {
        for (int i = 0; i < quantidade; i++)
            produtosNoCarrinho.remove(produto);
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

    public int contarOcorrenciasNoCarrinho(Produto produtoParaContar) {
        if (produtosNoCarrinho.contains(produtoParaContar))
            return Collections.frequency(produtosNoCarrinho, produtoParaContar);
        return 0;
    }

  /*  public static void main(String[] args) throws ProdutoNaoEncontradoException, ProdutoJaCadastradoException, EstoqueCheioException {
       GravadorDeDadosDoEstoque gravadorDeDadosDoEstoque = new GravadorDeDadosDoEstoque();
        Estoque estoque =  gravadorDeDadosDoEstoque.recuperarDadosDoEstoque();
        Produto produto1 = new Produto("Intel Core i5-13400F", "405766", "Intel",
                Departamento.HARDWARE, 1348.99, 150);
        Produto produto2 = new Produto("Microsoft Xbox Series S 512GB", "200089",
                "Microsoft", Departamento.CONSOLE, 2491.94, 200);
        gravadorDeDadosDoEstoque.salvarDadosDoEstoque(estoque);
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarProdutoNoCarrinho(estoque, produto1, 2);
        carrinho.adicionarProdutoNoCarrinho(estoque, produto2, 5);

        //System.out.println(carrinho);
        Compra compra = new Compra();
        compra.processarCompra(estoque, carrinho);

        for(Produto p : estoque.getProdutosNoEstoque().values())
            System.out.println(p.getQuantidade());
        System.out.println(carrinho);
        gravadorDeDadosDoEstoque.salvarDadosDoEstoque(estoque);
        estoque.gerarRelatorio("relatorio.txt");
    }*/
}