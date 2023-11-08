package comerciotech.exceptions;

public class ProdutoNaoEncontradoException extends Exception {
    public ProdutoNaoEncontradoException(String id) {
        super("Produto de id " + id + " não encontrado.");
    }
}
