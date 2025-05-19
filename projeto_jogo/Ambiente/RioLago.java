package Ambiente;

import Evento.Evento;
import Item.Item;
import Personagem.Personagem;
import Item.Agua;
import Item.Materiais;
import Item.Alimento;

import java.util.Random;
import java.util.List;

public class RioLago extends Ambiente {
    
    private static final String agua = "Água em abundância, pode ser potável ou precisar de purificação";
    private static final String pesca = "Peixes podem ser uma excelente fonte de alimento";
    private static final String terreno = "O terreno é lamacento, o que pode dificultar a locomoção";

    public RioLago() {
        super("Rio/Lago", agua + " " + pesca + " " + terreno, 2, new String[] {"Peixes e algas comestíveis", "Água doce", "Item.Materiais de construção"}, 0.4, "Ensolarado");
    }
    /*
    @Override 
    public void gerarEvento() {
        List<Evento.Evento> eventosDisponiveis = getEventos();
        Random rand = new Random();

        boolean eventoOcorrido = false;

        for (Evento.Evento evento : eventosDisponiveis) {
            if (evento.getCondicaoAtivacao().equalsIgnoreCase(getNome())) {
                if (rand.nextDouble() < evento.getProbabilidadeOcorrencia()) {
                    System.out.println("Evento.Evento ativado: " + evento.getNome());
                    System.out.println(evento.getDescricao());
                    evento.executar(jogador, this);
                    eventoOcorrido = true;
                }
            }
        }

        if (!eventoOcorrido) {
            System.out.println("Nenhum evento ocorreu.");
        }
    }
     */
    @Override
    public void gerarEvento() {
        List<Evento> eventosDisponiveis = getEventos();
        Random rand = new Random();

        Evento escolhido = null;

        for (Evento evento : eventosDisponiveis) {
            if (evento.getCondicaoAtivacao().equalsIgnoreCase(getNome())) {
                if (rand.nextDouble() < evento.getProbabilidadeOcorrencia()) {
                    escolhido = evento;
                    break;
                }
            }
        }

        if (escolhido != null) {
            System.out.println("Evento.Evento sorteado: " + escolhido.getNome());
            System.out.println(escolhido.getDescricao());

        } else {
            System.out.println("Nenhum evento sorteado.");
        }
    }
    @Override
    public void explorar(Personagem jogador) {
        String[] recursos = getRecursos();
        Random rand = new Random();
        int index = rand.nextInt(recursos.length);

        String recursoEncontrado = recursos[index];

        System.out.println("Você encontrou " + recursoEncontrado);

        Item itemEncontrado = criarItem(recursoEncontrado);

        if (itemEncontrado != null) {
            
            if (recursoEncontrado.equals("Peixes e algas comestíveis") || recursoEncontrado.equals("Água doce") || recursoEncontrado.equals("Item.Materiais de construção")) {
                
                if (jogador.getInventario().adicionarItem(itemEncontrado)) {
                    System.out.println(itemEncontrado.getNome() + " foi adicionado ao inventário.");
                }    
                else {
                    System.out.println("Não foi possível adicionar " + itemEncontrado.getNome() + " ao inventário.");
                }
            } 
            
            else {
                System.out.println("Este item não pode ser adicionado ao inventário.");
            }
        }
    }

    private Item criarItem(String recurso) {
        switch (recurso) {
            case "Água doce":
                return new Agua("Água doce", 1.0, 10,5,true);
            case "Item.Materiais de construção":
                return new Materiais("Item.Materiais de construção", 5, 2,"madeira",5);
            case "Peixes e algas comestíveis":
                return new Alimento("Peixes e algas comestíveis", 2, 3,5,"peixe",3);
            default:
                return null; 
        }
    }
}