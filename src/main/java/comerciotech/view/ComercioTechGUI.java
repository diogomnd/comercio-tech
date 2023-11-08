package comerciotech.view;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author astca
 */
import comerciotech.exceptions.EstoqueCheioException;
import comerciotech.exceptions.ProdutoJaCadastradoException;
import comerciotech.exceptions.ProdutoNaoEncontradoException;
import comerciotech.logic.Carrinho;
import comerciotech.logic.Controller;
import comerciotech.logic.SistemaEstoque;

import javax.swing.*;

public class ComercioTechGUI extends JFrame {
        SistemaEstoque sistemaEstoque = new SistemaEstoque();
        Carrinho carrinho;
        Controller controle;
    public ComercioTechGUI() {
        initComponents();
        controle = new Controller(this.sistemaEstoque);
    }

    private void initComponents() {
        sistemaEstoque.salvarDados();
        jPanel3 = new JPanel();
        logo = new JLabel();
        cabecalho = new JMenuBar();
        departamentoMenu = new JMenu();
        celularButton = new JMenuItem();
        computadorButton = new JMenuItem();
        consoleButton = new JMenuItem();
        hardwareButton = new JMenuItem();
        jogosButton = new JMenuItem();
        perifericosButton = new JMenuItem();
        telasButton = new JMenuItem();
        estoqueMenu = new JMenu();
        adicionarProdutoButton = new JMenuItem();
        removerProdutoButton = new JMenuItem();
        consultarPrecoButton = new JMenuItem();
        verificarDisponibilidadeButton = new JMenuItem();
        gerarRelatorio = new JMenuItem();
        Ordenar = new JMenu();
        listarProdPorPrecoCrescente = new JMenuItem();
        listarProdPorPrecoDecrescente = new JMenuItem();
        listarProdPorOrdemAlfabetica = new JMenuItem();
        pesquisarProdutosCustamEntre = new JMenuItem();
        pesquisarProdDoFabricante = new JMenuItem();
        carrinhoMenu = new JMenu();
        abrirCarrinhoButton = new JMenuItem();
        pesquisar = new JMenu();
        pesquisarNomeButton = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        logo.setIcon(new ImageIcon(("src/main/java/br/ufpb/dcx/comerciotech/images/asa.png"))); // NOI18N
        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(logo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(logo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cabecalho.setBackground(new java.awt.Color(24, 34,89 ));
        cabecalho.setBorder(null);
        cabecalho.setToolTipText("");
        cabecalho.setPreferredSize(new java.awt.Dimension(70, 70));

        departamentoMenu.setText("Departamentos");

        celularButton.setText("Celulares");
        celularButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controle.exibirEstoqueCompleto();
                sistemaEstoque.salvarDados();
            }
        });
        departamentoMenu.add(celularButton);

        computadorButton.setText("Computadores");
        departamentoMenu.add(computadorButton);

        consoleButton.setText("Consoles");
        consoleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        departamentoMenu.add(consoleButton);

        hardwareButton.setText("Hardware");
        departamentoMenu.add(hardwareButton);

        jogosButton.setText("Jogos");
        departamentoMenu.add(jogosButton);

        perifericosButton.setText("Perifericos");
        departamentoMenu.add(perifericosButton);

        telasButton.setText("Tv's / Monitores");
        departamentoMenu.add(telasButton);

        cabecalho.add(departamentoMenu);

        estoqueMenu.setText("Estoque");

        adicionarProdutoButton.setText("Adicionar produtos");
       adicionarProdutoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    controle.adicionar();
                } catch (ProdutoJaCadastradoException e) {
                    e.getMessage();
                } catch (EstoqueCheioException e) {
                    e.getMessage();
                }
            }
        });
        estoqueMenu.add(adicionarProdutoButton);

        removerProdutoButton.setText("Remover produtos");
        removerProdutoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    controle.removerProduto();
                } catch (ProdutoNaoEncontradoException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        estoqueMenu.add(removerProdutoButton);

        consultarPrecoButton.setText("Consultar preço");
        consultarPrecoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                   JOptionPane.showMessageDialog(null,controle.consultarPrecoProduto());
                } catch (ProdutoNaoEncontradoException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        estoqueMenu.add(consultarPrecoButton);

        verificarDisponibilidadeButton.setText("Verificar disponibilidade");
        verificarDisponibilidadeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    if(controle.verifcarDisponibiliadade()){
                        JOptionPane.showMessageDialog(null,"Disponível");
                    }else {
                        JOptionPane.showMessageDialog(null,"Indisponível");
                    }
                } catch (ProdutoNaoEncontradoException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        estoqueMenu.add(verificarDisponibilidadeButton);

        gerarRelatorio.setText("Gerar relatório");
        gerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });
        estoqueMenu.add(gerarRelatorio);

        cabecalho.add(estoqueMenu);

        Ordenar.setText("Ordenar");

        listarProdPorPrecoCrescente.setText("Maior preço");
        Ordenar.add(listarProdPorPrecoCrescente);

        listarProdPorPrecoDecrescente.setText("Menor preço");
        Ordenar.add(listarProdPorPrecoDecrescente);

        listarProdPorOrdemAlfabetica.setText("Ordem alfabetica");
        Ordenar.add(listarProdPorOrdemAlfabetica);

        pesquisarProdutosCustamEntre.setText("Preço entre");
        pesquisarProdutosCustamEntre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        Ordenar.add(pesquisarProdutosCustamEntre);

        pesquisarProdDoFabricante.setText("Por fabricante");
        Ordenar.add(pesquisarProdDoFabricante);

        cabecalho.add(Ordenar);

        carrinhoMenu.setText("Carrinho");

        abrirCarrinhoButton.setText("Abrir");
        abrirCarrinhoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        carrinhoMenu.add(abrirCarrinhoButton);

        cabecalho.add(carrinhoMenu);

        pesquisar.setText("Pesquisar");

        pesquisarNomeButton.setText("Pesquisar");
        pesquisarNomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    controle.procurarProduto();
                } catch (ProdutoNaoEncontradoException e) {
                    e.getMessage();
                }
            }
        });
        pesquisar.add(pesquisarNomeButton);

        cabecalho.add(pesquisar);

        setJMenuBar(cabecalho);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComercioTechGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComercioTechGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComercioTechGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComercioTechGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComercioTechGUI().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenu Ordenar;
    private JMenuItem abrirCarrinhoButton;
    private JMenuItem adicionarProdutoButton;
    private JMenuBar cabecalho;
    private JMenu carrinhoMenu;
    private JMenuItem celularButton;
    private JMenuItem computadorButton;
    private JMenuItem consoleButton;
    private JMenuItem consultarPrecoButton;
    private JMenu departamentoMenu;
    private JMenu estoqueMenu;
    private JMenuItem gerarRelatorio;
    private JMenuItem hardwareButton;
    private JPanel jPanel3;
    private JMenuItem jogosButton;
    private JMenuItem listarProdPorOrdemAlfabetica;
    private JMenuItem listarProdPorPrecoCrescente;
    private JMenuItem listarProdPorPrecoDecrescente;
    private JLabel logo;
    private JMenuItem perifericosButton;
    private JMenu pesquisar;
    private JMenuItem pesquisarNomeButton;
    private JMenuItem pesquisarProdDoFabricante;
    private JMenuItem pesquisarProdutosCustamEntre;
    private JMenuItem removerProdutoButton;
    private JMenuItem telasButton;
    private JMenuItem verificarDisponibilidadeButton;
    // End of variables declaration//GEN-END:variables
}
