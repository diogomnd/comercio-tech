package br.ufpb.dcx.comerciotech.exceptions;

public class EstoqueVazioException extends Exception {
    public EstoqueVazioException() {
        super("Não há como remover produtos, pois o estoque está vazio!");
    }
}
