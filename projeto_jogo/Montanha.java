import java.util.Random;
import java.util.List;

public class Montanha extends Ambiente {
    private static final String vegetacao = "Na montanha a vegetação é escassa, o que dificulta a busca por recursos."; 
    private static final String clima = "O clima na montanha é frio e instável. Nevascas e ventos fortes podem acontecer repentinamente";
    private static final String terreno = "O terreno na montanha é acidentado, o que exige maior gasto de energia para explorá-lo";

    public Montanha() {
        super("Montanha", vegetacao + " " + terreno, 4, new String[] {"Pedras preciosas e metais", "Refúgio em cavernas seguras", "Água de degelo"}, 0.6, clima);
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
            System.out.println("Evento sorteado: " + escolhido.getNome());
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
        System.out.println("Você encontrou " + recursos[index]);
    }
}
