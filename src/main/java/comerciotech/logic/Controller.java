/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerciotech.logic;
import comerciotech.exceptions.EstoqueCheioException;
import comerciotech.exceptions.ProdutoJaCadastradoException;
import comerciotech.exceptions.ProdutoNaoEncontradoException;

import javax.swing.JOptionPane;
import java.util.Map;

/**
 *
 * @author astca
 */
public class Controller {
    private SistemaEstoque sistemaEstoque;

    public Controller(SistemaEstoque sistemaEstoque){
        this.sistemaEstoque = sistemaEstoque;
    }
    
    public void adicionar() throws ProdutoJaCadastradoException, EstoqueCheioException {
        String nome = JOptionPane.showInputDialog("Nome: ");
        String id = JOptionPane.showInputDialog("Id: ");
        String fabricante = JOptionPane.showInputDialog("Fabricante: ");
        Departamento departamento = Departamento.parseTipo(JOptionPane.showInputDialog("Departamento: "));
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço: "));
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade: "));
        Produto produto = new Produto(nome,id,fabricante,departamento,preco,quantidade);
        sistemaEstoque.adicionarProduto(id,produto,quantidade);
    }

    public void removerProduto() throws ProdutoNaoEncontradoException {
        String id = JOptionPane.showInputDialog("Id: ");
        sistemaEstoque.removerProduto(id);
    }
    public double consultarPrecoProduto() throws ProdutoNaoEncontradoException {
        String id = JOptionPane.showInputDialog("Id: ");
        return sistemaEstoque.consultarPrecoDoProduto(id);
    }
    public boolean verifcarDisponibiliadade() throws ProdutoNaoEncontradoException {
        String id = JOptionPane.showInputDialog("Id: ");
        int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Quantidade: "));
        return sistemaEstoque.verificarDisponibilidade(id,quantidade);
    }

    public void procurarProduto() throws ProdutoNaoEncontradoException {
        String id = JOptionPane.showInputDialog("Id: ");
        Produto produtoPesquisado = sistemaEstoque.procurarProduto(id);
        JOptionPane.showMessageDialog(null,produtoPesquisado.toString());
    }


    public void exibirEstoqueCompleto(){
        Map<String,Produto> estoqueCompleto = sistemaEstoque.getProdutosNoEstoque();
        if (estoqueCompleto.isEmpty()){
            System.out.println("vazio");
        } else {
            for (Produto p: estoqueCompleto.values()) {
                System.out.println(p.toString());
            }
        }

    }
    
    
}
