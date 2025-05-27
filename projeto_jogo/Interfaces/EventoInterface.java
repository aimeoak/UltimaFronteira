package Interfaces;

import Ambiente.Ambiente;
import Personagem.Personagem;

public interface EventoInterface {
    void executar(Personagem jogador, Ambiente local);
    String getDescricao();
}
