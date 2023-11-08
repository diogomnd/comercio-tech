package br.ufpb.dcx.comerciotech.recorder;

import br.ufpb.dcx.comerciotech.logic.Estoque;

import java.io.*;

public class GravadorDeDadosDoEstoque {

    private static final String NOME_ARQUIVO = "estoque.dat";

    public void salvarDadosDoEstoque(Estoque estoque) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(NOME_ARQUIVO);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(estoque);
            outputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Estoque recuperarDadosDoEstoque() {
        Estoque estoque = null;
        try {
            FileInputStream arquivoInput = new FileInputStream(NOME_ARQUIVO);
            ObjectInputStream objectInputStream = new ObjectInputStream(arquivoInput);
            estoque = (Estoque) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return estoque;
    }
}