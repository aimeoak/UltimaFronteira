package Ambiente;

import Item.Ferramentas;
import Item.Materiais;
import Item.Armas;
import Item.Alimento;
import Item.Remedios;
import Item.Agua;
import Personagem.Personagem;
import Evento.Evento;
import Item.Item;
import Exception.InventarioCheioException;

import java.util.Random;
import java.util.List;
public class RuinasAbandonadas extends Ambiente {
  
    private static final String estruturas = "As estruturas estão bem instáveis e podem desmoronar a qualquer momento";
    private static final String ocupacao = "Algumas ruínas podem estar ocupadas por outros sobreviventes";
    private static final String clima = "Os abrigos oferecem boa proteção contra o clima";

  public RuinasAbandonadas(){
    super("Ruinas Abandonadas", estruturas + ocupacao, 3, new String[] {"Municao", "Bandagem", "Alimentos enlatados", "Rato","Agua"}, 0.5, "Ensolarado, mas "+ clima);
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
            if (recursoEncontrado.equals("Municao") ||
                    recursoEncontrado.equals("Bandagem") ||
                    recursoEncontrado.equals("Alimentos enlatados") ||
                    recursoEncontrado.equals("Rato")||
                    recursoEncontrado.equals("Agua")) {

                try {
                    jogador.getInventario().adicionarItem(itemEncontrado);
                    System.out.println(itemEncontrado.getNome() + " foi adicionado ao inventário.");
                } catch (InventarioCheioException e) {
                    System.out.println("Não foi possível adicionar " + itemEncontrado.getNome() + " ao inventário.");
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println("Este item não pode ser adicionado ao inventário.");
            }
        }
    }




       private Item criarItem(String recurso) {
           switch (recurso) {
               case "Municao":
                   return new Armas("Municao", 1.0, 10,"distancia",5,5);
               case "Bandagem":
                   return new Remedios("Bandagem",0.5,3,"Bandagem","Estancamento de ferimentos");
               case "Alimentos enlatados":
                   return new Alimento("Alimentos enlatados", 2, 3,5,"enlatado",5);
               case "Rato":
                   return new Alimento("Rato", 2.0, 1, 7,"carne",15);
               case "Agua":
                   return new Agua("Agua", 1.5, 1, 5, true);
               default:
                   return null; 
           }
       }
   }

