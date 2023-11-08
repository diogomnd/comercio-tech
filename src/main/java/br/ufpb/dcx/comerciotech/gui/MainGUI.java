package br.ufpb.dcx.comerciotech.gui;

import br.ufpb.dcx.comerciotech.controller.EstoqueController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainGUI extends JFrame {

    private EstoqueController estoqueController;

    public MainGUI() {
        initComponents();
        estoqueController = new EstoqueController();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                estoqueController.salvar();
                dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        JPanel painel = new JPanel();
        JLabel labelImagem = new JLabel();

        JMenuBar barraDeMenu = new JMenuBar();
        JMenu menuEstoque = new JMenu();
        JMenuItem botaoMenuAdicionarProduto = new JMenuItem();
        JMenuItem botaoMenuRemoverProduto = new JMenuItem();
        JMenuItem botaoMenuConsultarPreco = new JMenuItem();
        JMenuItem botaoMenuVerificarDisponibilidade = new JMenuItem();
        JMenuItem botaoMenuGerarRelatorio = new JMenuItem();
        JMenuItem botaoProcurarProduto = new JMenuItem();
        JMenu menuCarrinho = new JMenu();
        JMenuItem botaoAbrirCarrinho = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        painel.setBackground(new java.awt.Color(255, 255, 255));
        painel.setPreferredSize(new java.awt.Dimension(1280, 720));

        labelImagem.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/mercadoTechLogoMenor.png")))); // NOI18N
        labelImagem.setHorizontalTextPosition(SwingConstants.CENTER);

        GroupLayout painelLayout = new GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
                painelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(painelLayout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(labelImagem)
                                .addContainerGap(280, Short.MAX_VALUE))
        );
        painelLayout.setVerticalGroup(
                painelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(painelLayout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(labelImagem)
                                .addContainerGap(190, Short.MAX_VALUE))
        );

        menuEstoque.setText("Estoque");

        botaoMenuAdicionarProduto.setText("Adicionar Produto");
        botaoMenuAdicionarProduto.addActionListener(this::botaoMenuAdicionarProdutoActionPerformed);
        menuEstoque.add(botaoMenuAdicionarProduto);

        botaoMenuRemoverProduto.setText("Remover Produto");
        botaoMenuRemoverProduto.addActionListener(this::botaoMenuRemoverProdutoActionPerformed);
        menuEstoque.add(botaoMenuRemoverProduto);

        botaoMenuConsultarPreco.setText("Consultar preço do Produto");
        botaoMenuConsultarPreco.addActionListener(this::botaoMenuConsultarPrecoActionPerformed);
        menuEstoque.add(botaoMenuConsultarPreco);

        botaoMenuVerificarDisponibilidade.setText("Verificar disponibilidade");
        botaoMenuVerificarDisponibilidade.addActionListener(this::botaoMenuVerificarDisponibilidadeActionPerformed);
        menuEstoque.add(botaoMenuVerificarDisponibilidade);

        botaoMenuGerarRelatorio.setText("Gerar relatório");
        botaoMenuGerarRelatorio.addActionListener(this::botaoMenuGerarRelatorioActionPerformed);
        menuEstoque.add(botaoMenuGerarRelatorio);

        botaoProcurarProduto.setText("Procurar Produto");
        botaoProcurarProduto.addActionListener(this::botaoProcurarProdutoActionPerformed);
        menuEstoque.add(botaoProcurarProduto);

        barraDeMenu.add(menuEstoque);

        menuCarrinho.setText("Carrinho");

        botaoAbrirCarrinho.setText("Abrir carrinho");
        botaoAbrirCarrinho.addActionListener(this::botaoAbrirCarrinhoActionPerformed);
        menuCarrinho.add(botaoAbrirCarrinho);

        barraDeMenu.add(menuCarrinho);

        setJMenuBar(barraDeMenu);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(painel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(painel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void botaoMenuAdicionarProdutoActionPerformed(ActionEvent evt) {
        estoqueController.adicionarProduto();
    }

    private void botaoMenuRemoverProdutoActionPerformed(ActionEvent evt) {
        estoqueController.removerProduto();
    }

    private void botaoMenuVerificarDisponibilidadeActionPerformed(ActionEvent evt) {
        estoqueController.verificarDisponibilidade();
    }

    private void botaoMenuConsultarPrecoActionPerformed(ActionEvent evt) {
        estoqueController.consultarPrecoDoProduto();
    }

    private void botaoMenuGerarRelatorioActionPerformed(ActionEvent evt) {
        estoqueController.gerarRelatorio();
    }

    private void botaoAbrirCarrinhoActionPerformed(ActionEvent evt) {
        CarrinhoGUI janelaSecundaria = new CarrinhoGUI();
        janelaSecundaria.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                janelaSecundaria.setVisible(false);
            }
        });

        janelaSecundaria.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janelaSecundaria.setVisible(true);
    }

    private void botaoProcurarProdutoActionPerformed(ActionEvent evt) {
        estoqueController.procurarProduto();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }

}