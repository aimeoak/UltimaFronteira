import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ÙLTIMA FRONTEIRA\n\nEstas são as informações do seu personagem: \n");
        Medico jogador = new Medico("João");
        Alimento carne = new Alimento("Carne", 2.0, 1, 10, "Carne", 3);
        System.out.println(jogador);
        jogador.getInventario().adicionarItem(carne);

        Floresta floresta = new Floresta(); 
        System.out.println("\nAMBIENTE: \n");
        System.out.print(floresta);

        List<Item> recursosAbrigo = new ArrayList<>();
        recursosAbrigo.add(new Alimento("Carne Enlatada", 0.5, 5, 10, "Carne", 3));
        List<Item> recursosRuinas = new ArrayList<>();
        recursosRuinas.add(new Alimento("Cogumelo Comestível", 0.2, 2, 8, "Cogumelo", 5));
        List<Item> recursosFonte = new ArrayList<>();
        recursosFonte.add(new Alimento("Água Purificada", 0.3, 3, 5, "Água", 10));

        List<Evento> listaEventos = new ArrayList<>();
        listaEventos.add(new EventoDoencaFerimento("Infecção", "infecção", 0.3, 15, "Floresta", "Infecção", Arrays.asList("Antibiótico")));
        listaEventos.add(new EventoDoencaFerimento("Desidratação", "desidratação", 0.4, 10, "Floresta", "Desidratação",Arrays.asList("Água")));
        listaEventos.add(new EventoDoencaFerimento("Fratura", "fratura", 0.2, 20, "Floresta", "Fratura",Arrays.asList("Analgésico")));
        listaEventos.add(new EventoCriatura("Lobo", "lobo", 0.3, 15, "Floresta", "lobo", 3, Arrays.asList("Fugir", "Combater")));
        listaEventos.add(new EventoCriatura("Cobra", "cobra", 0.4, 10, "Floresta", "cobra", 2, Arrays.asList("Fugir", "Combater")));
        listaEventos.add(new EventoCriatura("Corvo", "corvo", 0.2, 5, "Floresta", "corvo", 1, Arrays.asList("Fugir", "Combater")));
        listaEventos.add(new EventoClimatico("Tempestade", "tempestade", 0.4, 10, "Floresta", "tempestade", 3));
        listaEventos.add(new EventoClimatico("Neve", "neve", 0.3, 15, "Floresta", "neve", 5));
        listaEventos.add(new EventoClimatico("Incêndio", "incendio", 0.2, 20, "Floresta", "incendio", 4));
        listaEventos.add(new EventoDescoberta("Abrigo","abrigo",0.5,0,"Floresta","abrigo",recursosAbrigo,""));
        listaEventos.add(new EventoDescoberta("Fonte","fonte",0.7,0,"Floresta","fonte",recursosFonte,""));
        listaEventos.add(new EventoDescoberta("Ruinas","ruinas",0.3,0,"Floresta","ruinas",recursosRuinas,""));

        GerenciadorDeEventos gerenciador = new GerenciadorDeEventos(0.8,listaEventos);


        int turno = 1;
        while (true){
            System.out.println("\n\n===TURNO: " + turno + "===" );
            //FASE INCIO e desgaste natural
            System.out.println("\n[STATUS DO PERSONAGEM]");
            System.out.println(jogador);
            System.out.println("\n[INVENTÁRIO]");
            jogador.getInventario().listarItens();
            System.out.println();
            floresta.modificarClima();

            jogador.reduzirFome(5);
            jogador.reduzirSede(5);
            jogador.aumentarEnergia(3);
            jogador.aumentarSanidade(2);

            //FASE EVENTOS ALEATORIOS
            Evento eventoSorteado = gerenciador.sortearEvento(floresta);
            if (eventoSorteado != null) {
                System.out.println("\n[EVENTO] " + eventoSorteado.getDescricao());
                eventoSorteado.executar(jogador, floresta);
            } else {
                System.out.println("\nNenhum evento ocorreu neste turno.");
            }

            //FASE AÇÃO
            System.out.println("O que você deseja fazer? (1) Comer (2) Explorar  (3) Curar (4) Status");
            int escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        boolean consumiu = false;
                        List<Item> itens = jogador.getInventario().getItens();

                        for (int i = 0; i < itens.size(); i++) {
                            Item item = itens.get(i);
                            if (item instanceof Alimento) {
                                item.usar(); // Reduz durabilidade
                                ((Alimento) item).consumir(jogador); // Aplica os efeitos do alimento

                                if (item.getDurabilidade() <= 0) {
                                    jogador.getInventario().removerItem(item);
                                    System.out.println(item.getNome() + " foi consumido completamente e removido do inventário.");
                                }

                                consumiu = true;
                                break; // Consome apenas um alimento por turno
                            }
                        }

                        if (!consumiu) {
                            System.out.println("Você não tem alimentos no inventário!");
                        } else {
                            System.out.println("\n\nINFORMAÇÕES DO PERSONAGEM APÓS O CONSUMO DO ALIMENTO\n");
                            System.out.println(jogador);
                        }
                        break;

                    case 2:
                        floresta.explorar(jogador);
                        break;
                    case 3:
                        jogador.curar();
                        System.out.println("\n\n[STATUS PÓS-CURA]\n" + jogador);
                        break;
                    case 4:
                        System.out.println("\n[STATUS ATUAL]\n" + jogador);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }

                //FASE MANUNTENÇÃO
                jogador.aplicarEfeitoVeneno();
                if(jogador.getVida() <= 0|| jogador.getFome() <=0 || jogador.getSede() <=0 || jogador.getEnergia() <=0|| jogador.getSanidade() <=0){
                    System.out.println("\nVocê não resistiu... Fim de jogo.");
                    System.out.println("Você sobreviveu durante " + turno + " dias.");
                    break;
                }
                turno++;


        }

        scanner.close();
        

    }

}
