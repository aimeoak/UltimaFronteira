package LogicaJogo;

import Ambiente.Floresta;
import Ambiente.*;
import Evento.*;
import Item.*;
import Exception.MorteException;
import Personagem.Personagem;
import Personagem.Medico;
import Personagem.Rastreador;
import Personagem.Sobrevivente;
import LogicaJogo.GerenciadorDeAmbientes;

import java.util.*;

public class Jogo {
    private Personagem jogador;
    //private Ambiente ambienteAtual;
    private GerenciadorDeEventos gerenciador;
    private Scanner scanner;

    private GerenciadorDeAmbientes gerenciadorDeAmbientes;

    private int contadorTurnosNoAmbiente = 0;
    private int indiceAmbienteAtual = 0;
    private int dificuldade = 1;


    public Jogo() {
        scanner = new Scanner(System.in);
        jogador = escolherPersonagem();

        List<Ambiente> ambientes = List.of(new Floresta(), new RioLago(), new Caverna(), new Montanha(), new RuinasAbandonadas());
        gerenciadorDeAmbientes = new GerenciadorDeAmbientes(ambientes, new ArrayList<>());


        jogador.getInventario().adicionarItem(new Alimento("Carne", 2.0, 1, 10, "Carne", 3));
        jogador.getInventario().adicionarItem(new Agua("Agua", 2.0, 1, 10, true));
        gerenciador = new GerenciadorDeEventos(0.8, inicializarEventos());

    }

    private List<Evento> inicializarEventos() {
        List<Item> recursosAbrigo = List.of(
                new Alimento("Carne Enlatada", 0.5, 5, 10, "Carne", 3),
                new Remedios("Antibiotico", 2, 1, "Antibiotico", "Alívio imediato da dor"),
                new Remedios("Bandagem", 0.5, 2, "Bandagem", "Estancamento de ferimentos")
        );
        List<Item> recursosRuinas = List.of(
                new Alimento("Cogumelo Comestível", 0.2, 2, 8, "Cogumelo", 5),
                new Remedios("Bandagem", 0.5, 3, "Bandagem", "Estancamento de ferimentos"),
                new Remedios("Analgesico", 4, 1, "Analgesico", "Alívio imediato da dor")
        );
        List<Item> recursosFonte = List.of(
                new Agua("Agua", 2, 2, 5, true),
                new Remedios("Bandagem", 0.5, 3, "Bandagem", "Estancamento de ferimentos"),
                new Remedios("Antibiotico", 2, 1, "Antibiotico", "Alívio imediato da dor")
        );

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
        int turno = 1;
        try {
            informacoesInciais();
            System.out.println("\nAMBIENTE: \n");
            System.out.println(gerenciadorDeAmbientes.getAmbienteAtual());


            while (true) {
                System.out.println("\n\n=== TURNO: " + turno + " ===");
                atualizarDificuldade(turno);
                verificarMorte();
                exibirStatusPersonagem();
                System.out.println(gerenciadorDeAmbientes.getAmbienteAtual());
                gerenciadorDeAmbientes.getAmbienteAtual().modificarClima();
                desgasteNatural();

                Evento eventoSorteado = gerenciador.sortearEvento(gerenciadorDeAmbientes.getAmbienteAtual());
                if (eventoSorteado != null) {
                    System.out.println("\n[EVENTO] " + eventoSorteado.getDescricao());
                    eventoSorteado.executar(jogador, gerenciadorDeAmbientes.getAmbienteAtual());
                } else {
                    System.out.println("\nNenhum evento ocorreu neste turno.");
                }
                executarAcoesDoJogador();
                jogador.aplicarEfeitoVeneno();
                contadorTurnosNoAmbiente++;
                if (contadorTurnosNoAmbiente >= 5) {
                    escolherAmbiente();

                    contadorTurnosNoAmbiente = 0;
                }
                turno++;
                if (verificarVitoria(turno)) {
                    break;
                }
            }

        } catch (MorteException e) {
            System.out.println("\n\n=== FIM DE JOGO ===");
            System.out.println("Motivo: " + e.getCausa());
            System.out.println(e.getMessage());
            System.out.println("Sobreviveu por " + (turno - 1) + " turnos");

        } finally {
            scanner.close();
            System.out.println("Histórico de movimentações:");
            for (Ambiente a : gerenciadorDeAmbientes.getHistoricoMovimentacao()) {
                System.out.println("- " + a.getNome());
            }
        }
    }

    private void verificarMorte() {
        if (jogador.getVida() <= 0) {
            throw new MorteException("Sua vida chegou a zero!", "Ferimentos graves");
        }
        if (jogador.getFome() <= 0) {
            throw new MorteException("Você morreu de fome!", "Fome extrema");
        }
        if (jogador.getSede() <= 0) {
            throw new MorteException("Você morreu de sede!", "Desidratação");
        }
        if (jogador.getSanidade() <= 0) {
            throw new MorteException("Você perdeu a sanidade!", "Loucura");
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

    private void usarMedicamento() {
        List<Item> itens = jogador.getInventario().getItens();
        for (Item item : itens) {
            if (item instanceof Remedios) {
                item.usar();
                ((Remedios) item).usar(jogador);
                if (item.getDurabilidade() <= 0) {
                    jogador.getInventario().removerItem(item);
                    System.out.println(item.getNome() + " foi usado completamente e removido do inventário.");
                }
                System.out.println("\n\nINFORMAÇÕES DO PERSONAGEM APÓS APLICAR MEDICAMENTO\n");
                System.out.println(jogador);
                return;
            }
        }
        System.out.println("Você não tem remedios no inventário!");
    }

    private void consumirAgua() {
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

    private void escolherAmbiente() {
        System.out.println("Escolha o próximo ambiente a ser explorado: ");
        System.out.println("1. Floresta");
        System.out.println("2. Caverna");
        System.out.println("3. Montanha");
        System.out.println("4. Rio/Lago");
        System.out.println("5. Ruínas abandonadas");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        List<Ambiente> ambientes = List.of(
                new Floresta(),
                new Caverna(),
                new Montanha(),
                new RioLago(),
                new RuinasAbandonadas()
        );

        Ambiente novoAmbiente = null;

        switch (escolha) {
            case 1:
                novoAmbiente = new Floresta();
                break;
            case 2:
                novoAmbiente = new Caverna();
                break;
            case 3:
                novoAmbiente = new Montanha();
                break;
            case 4:
                novoAmbiente = new RioLago();
                break;
            case 5:
                novoAmbiente = new RuinasAbandonadas();
                break;
            default:
                System.out.println("Escolha inválida.");
                return;
        }

        gerenciadorDeAmbientes.mudarAmbiente(jogador, novoAmbiente);

    }

    private Personagem escolherPersonagem() {
        System.out.println("Escolha seu personagem: ");
        System.out.println("1. Médico - habilidades de cura");
        System.out.println("2. Rastreador - especialista em encontrar recursos");
        System.out.println("3. Sobrevivente - mestre da resistência e da autoconsciência");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();

        switch (escolha) {
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

    private void executarAcoesDoJogador() {
        System.out.println("O que você deseja fazer? (1) Comer (2) Explorar  (3) Ação especial (4) Beber Água (5) Usar Medicamento");
        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                consumirAlimento();
                break;
            case 2:
                gerenciadorDeAmbientes.getAmbienteAtual().explorar(jogador);
                break;
            case 3:
                jogador.acaoEspecial();
                break;
            case 4:
                consumirAgua();
                break;
            case 5:
                usarMedicamento();
                break;
            default:
                System.out.println("Opção inválida!");
        }

    }

    private void desgasteNatural() {
        int reducao = 5 + dificuldade;
        jogador.reduzirFome(reducao);
        jogador.reduzirSede(reducao);
        jogador.aumentarEnergia(5);
        jogador.aumentarSanidade(5-dificuldade);
    }

    private void exibirStatusPersonagem() {
        System.out.println("\n[STATUS DO PERSONAGEM]");
        System.out.println(jogador);
        System.out.println("\n[INVENTÁRIO]");
        jogador.getInventario().listarItens();
        System.out.println();
    }

    private void informacoesInciais() {
        System.out.println("\nULTIMA FRONTEIRA\n\nEstas são as informações do seu personagem: \n");
        System.out.println(jogador);
        if (jogador instanceof Medico) {
            System.out.println("Você é um Médico: treinado para lidar com ferimentos e doenças, pode salvar a si mesmo e aos outros com seus conhecimentos de cura.");
        } else if (jogador instanceof Rastreador) {
            System.out.println("Você é um Rastreador: conhece os caminhos dos biomas como a palma da sua mão. Especialista em encontrar recursos escondidos.");
        } else if (jogador instanceof Sobrevivente) {
            System.out.println("Você é um Sobrevivente: já enfrentou situações extremas antes. Sua resistência física e mental é sua maior arma.");
        }

    }

    private boolean verificarVitoria(int turno) {
        if (turno > 25) {
            System.out.println("\n\n=== VITÓRIA ===");
            System.out.println("Parabéns jogador, você sobreviveu por 25 dias sozinho!");
            System.out.println("Um helicóptero sobrevoou a região e conseguiu localizá-lo.");
            System.out.println("Você foi resgatado com sucesso!");
            return true;
        }
        return false;
    }

    private void atualizarDificuldade(int turno) {
        this.dificuldade = 1 + turno / 5;
        System.out.println("DIFICULDADE ATUAL: nível " + dificuldade);

    }
}

