import java.util.Scanner;

public class Jogo {
    
    public Jogo() {
        Scanner s = new Scanner(System.in);
        String[] ilha = new String[15];
        String[] posicoesExploradas = new String[ilha.length];
        iniciar();
        inicializarIlha(ilha);
        inicializarControleExplorado(posicoesExploradas);
        exibirMenu(s,ilha,posicoesExploradas);

        
        
        s.close();
    }

    public void iniciar() {
        System.out.println("Iniciou o jogo");
    }

    private void exibirMenu(Scanner s, String[] ilha, String[] posicoesExploradas) {
        int opcao;
        do {
            System.out.println("\n ===== ILHA DOS TESOUROS =====");
            System.out.println("1 - Mostrar instruções");
            System.out.println("2 - Mostrar mapa");
            System.out.println("3 - Jogar");
            System.out.println("4 - Mostrar status");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção acima: ");
            opcao = s.nextInt();

            lerOpcaoMenu(s, opcao, ilha, posicoesExploradas);

        } while (opcao != 5);

    }
    private void lerOpcaoMenu(Scanner s, int opcao, String[] ilha, String[] posicoesExploradas) {
        switch (opcao) {
                case 1:
                    exibirInstrucoes();
                break;
                case 2:
                    mostrarMapaExplorado(posicoesExploradas);

                break;
                case 3:
                    lerPosicaoEValidar(s, ilha, posicoesExploradas);
                break;
                case 4:
                    mostrarStatus();
                break;
                case 5:
                    mostrarResultadoFinal();
                break;
            }
    }
    private void exibirInstrucoes() {

        System.out.println("\n --- INSTRUÇÕES ---");
                    System.out.println("1. Como escolher uma posição: (ainda preciso colocar) ");
                    System.out.println("2. Quais são os tipos de tesouros? ");
                    System.out.println(
                            " A Ilha dos Tesouros possui muitas riquezas e tesouros vastos. Nela você encontrará relíquias como: OURO, DIAMANTE OU RUBI."

                    );
                    System.out.println("3. Armadilhas! ");
                    System.out.println(
                            "Mas, apesar de ser uma ilha com tantas belezas, ela está repleta de armadilhas! \r\n" + //
                                    "No meio do caminho, você pode acabar dando de cara com: Buracos profundos, cobras das mais peçonhentas que existem e dos espinhos mais afiados possíveis!" + 
                                    " \n\r Esteja avisado, explore a ilha com sua própria conta em risco!"
                                    + //
                                    "");
                    System.out.println("4. Pontuação: ");
                    System.out.println("A pontuação funciona da seguinte maneira . . . (ainda tenho que colocar)");
                    System.out.println("5. O MAIS IMPORTANTE: ");
                    System.out.println("O jogador possui X tentativas. Boa sorte!");
    }
    private void inicializarIlha(String[] ilha) {
        for (int i = 0; i < ilha.length; i++) {
            ilha[i] = sortear();
        }
    }

    private String sortear() {
        String[] tipos = {"VAZIO", "OURO", "DIAMANTE","RUBI", "BURACO", "COBRA", "ESPINHOS"};
        int posicao = (int)(Math.random() * 7);
        return tipos[posicao];

    }
    
    private void inicializarControleExplorado(String[] posicoesExploradas) {
        for (int i = 0; i < posicoesExploradas.length; i++) {
            posicoesExploradas[i] = "?";
        }
    }

    private void mostrarMapaExplorado(String[] posicoesExploradas) {
        for (int i = 0; i < posicoesExploradas.length; i++) {
            System.out.println("[" + i + "] " + posicoesExploradas[i]);
        }

    }
    private void mostrarStatus() {

    }
    private void lerPosicaoEValidar(Scanner s, String[] ilha, String[] posicoesExploradas) {
        
        System.out.println("Digite a posição a ser explorada: ");

        int posicao = s.nextInt();
        
        while(posicao <= 0 || posicao > ilha.length){
            System.out.print("Posição invalida, insira novamente: ");
            posicao = s.nextInt();

        while(posicoesExploradas[posicao].equals("EXPLORADO")){
            System.out.print("Posição ja explorada, insira outra posição: ");

            posicao = s.nextInt();
        }
            posicoesExploradas[posicao] = "EXPLORADO";
    }

    }
    
    private void posicaoJaExplorada() {

    }
    private void verificarJogada() {

    }
    private void calcularPontuacao() {

    }
    private void contarTesourosNaIlha() {

    }
    private void verificarFimDeJogo() {

    }
    private void perguntarSeDesejaReiniciar() {

    }
    private void reiniciarJogo() {

    }
    private void mostrarResultadoFinal() {

    }

    

    
    
}