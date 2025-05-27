package Ambiente;

import Personagem.Personagem;
import Item.Item;
import Evento.Evento;
import Item.Materiais;
import Item.Remedios;
import Item.Agua;
import Item.Alimento;
import Exception.InventarioCheioException;



import java.util.Random;
import java.util.List;
public class Caverna extends Ambiente {
  
    private static final String luz = "Baixa iluminação: para uma exploração eficiente será necessária uma lanterna ou tocha";
    private static final String criaturas = "Pode ter a presença de criaturas desconhecidas";
  private static final String aguaGotas = "É um local com possivelmente a presença de água de gotejamento";

    public Caverna() {
        super("Ambiente.Caverna", luz + criaturas + aguaGotas, 1, new String[] {"Pedras preciosas e metais", "Agua doce", "Antibiotico","Bandagem","Morcego","Rato"}, 0.2, "Frio");
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
            try {
                if (recursoEncontrado.equals("Pedras preciosas e metais") ||
                        recursoEncontrado.equals("Agua doce")||
                        recursoEncontrado.equals("Antibiotico")||
                        recursoEncontrado.equals("Bandagem")||
                        recursoEncontrado.equals("Morcego")||
                        recursoEncontrado.equals("Rato")) {

                    // Tenta adicionar o item e captura a exceção se ocorrer
                    personagem.getInventario().adicionarItem(itemEncontrado);
                    System.out.println(itemEncontrado.getNome() + " foi adicionado ao inventário.");
                } else {
                    System.out.println("Este item não pode ser adicionado ao inventário.");
                }
            } catch (InventarioCheioException e) {
                // Captura a exceção específica de inventário cheio
                System.out.println("Não foi possível adicionar " + itemEncontrado.getNome() + ": " + e.getMessage());
            }
        }
    }

            private Item criarItem(String recurso) {
                switch (recurso) {
                    case "Pedras preciosas e metais":
                        return new Materiais("Pedra preciosa", 1.0, 10, "Minério", 100);
                    case "Agua doce":
                        return new Agua("Agua", 1.0, 1,10,true);
                    case "Antibiotico":
                        return new Remedios("Antibiotico",2,2,"Antibiotico","Alívio imediato da dor");
                    case "Bandagem":
                        return new Remedios("Bandagem",0.5,3,"Bandagem","Estancamento de ferimentos");
                    case "Morcego":
                        return new Alimento("Morcego", 2.0, 3, 7,"carne",15);
                    case "Rato":
                        return new Alimento("Rato", 0.8, 2, 5, "carne", 15);
                    default:
                        return null; 
                }
            }
        }
