package org.example.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileDownloader {

    public static void downloadFiles(String url) {
        try {
            System.out.println("Conectando ao site...");
            Document document = Jsoup.connect(url).get();

            File downloadDirectory = new File("downloads");
            createDirectoryIfNotExists(downloadDirectory);

            List<String> downloadedFiles = new ArrayList<>();

            System.out.println("Analisando links disponiveis...");
            for (Element link : document.select("a[href]")) {
                String linkHref = link.attr("href");
                String linkText = link.text();

                if (linkText.contains("Anexo I") || linkText.contains("Anexo II")) {
                    String fullUrl = buildFullUrl(linkHref);
                    String outputPath = buildOutputPath(linkHref);

                    if (linkHref.contains(".pdf")) {
                        try {
                            System.out.println("Efetuando download do arquivo... " + fullUrl);
                            downloadFile(fullUrl, outputPath);
                            downloadedFiles.add(outputPath);
                        } catch (Exception e) {
                            System.out.println("Erro ao fazer download do arquivo: " + fullUrl + " - Mensagem: " + e.getMessage());
                        }
                    }
                }
            }

            compressFiles(downloadedFiles);
        } catch (IOException e) {
            System.out.println("Erro ao acessar site: " + e.getMessage());
        }
    }

    private static void createDirectoryIfNotExists(File directory) {
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private static String buildFullUrl(String href) {
        String baseUrl = "https://www.gov.br";
        return href.startsWith("/") ? baseUrl + href : href;
    }

    private static String buildOutputPath(String href) {
        return System.getProperty("user.home") + "/Downloads/" + href.substring(href.lastIndexOf("/") + 1);
    }

    private static void downloadFile(String fileUrl, String outputPath) {
        try (InputStream inputStream = new URL(fileUrl).openStream();
             FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Arquivo baixado com sucesso: " + outputPath);
        } catch (IOException e) {
            System.out.println("Erro ao baixar arquivo da URL: : " + fileUrl + " - Mensagem: " + e.getMessage());
        }
    }

    private static void compressFiles(List<String> downloadedFiles) {
        System.out.println("Compactando arquivos...");
        if (!downloadedFiles.isEmpty()) {
            String zipFilePath = System.getProperty("user.home") + "/Downloads/arquivos_compactados.zip";
            FileCompressor.compressFilesToZip(zipFilePath, downloadedFiles.toArray(new String[0]));
        } else {
            System.out.println("Nenhum PDF encontrado para compactar.");
        }
        System.out.println("Processo completo!");
    }
}
