package comerciotech.logic;


public class    Compra extends Carrinho {

    public int numeroDoPedido = 0;
    private double valorTotalDaCompra;

    public Compra() {
        this.numeroDoPedido += 1;
    }

    public void processarCompra(SistemaEstoque sistemaEstoque, Carrinho carrinho) {
        for (Produto produto : sistemaEstoque.getProdutosNoEstoque().values()) {
            int quantidadeNoCarrinho = carrinho.contarOcorrenciasNoCarrinho(produto);
            produto.atualizarQuantidade(-quantidadeNoCarrinho);
            sistemaEstoque.atualizarNivelDoEstoque(-quantidadeNoCarrinho);
        }
        carrinho.limparCarrinho();
    }

    public double getValorTotalDaCompra() {
        return calculaTotal();
    }

    public String toString() {
        return "Valor total da transação " + getValorTotalDaCompra();
    }
}