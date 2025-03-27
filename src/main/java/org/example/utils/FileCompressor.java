package org.example.utils;

import java.io.*;
import java.util.zip.*;

public class FileCompressor {

    public static void compressFilesToZip(String zipFilePath, String[] pdfFiles) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            for (String filePath : pdfFiles) {
                File fileToCompress = new File(filePath);

                if (fileToCompress.exists() && fileToCompress.isFile()) {
                    try (FileInputStream fis = new FileInputStream(fileToCompress)) {
                        ZipEntry zipEntry = new ZipEntry(fileToCompress.getName());
                        zipOut.putNextEntry(zipEntry);

                        byte[] bytes = new byte[1024];
                        int length;
                        while ((length = fis.read(bytes)) >= 0) {
                            zipOut.write(bytes, 0, length);
                        }
                    }
                } else {
                    System.out.println("Arquivo não encontrado: " + filePath);
                }
            }
            zipOut.closeEntry();
            System.out.println("Arquivos PDFs compactados com sucesso:  " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
