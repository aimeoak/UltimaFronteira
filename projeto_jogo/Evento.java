public abstract class Evento {
    private String nome;
    private String descricao;
    private double probabilidadeOcorrencia;
    private int impacto;
    //adcionar atributo condição de ativação

    public Evento(String nome, String descricao, double probabilidadeOcorrencia, int impacto){
        this.nome = nome;
        this.descricao = descricao;
        this.probabilidadeOcorrencia = probabilidadeOcorrencia;
        this.impacto = impacto;
    }

    public abstract void executar(Personagem jogador, Ambiente local);
    public abstract String getDescricao();
}
