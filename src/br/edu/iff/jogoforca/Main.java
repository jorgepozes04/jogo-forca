package br.edu.iff.jogoforca;

import java.util.Scanner;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.rodada.Item;
import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;

public class Main {

    public static void main(String[] args) {

        Aplicacao app = Aplicacao.getSoleInstance();
        app.configurar();

        CarregadorDeDados.carregarCSV("palavras.csv");

        JogadorFactory jogadorFactory = app.getJogadorFactory();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nomeJogador = scanner.nextLine();
        Jogador jogador = jogadorFactory.getJogador(nomeJogador);

        RodadaAppService rodadaService = RodadaAppService.getSoleInstance();

        do {
            Rodada rodada = rodadaService.novaRodada(jogador.getId());

            System.out.println("\n========== NOVA RODADA ==========");

            while (!rodada.encerrou()) {

                System.out.println("Tema: " + rodada.getTema().getNome());

                exibirBoneco(rodada.getQtdeErros());

                exibirPalavras(rodada);

                System.out.print("Letras erradas: ");
                for (Letra l : rodada.getErradas()) {
                    System.out.print(l.getCodigo() + " ");
                }
                System.out.println();
                System.out.println("Tentativas restantes: " + rodada.getQtdeTentativasRestantes());
                System.out.println("---------------------------------");
                System.out.print("Digite uma letra (ou ! para arriscar todas as palavras): ");
                String entrada = scanner.nextLine().trim().toLowerCase();

                if (entrada.equals("!")) {
                    String[] palavras = new String[rodada.getNumPalavras()];
                    for (int i = 0; i < palavras.length; i++) {
                        System.out.print("Digite a palavra " + (i + 1) + ": ");
                        palavras[i] = scanner.nextLine().trim();
                    }
                    rodada.arriscar(palavras);
                } else if (entrada.length() == 1) {
                    rodada.tentar(entrada.charAt(0));
                } else {
                    System.out.println("Entrada invalida! Digite apenas uma letra ou '!'");
                }
            }

            System.out.println("\n========== FIM DA RODADA ==========");
            exibirBoneco(rodada.getQtdeErros());
            exibirPalavras(rodada);

            if (rodada.descobriu()) {
                System.out.println("Parabens! Voce descobriu todas as palavras!");
            } else {
                System.out.println("Que pena! Voce nao descobriu as palavras.");
                System.out.print("As palavras eram: ");
                for (Palavra p : rodada.getPalavras()) {
                    System.out.print(p.toString() + " ");
                }
                System.out.println();
            }

            System.out.println("Pontos desta rodada: " + rodada.calcularPontos());
            System.out.println("Pontuacao total: " + jogador.getPontuacao());

            rodadaService.salvarRodada(rodada);

            System.out.print("\nJogar novamente? (s/n): ");

        } while (scanner.nextLine().trim().equalsIgnoreCase("s"));

        System.out.println("\nObrigado por jogar, " + jogador.getNome() + "!");
        System.out.println("Pontuacao final: " + jogador.getPontuacao());
        scanner.close();
    }

    private static void exibirPalavras(Rodada rodada) {
        Item[] itens = rodada.getItens();
        System.out.println();
        for (int i = 0; i < itens.length; i++) {
            System.out.print("Palavra " + (i + 1) + ": [ ");
            for (int j = 0; j < itens[i].getPalavra().getTamanho(); j++) {
                Letra letraNaPosicao = itens[i].getPalavra().getLetra(j);
                boolean descoberta = false;
                for (Letra l : itens[i].getLetrasDescobertas()) {
                    if (l.getCodigo() == letraNaPosicao.getCodigo()) {
                        descoberta = true;
                        break;
                    }
                }
                if (descoberta) {
                    System.out.print(letraNaPosicao.getCodigo() + " ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println("]");
        }
    }

    private static void exibirBoneco(int erros) {
        System.out.println();
        System.out.println("  +---+");
        System.out.println("  |   |");
        System.out.println("  |   " + (erros >= 1 ? "O" : " "));
        System.out.println("  |  " + (erros >= 7 ? "/" : " ") + (erros >= 6 ? "|" : " ") + (erros >= 8 ? "\\" : " "));
        System.out.println("  |  " + (erros >= 9 ? "/" : " ") + " " + (erros >= 10 ? "\\" : " "));
        System.out.println("  |");
        System.out.println("=====");
        System.out.println("Erros: " + erros + "/10");
    }
}