import java.util.Random;
import java.util.List;
public class Floresta extends Ambiente {
  private static final String vegetacao = "Na floresta a vegetação é densa, o que reduz a sua visibilidade e dificulta sua movimentação.";

  private static final String fauna = "Fauna abundante -> Probabilidade de caça, mas também de ataques de animais selvagens.";


   public Floresta() {
          super("Floresta", vegetacao + " " + fauna, 2, new String[] {"Frutas", "Raízes", "Cogumelos", "Madeira", "Pequenos animais"}, 0.4, "Alta umidade -> Dificulta o acendimento de fogueiras");
      }

    @Override 
    public void gerarEvento(Personagem jogador) {
        List<Evento> eventosDisponiveis = getEventos();
        Random rand = new Random();

        boolean eventoOcorrido = false;

        for (Evento evento : eventosDisponiveis) {
            if (evento.getCondicaoAtivacao().equalsIgnoreCase(getNome())) {
                if (rand.nextDouble() < evento.getProbabilidadeOcorrencia()) {
                    System.out.println("Evento ativado: " + evento.getNome());
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
       @Override
    public void explorar(Personagem personagem) {
        String[] recursos = getRecursos();
        Random rand = new Random();
        int index = rand.nextInt(recursos.length);

        String recursoEncontrado = recursos[index];

        System.out.println("Você encontrou " + recursoEncontrado);

        Item itemEncontrado = criarItem(recursoEncontrado);

        if (itemEncontrado != null) {
            
            if (recursoEncontrado.equals("Pedras preciosas e metais") || recursoEncontrado.equals("Água de degelo")) {
                if (personagem.getInventario().adicionarItem(itemEncontrado)) {
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
                return new Alimentos("Frutas", 1.5, 8); 
            case "Raízes":
                return new Alimentos("Raízes", 0.7, 5);
            case "Cogumelos":
                return new Alimentos("Cogumelos", 1.0, 2); 
            case "Pequenos animais":
                return new Alimentos("Carne", 2.0, 12); 
            default:
                return null; 
        }
    }
}
