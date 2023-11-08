package br.ufpb.dcx.comerciotech.logic;


public class Compra extends Carrinho {

    private double valorTotalDaCompra;

    public Compra(double valorTotalDoCarrinho) {
        this.valorTotalDaCompra = valorTotalDoCarrinho;
    }

    public void processarCompra(Estoque estoque, Carrinho carrinho) {
        for (Produto produto : estoque.getProdutosNoEstoque().values()) {
            int quantidadeNoCarrinho = carrinho.contarOcorrenciasNoCarrinho(produto);
            produto.atualizarQuantidade(-quantidadeNoCarrinho);
            estoque.atualizarNivelDoEstoque(-quantidadeNoCarrinho);
        }
        carrinho.limparCarrinho();
    }

    public double getValorTotalDaCompra() {
        return valorTotalDaCompra;
    }

    public String toString() {
        return "Valor total da transação: R$ " + getValorTotalDaCompra();
    }
}