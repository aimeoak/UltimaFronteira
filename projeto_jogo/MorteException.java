package excecoes;
public class MorteException extends RuntimeException {
    private final String causa;

    public MorteException(String message, String causa) {
        super(message);
        this.causa = causa;
    }

    public String getCausa() {
        return causa;
    }
}
