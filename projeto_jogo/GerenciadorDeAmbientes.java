import Ambiente.Ambiente;
import Evento.Evento;
import Personagem.Personagem;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class GerenciadorDeAmbientes {
    private List<Ambiente> ambientesDisponiveis;
    private List<Ambiente> historicoMovimentacao;

    public GerenciadorDeAmbientes(List<Ambiente> ambientesDisponiveis, List<Ambiente> historicoMovimentacao) {
        this.ambientesDisponiveis = ambientesDisponiveis;
        this.historicoMovimentacao = new ArrayList<>();
    }

    public void mudarAmbiente(Personagem jogador, Ambiente novoAmbiente) {
        historicoMovimentacao.add(novoAmbiente);
        System.out.println("Você se moveu para: " + novoAmbiente.getNome());
    }
    private Random random = new Random();

    public void gerarEvento(Ambiente local, Personagem jogador){
        if (local.getEventos().isEmpty()) {
            System.out.println("Nenhum evento neste ambiente.");
            return;
        }

        Evento evento = local.getEventos().get(random.nextInt(local.getEventos().size()));
        System.out.println("Um evento ocorreu: " +evento.getDescricao());
        evento.executar(jogador, local);
    }


    public List<Ambiente> getHistoricoMovimentacao() {
        return historicoMovimentacao;
    }


    public List<Ambiente> getAmbientesDisponiveis() {
        return ambientesDisponiveis;
    }
}