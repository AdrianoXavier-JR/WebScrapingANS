package org.example;

import org.example.utils.FileDownloader;

public class WebScraping {
    public static void main(String[] args) {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        FileDownloader.downloadFiles(url);
    }
}
