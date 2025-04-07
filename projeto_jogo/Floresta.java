public class Floresta extends Ambiente{
  private static final String vegetacao = "Na floresta a vegetação é densa, o que reduz a sua visibilidade e dificulta sua movimentação.";

  private static final String fauna = "Fauna abundante -> Probabilidade de caça, mas também de ataques de animais selvagens.";


   public Floresta() {
          super("Floresta", vegetacao + " " + fauna, 2, new String[] {"Frutas", "Raízes", "Cogumelos", "Madeira", "Pequenos animais"}, 0.4, "Alta umidade -> Dificulta o acendimento de fogueiras");
      }

      @Override
      public void explorar() {
          System.out.println("Você está explorando a floresta.");
      }
}
