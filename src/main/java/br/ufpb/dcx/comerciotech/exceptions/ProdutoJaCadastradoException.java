package br.ufpb.dcx.comerciotech.exceptions;

public class ProdutoJaCadastradoException extends Exception {
    public ProdutoJaCadastradoException(String id) {
        super("Produto de id " + id + " já está cadastrado no estoque.");
    }
}
