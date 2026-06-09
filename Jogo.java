import java.util.Scanner;

public class Jogo {
    
    public Jogo() {
        Scanner s = new Scanner(System.in);
        String[] ilha = new String[15];
        String[] posicoesExploradas = new String[ilha.length];
        int pontuacao = 0;
        int tentativas = 8;
        int tenativasRestantes = tentativas;
        int tesourosEncontrados = 0;
        int armadilhasEncontradas = 0;
        int posicoesVaziasExploradas = 0;
        int pontosMinimosParaGanhar = 10;
        iniciar();
        inicializarIlha(ilha);
        inicializarControleExplorado(posicoesExploradas);
        exibirMenuEColatarOpcao(s,ilha,posicoesExploradas, pontuacao, tentativas, tenativasRestantes, tesourosEncontrados, armadilhasEncontradas, posicoesVaziasExploradas, pontosMinimosParaGanhar);
        s.close();
    }

    public void iniciar() {
        System.out.println("Iniciou o jogo: ");
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

    private void exibirMenuEColatarOpcao(Scanner s, String[] ilha, String[] posicoesExploradas, int pontuacao, int tentativas, int tenativasRestantes, int tesourosEncontrados, int armadilhasEncontradas, int posicoesVaziasExploradas, int pontosMinimosParaGanhar) {
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

            executarOpcao(s, opcao, ilha, posicoesExploradas, pontuacao, tentativas, tenativasRestantes, tesourosEncontrados, armadilhasEncontradas, posicoesVaziasExploradas, pontosMinimosParaGanhar);

        } while (opcao != 5 && tenativasRestantes > 0);

        }
    
    private void executarOpcao(Scanner s, int opcao, String[] ilha, String[] posicoesExploradas, int pontuacao, int tentativas, int tenativasRestantes, int tesourosEncontrados, int armadilhasEncontradas, int posicoesVaziasExploradas, int pontosMinimosParaGanhar) {
        switch (opcao) {
                case 1:
                    exibirInstrucoes();
                break;
                case 2:
                    mostrarMapaExplorado(posicoesExploradas);
                break;
                case 3:
                    int posicaoAtual = lerPosicaoEValidar(s, ilha, posicoesExploradas);
                    pontuacao = pontuacao + calcularPontuacao(posicaoAtual, ilha);
                    tesourosEncontrados = tesourosEncontrados + contarTesouros(posicaoAtual, ilha);
                    armadilhasEncontradas = armadilhasEncontradas + contarArmadilhas(posicaoAtual, ilha);
                    posicoesVaziasExploradas = posicoesVaziasExploradas + contarVazios(posicaoAtual, ilha);
                    tenativasRestantes = tenativasRestantes -1;
                    if (tenativasRestantes == 0) {
                        mostrarResultado(pontuacao, tesourosEncontrados, armadilhasEncontradas, posicoesVaziasExploradas, pontosMinimosParaGanhar);
                        boolean continuar = perguntarSeDesejaReiniciar(s);
                        if (continuar == true) {
                            pontuacao = 0;
                            tenativasRestantes = tentativas;
                            tesourosEncontrados = 0;
                            armadilhasEncontradas = 0;
                            posicoesVaziasExploradas = 0;
                            inicializarIlha(ilha);
                            inicializarControleExplorado(posicoesExploradas);
                        }
                    }
                break;
                case 4:
                    mostrarStatus(pontuacao, tenativasRestantes, tesourosEncontrados, armadilhasEncontradas, posicoesVaziasExploradas);
                break;
                case 5:
                    mostrarResultado(pontuacao, tesourosEncontrados, armadilhasEncontradas, posicoesVaziasExploradas, pontosMinimosParaGanhar);
                    System.out.println("Jogo Encerrado.");
                break;
                default:
                    System.out.print("Opção inválida !!!");
            }
    }
    private void exibirInstrucoes() {

        System.out.println("\n --- INSTRUÇÕES ---");
                    System.out.println("1. Como escolher uma posição: \n\r No menu, escolher a opção 3, na sequência informar uma posição valida de 0 até 14.\n\r Feito isso você verá se encontrou um tesouro ou armadilha e sua pontuação será modificada\n\r");
                    System.out.println("2. Quais são os tipos de tesouros? ");
                    System.out.println(" A Ilha dos Tesouros possui muitas riquezas e tesouros vastos. Nela você encontrará relíquias como:" +
                                        "\n\r DIAMANTE = +20 pontos;\n\r RUBI = +15 pontos; ou \n\r OURO = +10 pontos \n\r");
                    System.out.println("3. Armadilhas: ");
                    System.out.println(" Mas, apesar de ser uma ilha com tantas belezas, ela está repleta de armadilhas!\r\n" +
                                        "No meio do caminho, você pode acabar dando de cara com: \n\r Buracos profundos = -5 pontos;\n\r Espinhos mais afiados possíveis = -7 pontos; e \n\rCobras das mais peçonhentas que existem = -10 pontos" + 
                                        " \n\rEsteja avisado, explore a ilha por sua própria conta e risco!\n\r");
                    System.out.println("4. Pontuação: ");
                    System.out.println(" Para ganhar o jogo é necessário finalizar com mais de 10 pontos");
                    System.out.println("5. O MAIS IMPORTANTE: ");
                    System.out.println(" O jogador possui 8 tentativas. Boa sorte!");
    }
    

    private void mostrarMapaExplorado(String[] posicoesExploradas) {
        for (int i = 0; i < posicoesExploradas.length; i++) {
            System.out.println("[" + i + "] " + posicoesExploradas[i]);
        }

    }
    
    private int lerPosicaoEValidar(Scanner s, String[] ilha, String[] posicoesExploradas) {
        
        System.out.println("Digite a posição a ser explorada: ");

        int posicao = s.nextInt();
        
        while(posicao < 0 || posicao >= ilha.length) {
            System.out.print("Posição invalida, insira novamente um valor de 0 a 14: ");
            posicao = s.nextInt();

            while(posicoesExploradas[posicao].equals("EXPLORADO")) {
                System.out.print("Posição ja explorada, insira outra posição: ");
                posicao = s.nextInt();
            }
        }

            posicoesExploradas[posicao] = "EXPLORADO";

            return posicao;
    }

    private int calcularPontuacao(int posicaoAtual, String[] ilha) {

        int pontuacaoAtual = 0;

        if (ilha[posicaoAtual].equals("DIAMANTE")) {
            pontuacaoAtual = 20;
        }
        else if (ilha[posicaoAtual].equals("RUBI")) {
            pontuacaoAtual = 15;
        }
        else if (ilha[posicaoAtual].equals("OURO")) {
            pontuacaoAtual = 10;
        }
        else if (ilha[posicaoAtual].equals("BURACO")) {
            pontuacaoAtual = -5;
        }
        else if (ilha[posicaoAtual].equals("ESPINHOS")) {
            pontuacaoAtual = -7;
        }
        else if (ilha[posicaoAtual].equals("COBRA")) {
            pontuacaoAtual = -10;
        }
        else {
            pontuacaoAtual = 0;
        }
        
        return pontuacaoAtual;
    }

    
    private int contarTesouros(int posicaoAtual, String[] ilha) {

        int tesouro = 0;

        if (ilha[posicaoAtual].equals("DIAMANTE") || ilha[posicaoAtual].equals("RUBI") ||  ilha[posicaoAtual].equals("OURO")){
            tesouro++;
        }
        return tesouro;

    }

    private int contarArmadilhas(int posicaoAtual, String[] ilha) {

        int armadilha = 0;

        if (ilha[posicaoAtual].equals("BURACO") || ilha[posicaoAtual].equals("ESPINHOS") ||  ilha[posicaoAtual].equals("COBRA")){
            armadilha++;
        }
        return armadilha;

    }

    private int contarVazios(int posicaoAtual, String[] ilha) {

        int vazio = 0;

        if (ilha[posicaoAtual].equals("VAZIO")){
            vazio++;
        }
        return vazio;

    }

    private boolean perguntarSeDesejaReiniciar(Scanner s) {
        
        System.out.println("Deseja jogar novamente? (S/N): ");
        String continuar = s.next();

        while (!continuar.equalsIgnoreCase("s") && !continuar.equalsIgnoreCase("n")) {
            System.out.print("Resposta inválida ! Informe uma resposta correta: ");
            continuar = s.next();
        }

        if (continuar.equalsIgnoreCase("s")) {
            return true;
        }

        else {
            return false;
        }
    }


    private void mostrarStatus(int pontuacao, int tenativasRestantes, int tesourosEncontrados, int armadilhasEncontradas, int posicoesVaziasExploradas) {
        System.out.println("A situação atual do jogador é:" +
                            "\n\r Pontuação atual: " + pontuacao +
                            "\n\r Quantidade de tentativas restantes: " + tenativasRestantes +
                            "\n\r Quantidade de tesouros encontrados: " + tesourosEncontrados +
                            "\n\r Quantidade de armadilhas encontradas: " + armadilhasEncontradas +
                            "\n\r Quantidade de posições vazias exploradas: " + posicoesVaziasExploradas);

    }


    private void mostrarResultado(int pontuacao, int tesourosEncontrados, int armadilhasEncontradas, int posicoesVaziasExploradas, int pontosMinimosParaGanhar) {
        System.out.println("Fim de jogo!\n\r");
        System.out.println(" Tesouros encontrados: " + tesourosEncontrados +
                            "\n\r Armadilhas encontradas: " + armadilhasEncontradas +
                            "\n\r Posições vazias exploradas: " + posicoesVaziasExploradas +
                            "\n\r Pontuação Final: " + pontuacao);
        if (pontuacao >= pontosMinimosParaGanhar) {
            System.out.println("Você Ganhou !!! PARABÉNS !!! ");
        }
        else {
            System.out.println("Você perdeu. Tente novamente!");
        }

    
    }

    

    
    
}