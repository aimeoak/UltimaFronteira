package Evento;
import Interfaces.EventoInterface;
import Ambiente.Ambiente;
import Personagem.Personagem;
import Item.Item;
import Item.Alimento;
import Item.Inventario;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EventoCriatura extends Evento implements EventoInterface{


    private String tipoCriatura; //Lobo,urso,corvo
    private int nivelPerigo;     //1,2,3
    private List<String> opcoesAcao = new ArrayList<>(Arrays.asList("Fugir", "Combater"));

    public EventoCriatura(String nome, String descricao, double probabilidadeOcorrencia, int impacto,String condicaoAtivacao ,String tipoCriatura,int nivelPerigo,List<String> opcoesAcao){
        super(nome, descricao, probabilidadeOcorrencia, impacto, condicaoAtivacao);
        this.tipoCriatura = tipoCriatura;
        this.nivelPerigo=nivelPerigo;
        this.opcoesAcao = Collections.unmodifiableList(new ArrayList<>(opcoesAcao));
    }

    public List<String> getOpcoesAcao() {
        return opcoesAcao;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        if (tipoCriatura.equalsIgnoreCase("lobo")) {
            // Lobo ataca o jogador
            jogador.reduzirVida(15);
            jogador.reduzirEnergia(7);
            jogador.reduzirSanidade(10);

            // Tentativa do lobo de roubar um item do inventário
            Inventario inventario = jogador.getInventario();
            //List<Item> itens = inventario.getItens();
            List<Item> itens = new ArrayList<>(inventario.getItens());

            if (!itens.isEmpty()) {
                // Procurando por um alimento para roubar
                for (Item item : itens) {
                    if (item instanceof Alimento) {
                        System.out.println("O lobo roubou: " + item.getNome());
                        inventario.removerItem(item.getNome()); // Remove o item do inventário
                        break; // Apenas um item será roubado por vez
                    }
                }
            } else {
                System.out.println("O inventário está vazio, o lobo não conseguiu roubar nada.");
            }
        } else if (tipoCriatura.equalsIgnoreCase("cobra")) {
            jogador.reduzirVida(7);
            jogador.reduzirSanidade(10);
            jogador.reduzirEnergia(10);
            jogador.envenenar();
        } else if (tipoCriatura.equalsIgnoreCase("corvo")) {
            jogador.reduzirSanidade(15);
            jogador.reduzirEnergia(10);
        } else {
            System.out.println("O dia está tranquilo, nenhum animal nocivo à vista.");
        }
    }


    private String gerarDescricao() {
        switch (tipoCriatura.toLowerCase()) {
            case "lobo":
                return "Um lobo faminto surge das sombras e ataca com ferocidade. Você é ferido pelas mordidas e, caso esteja carregando comida, pode tê-la roubada pelo animal selvagem.";
            case "cobra":
                return "De repente você sente uma picada aguda no tornozelo. Uma cobra venenosa recua rapidamente, mas o estrago está feito. O veneno começa a se espalhar... você sente seu corpo fraquejar pouco a pouco.";
            case "corvo":
                return "Um silêncio estranho toma o ar... até que ouve um grasnar agudo. Corvos! Muitos deles, girando no céu e mergulhando em sua direção. Eles não te ferem fisicamente, mas sua mente começa a vacilar diante da presença inquietante das aves.";
            default:
                return "Nada surge dos arbustos. Nenhum rugido, nenhum bater de asas. Você respira fundo e continua sua jornada, grato por um momento de tranquilidade.";
        }
    }
    @Override
    public String getDescricao() {
        return gerarDescricao();
    }
}
