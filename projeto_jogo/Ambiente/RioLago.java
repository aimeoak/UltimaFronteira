package Ambiente;

import Evento.Evento;
import Item.Item;
import Personagem.Personagem;
import Item.Agua;
import Item.Remedios;
import Item.Materiais;
import Item.Alimento;
import Exception.InventarioCheioException;

import java.util.Random;
import java.util.List;

public class RioLago extends Ambiente {
    
    private static final String agua = "Água em abundância, pode ser potável ou precisar de purificação";
    private static final String pesca = "Peixes podem ser uma excelente fonte de alimento";
    private static final String terreno = "O terreno é lamacento, o que pode dificultar a locomoção";

    public RioLago() {
        super("Ambiente.RioLago", agua + " " + pesca + " " + terreno, 2, new String[] {"Peixe", "Agua doce", "Alga","Alga curativa"}, 0.4, "Ensolarado");
    }

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
            try {
                // Verifica se o recurso é dos tipos permitidos
                if (recursoEncontrado.equals("Peixe") ||
                        recursoEncontrado.equals("Agua doce") ||
                        recursoEncontrado.equals("Alga curativa")||
                        recursoEncontrado.equals("Alga")) { // Removi "Item." do nome

                    // Tenta adicionar o item (método agora lança exceção)
                    jogador.getInventario().adicionarItem(itemEncontrado);
                    System.out.println(itemEncontrado.getNome() + " foi adicionado ao inventário.");

                } else {
                    System.out.println("Este item não pode ser adicionado ao inventário.");
                }
            } catch (InventarioCheioException e) {
                // Captura a exceção específica
                System.out.println("Falha ao adicionar " + itemEncontrado.getNome() + ": " + e.getMessage());
            }
        }
    }

    private Item criarItem(String recurso) {
        switch (recurso) {
            case "Agua doce":
                return new Agua("Agua", 1.0, 1,10,true);
            case "Peixe":
                return new Alimento("Peixes", 2, 3,5,"peixe",3);
            case "Alga":
                return new Alimento("Alga", 1.5, 1, 5, "Alga",10);
            case "Alga curativa":
                return new Remedios("Alga curativa",0.5,1,"Bandagem","Estancamento de ferimentos");
            default:
                return null; 
        }
    }
}