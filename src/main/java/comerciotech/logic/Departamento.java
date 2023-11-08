package comerciotech.logic;

public enum Departamento {

    HARDWARE, PERIFERICO, TELAS, CONSOLE, COMPUTADOR, CELULAR, JOGOS;

    public String toString() {
        if (this.equals(HARDWARE)) return "Hardware";
        else if (this.equals(PERIFERICO)) return "Periféricos";
        else if (this.equals(TELAS)) return "TV's/Monitores";
        else if (this.equals(CONSOLE)) return "Consoles";
        else if (this.equals(COMPUTADOR)) return "Computadores/Notebooks";
        else if (this.equals(CELULAR)) return "Celulares";
        else if (this.equals(JOGOS)) return "Jogos";
        return null;
    }

    public static Departamento parseTipo(String tipo) {
        return switch (tipo) {
            case "Hardware" -> HARDWARE;
            case "Periféricos" -> PERIFERICO;
            case "TV's/Monitores" -> TELAS;
            case "Consoles" -> CONSOLE;
            case "Computadores/Notebooks" -> COMPUTADOR;
            case "Celulares" -> CELULAR;
            case "Jogos" -> JOGOS;
            default -> null;
        };
    }
}
