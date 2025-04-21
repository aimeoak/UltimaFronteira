public class EventoClimatico extends Evento {
    private String tipoClima;       //neve,tempestade,incendio...
    private int duracao;
    //adicionar atributo efeito no ambiente

    public EventoClimatico(String nome, String descricao, double probabilidadeOcorrencia, int impacto,String condicaoAtivacao ,String tipoClima, int duracao) {
        super(nome, descricao, probabilidadeOcorrencia, impacto, condicaoAtivacao);
        this.tipoClima = tipoClima;
        this.duracao = duracao;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        if (tipoClima.equalsIgnoreCase("neve")) {
            jogador.reduzirVida(5);
            jogador.reduzirEnergia(10);
            jogador.reduzirSanidade(5);
        } else if (tipoClima.equalsIgnoreCase("tempestade")) {
            jogador.reduzirVida(5);
            jogador.reduzirEnergia(5);
            jogador.reduzirSanidade(5);
        } else if (tipoClima.equalsIgnoreCase("incendio")) {
            jogador.reduzirVida(10);
            jogador.reduzirEnergia(7);
            jogador.reduzirSede(15);
            jogador.reduzirSanidade(5);

        } else {
            System.out.println("O clima está instável, mas não afeta diretamente o personagem.");

        }

        }
    @Override
    public String getDescricao() {
        return gerarDescricao();
    }

    private String gerarDescricao() {
        switch (tipoClima.toLowerCase()) {
            case "neve":
                return "Uma nevasca repentina reduz a visibilidade e cobre o chão com neve, dificultando a movimentação. As temperaturas caem rapidamente, aumentando o risco de hipotermia para aqueles expostos. É urgente buscar abrigo para preservar a energia e a saúde.";
            case "tempestade":
                return "A chuva forte transforma o ambiente em um local lamacento e escorregadio. Encontrar abrigo se torna essencial para evitar penalidades em energia e sanidade.";
            case "incendio":
                return "O calor extremo eleva rapidamente as temperaturas, facilitando a desidratação. O personagem deve consumir mais água para evitar fadiga e alucinações. A busca por sombra e recursos se torna vital para a sobrevivência.";
            default:
                return "O clima está agradável, sem alterações significativas.";
        }
    }

    }