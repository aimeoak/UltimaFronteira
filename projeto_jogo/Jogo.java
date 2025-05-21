import Ambiente.Floresta;
import Ambiente.*;
import Evento.*;
import Item.*;
import Personagem.Personagem;
import Personagem.Medico;
import Personagem.Rastreador;
import Personagem.Sobrevivente;

import java.util.*;

public class Jogo {
    private Personagem jogador;
    private Ambiente ambienteAtual;
    private GerenciadorDeEventos gerenciador;
    private Scanner scanner;

    private GerenciadorDeAmbientes gerenciadorDeAmbientes;

    private int contadorTurnosNoAmbiente = 0;
    private int indiceAmbienteAtual = 0;



    public Jogo() {
        scanner = new Scanner(System.in);
        jogador = escolherPersonagem();

        List<Ambiente> ambientes = List.of(new Floresta(), new RioLago(), new Caverna(), new Montanha(), new RuinasAbandonadas());
        gerenciadorDeAmbientes = new GerenciadorDeAmbientes(ambientes, new ArrayList<>());

        ambienteAtual = ambientes.get(0);


        jogador.getInventario().adicionarItem(new Alimento("Carne", 2.0, 1, 10, "Carne", 3));
        jogador.getInventario().adicionarItem(new Agua("Água", 2.0, 1, 10, true));
        gerenciador = new GerenciadorDeEventos(0.8,inicializarEventos());

    }

    private List<Evento> inicializarEventos() {
        List<Item> recursosAbrigo = List.of(new Alimento("Carne Enlatada", 0.5, 5, 10, "Carne", 3));
        List<Item> recursosRuinas = List.of(new Alimento("Cogumelo Comestível", 0.2, 2, 8, "Cogumelo", 5));
        List<Item> recursosFonte = List.of(new Agua("Garrafa de água", 2, 2, 5, true));

        return new ArrayList<>(List.of(
                new EventoDoencaFerimento("Infecção", "infecção", 0.3, 15, "Ambiente.RioLago", "Infecção", List.of("Antibiótico")),
                new EventoDoencaFerimento("Desidratação", "desidratação", 0.4, 10, "Ambiente.RioLago", "Desidratação", List.of("Água")),
                new EventoDoencaFerimento("Fratura", "fratura", 0.2, 20, "Ambiente.Floresta", "Fratura", List.of("Analgésico")),
                new EventoDoencaFerimento("Fratura", "fratura", 0.2, 20, "Ambiente.Montanha", "Fratura", List.of("Analgésico")),
                new EventoDoencaFerimento("Fratura", "fratura", 0.2, 20, "Ambiente.Caverna", "Fratura", List.of("Analgésico")),
                new EventoDoencaFerimento("Fratura", "fratura", 0.2, 20, "Ambiente.RuinasAbandonadas", "Fratura", List.of("Analgésico")),
                new EventoCriatura("Lobo", "lobo", 0.3, 15, "Ambiente.Floresta", "lobo", 3, List.of("Fugir", "Combater")),
                new EventoCriatura("Lobo", "lobo", 0.3, 15, "Ambiente.Montanha", "lobo", 3, List.of("Fugir", "Combater")),
                new EventoCriatura("Lobo", "lobo", 0.3, 15, "Ambiente.RuinasAbandonadas", "lobo", 3, List.of("Fugir", "Combater")),
                new EventoCriatura("Cobra", "cobra", 0.4, 10, "Ambiente.Caverna", "cobra", 2, List.of("Fugir", "Combater")),
                new EventoCriatura("Cobra", "cobra", 0.4, 10, "Ambiente.RuinasAbandonadas", "cobra", 2, List.of("Fugir", "Combater")),
                new EventoCriatura("Corvo", "corvo", 0.2, 5, "Ambiente.Montanha", "corvo", 1, List.of("Fugir", "Combater")),
                new EventoClimatico("Tempestade", "tempestade", 0.4, 10, "Ambiente.Floresta", "tempestade", 3),
                new EventoClimatico("Neve", "neve", 0.3, 15, "Ambiente.Montanha", "neve", 5),
                new EventoClimatico("Incêndio", "incendio", 0.2, 20, "Ambiente.Floresta", "incendio", 4),
                new EventoDescoberta("Abrigo", "abrigo", 0.5, 0, "Ambiente.Floresta", "abrigo", recursosAbrigo, ""),
                new EventoDescoberta("Fonte", "fonte", 0.7, 0, "Ambiente.RioLago", "fonte", recursosFonte, ""),
                new EventoDescoberta("Fonte", "fonte", 0.7, 0, "Ambiente.Montanha", "fonte", recursosFonte, ""),
                new EventoDescoberta("Fonte", "fonte", 0.7, 0, "Ambiente.Caverna", "fonte", recursosFonte, ""),
                new EventoDescoberta("Fonte", "fonte", 0.7, 0, "Ambiente.RuinasAbandonadas", "fonte", recursosFonte, ""),
                new EventoDescoberta("Fonte", "fonte", 0.7, 0, "Ambiente.Floresta", "fonte", recursosFonte, ""),
                new EventoDescoberta("Ruinas", "ruinas", 0.3, 0, "Ambiente.Caverna", "ruinas", recursosRuinas, ""),
                new EventoDescoberta("Ruinas", "ruinas", 0.3, 0, "Ambiente.RuinasAbandonadas", "ruinas", recursosRuinas, "")));
    }

    public void iniciar() {
        informacoesInciais();
        System.out.println("\nAMBIENTE: \n");
        System.out.println(ambienteAtual);

        int turno = 1;
        while (true) {
            System.out.println("\n\n===TURNO: " + turno + "===");
            exibirStatusPersonagem();
            System.out.println(ambienteAtual);


            ambienteAtual.modificarClima();
            desgasteNatural();

            Evento eventoSorteado = gerenciador.sortearEvento(ambienteAtual);
            if (eventoSorteado != null) {
                System.out.println("\n[EVENTO] " + eventoSorteado.getDescricao());
                eventoSorteado.executar(jogador, ambienteAtual);
            } else {
                System.out.println("\nNenhum evento ocorreu neste turno.");
            }

            executarAcoesDojogador();

            jogador.aplicarEfeitoVeneno();
            if (jogador.getVida() <= 0 || jogador.getFome() <= 0 || jogador.getSede() <= 0 || jogador.getEnergia() <= 0 || jogador.getSanidade() <= 0) {
                System.out.println("\nVocê não resistiu... Fim de jogo.");
                System.out.println("Você sobreviveu durante " + turno + " dias.");
                break;
            }

            turno++;
            contadorTurnosNoAmbiente++;
            //if (contadorTurnosNoAmbiente >= 5) {
                //mudarParaProximoAmbiente();
            //    System.out.println("\nVocê avançou para um novo ambiente: " + ambienteAtual.getNome());
            //    contadorTurnosNoAmbiente = 0;
         //   }
            if (contadorTurnosNoAmbiente >= 5) {
                List<Ambiente> ambientes = gerenciadorDeAmbientes.getAmbientesDisponiveis();

                int proximoIndice = (indiceAmbienteAtual + 1) % ambientes.size();
                Ambiente novoAmbiente = ambientes.get(proximoIndice);
                indiceAmbienteAtual = proximoIndice;

                gerenciadorDeAmbientes.mudarAmbiente(jogador, novoAmbiente);
                ambienteAtual = novoAmbiente;

                contadorTurnosNoAmbiente = 0;
            }
        }
        scanner.close();

        System.out.println("Histórico de movimentações:");
        for (Ambiente a : gerenciadorDeAmbientes.getHistoricoMovimentacao()) {
            System.out.println("- " + a.getNome());
        }
    }

    private void consumirAlimento() {
        List<Item> itens = jogador.getInventario().getItens();
        for (Item item : itens) {
            if (item instanceof Alimento) {
                item.usar();
                ((Alimento) item).consumir(jogador);
                if (item.getDurabilidade() <= 0) {
                    jogador.getInventario().removerItem(item);
                    System.out.println(item.getNome() + " foi consumido completamente e removido do inventário.");
                }
                System.out.println("\n\nINFORMAÇÕES DO PERSONAGEM APÓS O CONSUMO DO ALIMENTO\n");
                System.out.println(jogador);
                return;
            }
        }
        System.out.println("Você não tem alimentos no inventário!");
    }

    private void consumirAgua(){
        List<Item> itens = jogador.getInventario().getItens();
        for (Item item : itens) {
            if (item instanceof Agua) {

                ((Agua) item).beber(jogador);
                if (((Agua) item).getVolume() <= 0) {
                    jogador.getInventario().removerItem(item);
                    System.out.println(item.getNome() + " foi consumida completamente e removida do inventário.");
                }
                System.out.println("\n\nINFORMAÇÕES DO PERSONAGEM APÓS O CONSUMO DA ÁGUA\n");
                System.out.println(jogador);
                return;
            }
        }
        System.out.println("Você não tem água no inventário!");
    }
    private Personagem escolherPersonagem(){
        System.out.println("Escolha seu personagem: ");
        System.out.println("1. Médico - habilidades de cura");
        System.out.println("2. Rastreador - especialista em encontrar recursos");
        System.out.println("3. Sobrevivente - mestre da resistência e da autoconsciência");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();

        switch (escolha){
            case 1:
                return new Medico(nome);
            case 2:
                return new Rastreador(nome);
            case 3:
                return new Sobrevivente(nome);
            default:
                System.out.println("Escolha inválida. Será atribuido o personagem padrão: Médico");
                return new Medico(nome);
        }
    }
    private void executarAcoesDojogador(){
        System.out.println("O que você deseja fazer? (1) Comer (2) Explorar  (3) Ação especial (4) Beber Água");
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                consumirAlimento();
                break;
            case 2:
                ambienteAtual.explorar(jogador);
                break;
            case 3:
                jogador.acaoEspecial();
                break;
            case 4:
                consumirAgua();
                break;
            default:
                System.out.println("Opção inválida!");
        }

    }
    private void desgasteNatural(){
        jogador.reduzirFome(5);
        jogador.reduzirSede(5);
        jogador.aumentarEnergia(3);
        jogador.aumentarSanidade(2);
    }
    private void exibirStatusPersonagem(){
        System.out.println("\n[STATUS DO PERSONAGEM]");
        System.out.println(jogador);
        System.out.println("\n[INVENTÁRIO]");
        jogador.getInventario().listarItens();
    }
    private void informacoesInciais(){
        System.out.println("ÙLTIMA FRONTEIRA\n\nEstas são as informações do seu personagem: \n");
        System.out.println(jogador);
        if (jogador instanceof Medico) {
            System.out.println("Você é um Médico: treinado para lidar com ferimentos e doenças, pode salvar a si mesmo e aos outros com seus conhecimentos de cura.");
        } else if (jogador instanceof Rastreador) {
            System.out.println("Você é um Rastreador: conhece os caminhos dos biomas como a palma da sua mão. Especialista em encontrar recursos escondidos.");
        } else if (jogador instanceof Sobrevivente) {
            System.out.println("Você é um Sobrevivente: já enfrentou situações extremas antes. Sua resistência física e mental é sua maior arma.");
        }

    }
    /*
    private void mudarParaProximoAmbiente() {
        indiceAmbienteAtual = (indiceAmbienteAtual + 1) % ambientesDisponiveis.size();
        ambienteAtual = ambientesDisponiveis.get(indiceAmbienteAtual);
        System.out.println("\nVocê avançou para um novo ambiente: " + ambienteAtual.getNome());
        System.out.println(ambienteAtual);
    }
    private void inicializarEventosAmbientes() {
        for (Ambiente ambiente : ambientesDisponiveis) {
            if (ambiente instanceof Floresta) {
                ambiente.adicionarEvento(new EventoCriatura("Lobo", "lobo", 0.3, 15, "Floresta", "lobo", 3, List.of("Fugir", "Combater")));
                ambiente.adicionarEvento(new EventoClimatico("Tempestade", "tempestade", 0.4, 10, "Floresta", "tempestade", 3));
                // Adicione mais eventos da Floresta aqui
            } else if (ambiente instanceof Caverna) {
                ambiente.adicionarEvento(new EventoCriatura("Morcego", "morcego", 0.25, 8, "Caverna", "morcego", 2, List.of("Fugir", "Combater")));
                // Eventos específicos da Caverna
            } else if (ambiente instanceof Montanha) {
                ambiente.adicionarEvento(new EventoClimatico("Nevasca", "nevasca", 0.3, 15, "Montanha", "nevasca", 4));
                // Eventos da Montanha
            }
            // continuar para RioLago, RuinasAbandonadas etc.
        }

     */
    }

