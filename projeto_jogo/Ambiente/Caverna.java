package Ambiente;

import Personagem.Personagem;
import Item.Item;
import Evento.Evento;
import Item.Materiais;




import java.util.Random;
import java.util.List;
public class Caverna extends Ambiente {
  
    private static final String luz = "Baixa iluminação: para uma exploração eficiente será necessária uma lanterna ou tocha";
    private static final String criaturas = "Pode ter a presença de criaturas desconhecidas";
  private static final String aguaGotas = "É um local com possivelmente a presença de água de gotejamento";

    public Caverna() {
        super("Ambiente.Ambiente.Caverna", luz + criaturas + aguaGotas, 1, new String[] {"Pedras preciosas e metais", "Pequenos lagos subterrâneos", "Ossos e vestígios de exploradores antigos"}, 0.2, "Frio");
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
                    
                    if (recursoEncontrado.equals("Pedras preciosas e metais") || recursoEncontrado.equals("Ossos e vestígios de exploradores antigos")) {
                      
                        if (personagem.getInventario().adicionarItem(itemEncontrado)) {
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
                    case "Pedras preciosas e metais":
                        return new Materiais("Pedra preciosa", 1.0, 10, "Minério", 100); 
                    case "Ossos e vestígios de exploradores antigos":
                        return new Materiais("Ossos e vestígios de exploradores antigos", 5.0, 2, "Histórico", 50);
                    default:
                        return null; 
                }
            }
        }
