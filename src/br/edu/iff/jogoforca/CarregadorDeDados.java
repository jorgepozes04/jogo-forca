package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CarregadorDeDados {

    public static void carregarCSV(String caminhoArquivo) {
        TemaFactory temaFactory = Aplicacao.getSoleInstance().getTemaFactory();
        PalavraAppService palavraAppService = PalavraAppService.getSoleInstance();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] colunas = linha.split(";");
                if (colunas.length == 2) {
                    String nomeTema = colunas[0].trim();
                    String nomePalavra = colunas[1].trim();

                    Tema tema = temaFactory.getTema(nomeTema);

                    try {
                        palavraAppService.novaPalavra(nomePalavra, tema.getId());
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar a palavra: " + nomePalavra);
                    }
                }
            }
            System.out.println("Carga inicial de palavras concluída com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}