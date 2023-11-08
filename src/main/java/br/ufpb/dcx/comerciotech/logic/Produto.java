package br.ufpb.dcx.comerciotech.logic;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Comparable<Produto>, Serializable {

    private final String nomeProduto;
    private final String idProduto;
    private final String fabricante;
    private final Departamento departamento;
    private double preco;
    private int quantidade;

    public Produto(String nomeProduto, String idProduto, String fabricante,
                   Departamento departamento, double preco, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.idProduto = idProduto;
        this.fabricante = fabricante;
        this.departamento = departamento;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public String getFabricante() {
        return fabricante;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void atualizarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(nomeProduto, produto.nomeProduto) && Objects.equals(idProduto, produto.idProduto);
    }

    public String toString() {
        return nomeProduto + "\n"
                + "ID: " + idProduto + "\n"
                + "Fabricante: " + fabricante + "\n"
                + "Departamento: " + departamento + "\n"
                + "Valor: R$ " + preco + "\n"
                + "Quantidade no estoque: " + quantidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeProduto, idProduto, fabricante, preco, quantidade);
    }

    @Override
    public int compareTo(Produto outroProduto) {
        return Double.compare(this.preco, outroProduto.getPreco());
    }

}