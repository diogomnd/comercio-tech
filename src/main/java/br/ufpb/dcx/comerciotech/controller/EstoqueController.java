/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufpb.dcx.comerciotech.controller;

import br.ufpb.dcx.comerciotech.exceptions.EstoqueCheioException;
import br.ufpb.dcx.comerciotech.exceptions.EstoqueVazioException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoJaCadastradoException;
import br.ufpb.dcx.comerciotech.exceptions.ProdutoNaoEncontradoException;
import br.ufpb.dcx.comerciotech.logic.Departamento;
import br.ufpb.dcx.comerciotech.logic.Estoque;
import br.ufpb.dcx.comerciotech.recorder.GravadorDeDadosDoEstoque;
import br.ufpb.dcx.comerciotech.logic.Produto;

import javax.swing.*;

public class EstoqueController {

    private Estoque estoque;
    private GravadorDeDadosDoEstoque gravador;
    private static final String NOME_ARQUIVO = "relatorio.txt";

    public EstoqueController() {
        gravador = new GravadorDeDadosDoEstoque();
        estoque = gravador.recuperarDadosDoEstoque();
        if (estoque == null) estoque = new Estoque();
    }

    public void adicionarProduto() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto");
        String id = JOptionPane.showInputDialog("Digite o ID do produto");
        String fabricante = JOptionPane.showInputDialog("Digite o fabricante do produto");
        Departamento departamento = Departamento.parseTipo(JOptionPane.showInputDialog("Digite o departamento do produto"));
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor unitário do produto"));
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade a ser adicionada no estoque"));
        Produto produto = new Produto(nome, id, fabricante, departamento, preco, quantidade);
        try {
            estoque.adicionarProduto(id, produto, quantidade);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso.");
        } catch (ProdutoJaCadastradoException | EstoqueCheioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void removerProduto() {
        String id = JOptionPane.showInputDialog("Qual o ID do produto que você quer remover?");
        try {
            estoque.removerProduto(id);
            JOptionPane.showMessageDialog(null, "Produto removido com sucesso.");
        } catch (ProdutoNaoEncontradoException | EstoqueVazioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void consultarPrecoDoProduto() {
        String id = JOptionPane.showInputDialog("Qual o ID do produto que se deseja procurar?");
        try {
            double preco = estoque.consultarPrecoDoProduto(id);
            JOptionPane.showMessageDialog(null, "O produto de id " + id + " custa R$ " + preco);
        } catch (ProdutoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void verificarDisponibilidade() {
        String id = JOptionPane.showInputDialog("Qual o ID do produto que se deseja procurar?");
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade desejada?"));
        try {
            boolean disponivel = estoque.verificarDisponibilidade(id, quantidade);
            if (disponivel)
                JOptionPane.showMessageDialog(null, "O produto de id " + id + " está disponível.");
            else
                JOptionPane.showMessageDialog(null, "O produto de id " + id + " não está disponível.");
        } catch (ProdutoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void procurarProduto() {
        String id = JOptionPane.showInputDialog("Qual o ID do produto que se deseja procurar?");
        try {
            Produto produto = estoque.procurarProduto(id);
            JOptionPane.showMessageDialog(null, "Produto encontrado");
            JOptionPane.showMessageDialog(null, produto.toString());
        } catch (ProdutoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void gerarRelatorio() {
        estoque.gerarRelatorio(NOME_ARQUIVO);
        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!");
    }

    public void salvar() {
        gravador.salvarDadosDoEstoque(estoque);
    }
}
