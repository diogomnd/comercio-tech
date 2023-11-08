package br.ufpb.dcx.comerciotech.gui;

import br.ufpb.dcx.comerciotech.controller.CarrinhoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarrinhoGUI extends JFrame {

    private CarrinhoController carrinhoController;

    public CarrinhoGUI() {
        this.carrinhoController = new CarrinhoController();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        JPanel painel1 = new JPanel();
        JButton botaoAdicionar = new JButton();
        JButton botaoRemover = new JButton();
        JButton botaoExibir = new JButton();
        JButton botaoProcessarCompra = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        botaoAdicionar.setText("Adicionar produto ao carrinho");
        botaoAdicionar.addActionListener(this::botaoAdicionarActionPerformed);

        GroupLayout jPanel1Layout = new GroupLayout(painel1);
        painel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(botaoAdicionar, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(botaoAdicionar, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(67, Short.MAX_VALUE))
        );

        botaoRemover.setText("Remover produto do carrinho");
        botaoRemover.addActionListener(this::botaoRemoverActionPerformed);

        botaoExibir.setText("Exibir Carrinho");
        botaoExibir.addActionListener(this::botaoExibirActionPerformed);

        botaoProcessarCompra.setText("Processar compra");
        botaoProcessarCompra.addActionListener(this::botaoProcessarCompraActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(painel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoRemover, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoExibir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoProcessarCompra, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(painel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoExibir, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(botaoRemover, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(botaoProcessarCompra, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67))
        );

        pack();
    }

    private void botaoRemoverActionPerformed(ActionEvent evt) {
        carrinhoController.removerProdutoDoCarrinho();
    }

    private void botaoAdicionarActionPerformed(ActionEvent evt) {
        carrinhoController.adicionarProdutoNoCarrinho();
    }

    private void botaoExibirActionPerformed(ActionEvent evt) {
        carrinhoController.exibirCarrinho();
    }

    private void botaoProcessarCompraActionPerformed(ActionEvent evt) {
        carrinhoController.processarCompra();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CarrinhoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new CarrinhoGUI().setVisible(true));
    }
}
