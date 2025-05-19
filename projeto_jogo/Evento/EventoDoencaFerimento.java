package Evento;

import Personagem.Personagem;

import Ambiente.Ambiente;

import java.util.List;
public class EventoDoencaFerimento extends Evento {
    private String tipoCondicao; //Infecção, febre, desidratação, fratura, etc
    private List<String> curaItens;

    public EventoDoencaFerimento(String nome, String descricao, double probabilidadeOcorrencia, int impacto, String condicaoAtivacao ,String tipoCondicao,List<String> curaItens) {
        super(nome, descricao, probabilidadeOcorrencia, impacto,condicaoAtivacao);
        this.tipoCondicao = tipoCondicao;
        this.curaItens = curaItens;
    }
    //ADICIONAR EFEITO NEGATIVO CONSTANTE!
    @Override
    public void executar(Personagem jogador, Ambiente local) {
        if (tipoCondicao.equalsIgnoreCase("fratura")) {
            jogador.reduzirVida(10);

        } else if (tipoCondicao.equalsIgnoreCase("infecção")) {
            jogador.reduzirVida(5);
            jogador.reduzirEnergia(5);
            jogador.reduzirSanidade(5);
        } else if (tipoCondicao.equalsIgnoreCase("desidratação")) {
            jogador.reduzirSede(15);
            jogador.reduzirSanidade(5);

        } else {
            System.out.println("Apesar dos perigos à espreita, seu corpo resiste. Por ora, você se sente forte e saudável.");

        }

    }

    private static String gerarDescricao(String tipoCondicao) {
        switch (tipoCondicao.toLowerCase()) {
            case "fratura":
                return "Você dá um passo e de repente um estalo seco ecoa. A dor é imediata e pulsante. Cada passo agora é uma batalha contra seu próprio corpo. Você precisa de um analgésico.";
            case "infecção":
                return "Você percebe que um ferimento escureceu e lateja com força. Febre sobe, a pele arrepia. Você precisa tratar a infecção com um antibiótico.";
            case "desidratação":
                return "A boca está seca como areia. A visão embaça, os pensamentos se arrastam. Você está desidratado e o corpo clama por uma gota d'água.";
            default:
                return "Apesar dos perigos à espreita, seu corpo resiste. Por ora, você se sente forte e saudável.";
        }
    }


    @Override
    public String getDescricao() {
        return gerarDescricao(tipoCondicao);
    }
}