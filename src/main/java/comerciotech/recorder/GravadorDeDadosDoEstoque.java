package comerciotech.recorder;

import comerciotech.logic.Produto;

import java.io.*;
import java.util.Map;

public class GravadorDeDadosDoEstoque {

    private static final String NOME_ARQUIVO = "estoque.dat";

    public void salvarDadosDoEstoque(Map<String, Produto> produtosEstoque) throws IOException {
        ObjectOutputStream out = null;
        try {
           out = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO));
           out.writeObject(produtosEstoque);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new IOException("Erro ao salvar os arquivos");
        }
    }

    public Map<String,Produto> recuperarDadosDoEstoque() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO));
            return (Map<String, Produto>) in.readObject();
        }catch (Exception e){
            System.out.println("Não foi possivel recuperar o estoque");
            throw new IOException("Não foi possivel recuperar os dados do arquivo");
        }finally {
            if(in != null) in.close();
        }
    }
}