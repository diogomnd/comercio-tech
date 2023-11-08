/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufpb.dcx.comerciotech.controller;

import br.ufpb.dcx.comerciotech.exceptions.ProdutoNaoEncontradoException;
import br.ufpb.dcx.comerciotech.logic.Carrinho;
import br.ufpb.dcx.comerciotech.logic.Compra;
import br.ufpb.dcx.comerciotech.logic.Estoque;
import br.ufpb.dcx.comerciotech.logic.Produto;
import br.ufpb.dcx.comerciotech.recorder.GravadorDeDadosDoEstoque;

import javax.swing.*;

public class CarrinhoController {

    private Carrinho carrinho;
    private GravadorDeDadosDoEstoque gravador = new GravadorDeDadosDoEstoque();
    private Estoque estoque;

    public CarrinhoController() {
        this.carrinho = new Carrinho();
        this.estoque = gravador.recuperarDadosDoEstoque();
    }

    public void exibirCarrinho() {
        JOptionPane.showMessageDialog(null, carrinho.toString());
    }

    public void adicionarProdutoNoCarrinho() {
        String id = JOptionPane.showInputDialog("Qual o ID do produto a ser adicionado?");
        Produto produto = estoque.getProdutosNoEstoque().get(id);
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade?"));
        try {
            carrinho.adicionarProdutoNoCarrinho(estoque, produto, quantidade);
        } catch (ProdutoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void removerProdutoDoCarrinho() {
        String id = JOptionPane.showInputDialog("Qual o ID do produto a ser removido?");
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade?"));
        carrinho.removerProdutoNoCarrinho(id, quantidade);
    }

    public void processarCompra() {
        Compra compra = new Compra(carrinho.calculaTotal());
        compra.processarCompra(estoque, carrinho);
        JOptionPane.showMessageDialog(null, "Compra realizada com sucesso!");
    }
}
