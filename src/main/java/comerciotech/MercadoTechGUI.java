package comerciotech;

import javax.swing.*;
import java.awt.*;

public class MercadoTechGUI extends JFrame {

    public MercadoTechGUI(){
        setTitle("VTNC");
        setSize(1280,832);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JMenuBar cabecalho = new JMenuBar();
        cabecalho.setBounds(0,0,1280,121);
        cabecalho.setBackground(new Color(1,1,1));
        add(cabecalho);

        ImageIcon imagemLogo = new ImageIcon("src/main/java/br/ufpb/dcx/comerciotech/images/madfjopbnujipd.png");
        JLabel logo = new JLabel(imagemLogo);
        logo.setBounds(0,121,1280,711);
        add(logo);

        ImageIcon textoLogoTemp = new ImageIcon("src/main/java/br/ufpb/dcx/comerciotech/images/textoLogo.png");
        JLabel textoLogo = new JLabel(textoLogoTemp);
        textoLogo.setBounds(49,21,293,60);

        JMenu departamentos = new JMenu();
        departamentos.setText("Departamentos");
        departamentos.setBounds(1099,47,153,34);
        departamentos.setForeground(new Color(242,242,242));

        cabecalho.add(departamentos);
        cabecalho.add(textoLogo);


        /*JTable cabecalho = new JTable();
        cabecalho.setBounds(0,0,1280,121);
        cabecalho.setBackground(new Color(24,34,89,100));
        cabecalho.add(menuDepartamentos);
        add(cabecalho);*/
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MercadoTechGUI();
            }
        });
    }
}
