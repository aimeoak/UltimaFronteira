package Evento;

import Personagem.Personagem;
import Item.Item;
import Item.Alimento;
import Item.Agua;
import Ambiente.Ambiente;
import Exception.InventarioCheioException;

import java.util.List;
import java.util.Arrays;
import java.util.Random;
public class EventoDescoberta extends Evento {
    private String tipoDescoberta; //Ambiente.Ambiente.Caverna, abrigo, suprimentos abandonados
    private List<Item> recursosEncontrados; //Comida, agua, ferramentas, armas
    private String condicaoEspecial;

    public EventoDescoberta(String nome, String descricao, double probabilidadeOcorrencia, int impacto, String condicaoAtivacao , String tipoDescoberta, List<Item> recursosEncontrados, String condicaoEspecial) {
        super(nome, descricao, probabilidadeOcorrencia, impacto, condicaoAtivacao);
        this.tipoDescoberta = tipoDescoberta;
        this.recursosEncontrados=recursosEncontrados;
        this.condicaoEspecial = condicaoEspecial;
    }

    /*@Override
    public void executar(Personagem.Personagem jogador, Ambiente.Ambiente local) {

        if (tipoDescoberta.equalsIgnoreCase("abrigo")) {
            jogador.aumentarEnergia(5);
            jogador.aumentarSanidade(5);

        } else if (tipoDescoberta.equalsIgnoreCase("fonte")) {
            jogador.aumentarSanidade(5);


        } else if (tipoDescoberta.equalsIgnoreCase("ruinas")) {
            jogador.aumentarSanidade(5);

        } else {
            System.out.println("Você não encontrou nada útil.");

        }
    }
    @Override
    public void executar(Personagem jogador, Ambiente local) {
        Random random = new Random();
        Item itemEncontrado = null;

        switch (tipoDescoberta.toLowerCase()) {
            case "abrigo":
                jogador.aumentarEnergia(5);
                jogador.aumentarSanidade(5);

                List<Item> itensAbrigo = Arrays.asList(
                        new Alimento("Carne seca", 1, 5, 10, "carne", 3),
                        new Alimento("Sopa", 1, 3, 5, "enlatado", 10)
                );

                itemEncontrado = itensAbrigo.get(random.nextInt(itensAbrigo.size()));
                break;

            case "fonte":
                jogador.aumentarSanidade(5);

                List<Item> itensFonte = Arrays.asList(
                        new Alimento("Água", 0.7, 2, 4, "agua", 8),
                        new Alimento("Fruta", 0.5, 1, 3, "fruta", 4)
                );

                itemEncontrado = itensFonte.get(random.nextInt(itensFonte.size()));
                break;

            case "ruinas":
                jogador.aumentarSanidade(5);

                List<Item> itensRuinas = Arrays.asList(
                        new Alimento("Cogumelo Antigo", 0.5, 2, 6, "cogumelo", 6),
                        new Alimento("Carne enlatada", 1.5, 3, 7, "carne", 5)
                );

                itemEncontrado = itensRuinas.get(random.nextInt(itensRuinas.size()));
                break;

            default:
                System.out.println("Você não encontrou nada útil.");
                return;
        }

        if (itemEncontrado != null) {
            System.out.println("Você encontrou: " + itemEncontrado.getNome());
            boolean adicionado = jogador.getInventario().adicionarItem(itemEncontrado);
            if (!adicionado) {
                System.out.println("O item não pôde ser adicionado ao inventário (sem espaço).");
            }
        }
    }
    */
    @Override
    public void executar(Personagem jogador, Ambiente local) {
        Random random = new Random();
        Item itemEncontrado = null;

        switch (tipoDescoberta.toLowerCase()) {
            case "abrigo":
                jogador.aumentarEnergia(5);
                jogador.aumentarSanidade(5);

                List<Item> itensAbrigo = Arrays.asList(
                        new Alimento("Carne seca", 1, 5, 10, "carne", 3),
                        new Alimento("Sopa", 1, 3, 5, "enlatado", 10)
                );

                itemEncontrado = itensAbrigo.get(random.nextInt(itensAbrigo.size()));
                break;

            case "fonte":
                jogador.aumentarSanidade(5);

                List<Item> itensFonte = Arrays.asList(
                        new Agua("Agua", 0.7, 2, 4, true),
                        new Alimento("Fruta", 0.5, 1, 3, "fruta", 4)
                );

                itemEncontrado = itensFonte.get(random.nextInt(itensFonte.size()));
                break;

            case "ruinas":
                jogador.aumentarSanidade(5);

                List<Item> itensRuinas = Arrays.asList(
                        new Alimento("Cogumelo Antigo", 0.5, 2, 6, "cogumelo", 6),
                        new Alimento("Carne enlatada", 1.5, 3, 7, "carne", 5)
                );

                itemEncontrado = itensRuinas.get(random.nextInt(itensRuinas.size()));
                break;

            default:
                System.out.println("Você não encontrou nada útil.");
                return;
        }

        if (itemEncontrado != null) {
            System.out.println("Você encontrou: " + itemEncontrado.getNome());

            try {
                jogador.getInventario().adicionarItem(itemEncontrado);
                System.out.println(itemEncontrado.getNome() + " foi adicionado ao inventário.");
            } catch (InventarioCheioException e) {
                System.out.println("Não foi possível adicionar " + itemEncontrado.getNome() + " ao inventário.");
                System.out.println(e.getMessage());
            }
        }
    }



    private String gerarDescricao() {
        switch (tipoDescoberta.toLowerCase()) {
            case "abrigo":
                return "Você encontrou um abrigo velho e silencioso, talvez tenham esquecido comida e objetos úteis em algum lugar...";
            case "fonte":
                return "Você encontra uma pequena nascente borbulhante, a água parece potável.";
            case "ruinas":
                return "Você avista ruínas cobertas por trepadeiras, elas guardam segredos antigos e possivelmente há itens raros entre os escombros.";
            default:
                return "Você vasculha a área em busca de algo útil... mas encontra apenas silêncio e vestígios do tempo. Nada parece valer a pena aqui — pelo menos por enquanto.";
        }
    }
    @Override
    public String getDescricao() {
        return gerarDescricao();
    }


}
