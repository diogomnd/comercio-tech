package comerciotech.exceptions;

public class EstoqueCheioException extends Exception {
    public EstoqueCheioException(int capacidade) {
        super("O estoque atingiu a capacidade máxima de " + capacidade + " produtos.");
    }
}
