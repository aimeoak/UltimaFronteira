public class RuinasAbandonadas extends Ambiente{
  
    private String estruturas = "As estruturas estão bem instáveis e podem desmoronar a qualquer momento";
    private String ocupacao = "Algumas ruínas podem estar ocupadas por outros sobreviventes";
    private String clima = "Os abrigos oferecem boa proteção contra o clima";

  public RuinasAbandonadas(){
    super("Ruinas Abandonadas", estruturas + ocupacao, 3, new String[] {"Municao", "Ferramentas", "Alimentos enlatados", "Mapas e pistas"}, 0.5, "Ensolarado, mas "+ clima);
  }

   @Override 
       public void gerarEvento(Personagem jogador) {
           List<Evento> eventosDisponiveis = getEventos();
           Random rand = new Random();

           boolean eventoOcorrido = false;

           for (Evento evento : eventosDisponiveis) {
               if (evento.getCondicaoAtivacao().equalsIgnoreCase(getNome())) {
                   if (rand.nextDouble() < evento.getProbabilidadeOcorrencia()) {
                       System.out.println("Evento ativado: " + evento.getNome());
                       System.out.println(evento.getDescricao());
                       evento.executar(jogador, this);
                       eventoOcorrido = true;
                   }
               }
           }

           if (!eventoOcorrido) {
               System.out.println("Nenhum evento ocorreu.");
           }
       }

       @Override
       public void explorar(Personagem jogador) {
           String[] recursos = getRecursos();
           Random rand = new Random();
           int index = rand.nextInt(recursos.length);

           String recursoEncontrado = recursos[index];

           System.out.println("Você encontrou " + recursoEncontrado);

           Item itemEncontrado = criarItem(recursoEncontrado);

           if (itemEncontrado != null) {

               if (recursoEncontrado.equals("Municao") || recursoEncontrado.equals("Ferramentas") || recursoEncontrado.equals("Alimentos enlatados") || recursoEncontrado.equals("Mapas e pistas")) {

                   if (jogador.getInventario().adicionarItem(itemEncontrado)) {
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
               case "Municao":
                   return new Armas("Municao", 1.0, 10);
               case "Ferramentas":
                   return new Ferramentas("Ferramentas", 5, 2); 
               case "Alimentos enlatados":
                   return new Alimento("Alimentos enlatados", 2, 3);
               case "Mapas e pistas":
                  return new Materiais("Mapas e pistas", 2, 3);
               default:
                   return null; 
           }
       }
   }
}