package Ambiente;

import Personagem.Personagem;
import Item.Item;
import Evento.Evento;
import Item.Alimento;

import java.util.Random;
import java.util.List;
public class Floresta extends Ambiente {
  private static final String vegetacao = "Na floresta a vegetação é densa, o que reduz a sua visibilidade e dificulta sua movimentação.";

  private static final String fauna = "Fauna abundante -> Probabilidade de caça, mas também de ataques de animais selvagens.";


   public Floresta() {
          super("Ambiente.Floresta", vegetacao + " " + fauna, 2, new String[] {"Frutas", "Raízes", "Cogumelos", "Madeira", "Pequenos animais"}, 0.4, "Alta umidade -> Dificulta o acendimento de fogueiras");
      }
    @Override
    public void gerarEvento() {
        List<Evento> eventosDisponiveis = getEventos();
        Random rand = new Random();

        boolean eventoOcorrido = false;

        for (Evento evento : eventosDisponiveis) {
            if (evento.getCondicaoAtivacao().equalsIgnoreCase(getNome())) {
                if (rand.nextDouble() < evento.getProbabilidadeOcorrencia()) {
                    System.out.println("Evento.Evento ativado: " + evento.getNome());
                    System.out.println(evento.getDescricao());
                    evento.executar(null, this);
                    eventoOcorrido = true;
                }
            }
        }

        if (!eventoOcorrido) {
            System.out.println("Nenhum evento ocorreu.");
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
            
            if (recursoEncontrado.equals("Pedras preciosas e metais") || recursoEncontrado.equals("Água de degelo")) {
                if (jogador.getInventario().adicionarItem(itemEncontrado)) {
                    System.out.println(itemEncontrado.getNome() + " foi adicionado ao inventário.");
                } else {
                    System.out.println("Não foi possível adicionar " + itemEncontrado.getNome() + " ao inventário.");
                }
            } else {
                System.out.println("Este item não pode ser adicionado ao inventário.");
            }
        }
    }

    private Item criarItem(String recurso) {
        switch (recurso) {
            case "Frutas":
                return new Alimento("Frutas", 1.5, 8,5,"fruta",1);
            case "Raízes":
                return new Alimento("Raízes", 0.7, 5,3,"raiz",3);
            case "Cogumelos":
                return new Alimento("Cogumelos", 1.0, 2,2,"cogumelo",3);
            case "Pequenos animais":
                return new Alimento("Carne", 2.0, 12,5,"carne",3);
            default:
                return null; 
        }
    }
}
