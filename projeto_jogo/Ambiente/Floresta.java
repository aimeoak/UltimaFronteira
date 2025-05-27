package Ambiente;

import Personagem.Personagem;
import Item.Item;
import Evento.Evento;
import Item.Alimento;
import Item.Agua;
import Item.Remedios;
import Exception.InventarioCheioException;
import java.util.Random;
import java.util.List;
public class Floresta extends Ambiente {
  private static final String vegetacao = "Na floresta a vegetação é densa, o que reduz a sua visibilidade e dificulta sua movimentação.";

  private static final String fauna = "Fauna abundante -> Probabilidade de caça, mas também de ataques de animais selvagens.";


   public Floresta() {
          super("Ambiente.Floresta", vegetacao + " " + fauna, 2, new String[] {"Frutas", "Raízes", "Cogumelos", "Planta Curativa", "Coelho","Agua"}, 0.4, "Alta umidade -> Dificulta o acendimento de fogueiras");
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
            try {
                if (recursoEncontrado.equals("Frutas") ||
                        recursoEncontrado.equals("Raízes")||
                        recursoEncontrado.equals("Cogumelos")||
                        recursoEncontrado.equals("Planta Curativa")||
                        recursoEncontrado.equals("Coelho")||
                        recursoEncontrado.equals("Agua")) {

                    jogador.getInventario().adicionarItem(itemEncontrado);
                    System.out.println(itemEncontrado.getNome() + " foi adicionado ao inventário.");
                } else {
                    System.out.println("Este item não pode ser adicionado ao inventário.");
                }
            } catch (InventarioCheioException e) {
                System.out.println(e.getMessage()); // Mensagem da exceção
                System.out.println("Você precisará descartar algo para carregar este item.");
            }
        }
    }
    /*
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
     */

    private Item criarItem(String recurso) {
        switch (recurso) {
            case "Frutas":
                return new Alimento("Frutas", 1.5, 8,5,"fruta",1);
            case "Raízes":
                return new Alimento("Raízes", 0.7, 5,3,"raiz",3);
            case "Cogumelos":
                return new Alimento("Cogumelos", 1.0, 2,2,"cogumelo",3);
            case "Coelho":
                return new Alimento("Coelho", 2.0, 3, 15,"carne",15);
            case "Agua":
                return new Agua("Agua", 1.5, 1, 5, true);
            case "Planta Curativa":
                return new Remedios("Planta curativa",0.5,1,"Bandagem","Estancamento de ferimentos");
            default:
                return null; 
        }
    }
}
