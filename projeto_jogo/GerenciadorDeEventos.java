import Ambiente.Ambiente;
import Evento.Evento;
import Personagem.Personagem;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class GerenciadorDeEventos {
    private List<Evento> eventosPossiveis;
    private List<Evento> historicoEventos;
    private double probabilidadeOcorrencia;

    public GerenciadorDeEventos(double probabilidadeOcorrencia, List<Evento> eventosPossiveis){
        this.eventosPossiveis= eventosPossiveis;
        this.historicoEventos= new ArrayList<>();
        this.probabilidadeOcorrencia = probabilidadeOcorrencia;
    }

    public void adicionarEvento(Evento evento){
        eventosPossiveis.add(evento);
    }
    public Evento sortearEvento(Ambiente local){
        List<Evento> eventosValidos = new ArrayList<>();

        for(Evento a : eventosPossiveis){
            if(a.getCondicaoAtivacao().equalsIgnoreCase(local.getNome())){
                eventosValidos.add(a);
            }
        }

        if(eventosValidos.isEmpty()) {
            return null;
        }

        Random random = new Random();
        double chance = random.nextDouble();

        if (chance <= probabilidadeOcorrencia) {

            Evento sorteado = eventosValidos.get(random.nextInt(eventosValidos.size()));
            historicoEventos.add(sorteado);
            return sorteado;
        }

        return null;
    }
    public void aplicarEvento(Personagem jogador, Ambiente local){
        Evento evento = sortearEvento(local);

        if (evento != null) {
            System.out.println("\n[EVENTO APLICADO] " + evento.getDescricao());
            evento.executar(jogador, local);
        } else {
            System.out.println("Nenhum evento ocorreu neste turno.");
        }
    }
    public void removerEvento(Evento evento){
        eventosPossiveis.remove(evento);
    }
    public List<Evento>getHistoricoEventos(){
        return historicoEventos;
    }



}