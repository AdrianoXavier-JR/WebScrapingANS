# Web Scraping e Compactação de Arquivos

Este projeto foi desenvolvido como parte de um teste para uma vaga de estágio, onde o objetivo era realizar um processo de web scraping para baixar arquivos PDF (Anexo I e Anexo II) de uma página governamental e compactá-los em um arquivo ZIP.

## Objetivo

- **Acessar o site**: [Acesso à Informação - ANS](https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos)
- **Baixar os arquivos PDF**: Anexo I e Anexo II.
- **Compactar os arquivos em um único arquivo ZIP**.

## Tecnologias Utilizadas

*   **Java**: A principal linguagem utilizada para o desenvolvimento do código.
*   **JSoup**: Para fazer o scraping e download do PDF.
  
## Funcionalidades

1. **Acesso ao site**: O código acessa o site indicado e recupera o HTML da página.
2. **Busca por links de arquivos**: Identifica links para os arquivos PDF (Anexo I e Anexo II) na página.
3. **Download dos arquivos**: Baixa os arquivos PDF para o diretório de downloads do usuário.
4. **Compactação dos arquivos**: Após o download, os arquivos são compactados em um arquivo ZIP.

### Requisitos

- **Java 11 ou superior** instalado.

**Como Usar**
-------------

### **Passo 1: Pré-requisitos**

Antes de executar o código, verifique se você tem o Java instalado no seu computador. Você pode baixar a versão mais recente do [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

### **Passo 2: Download do Código**

Clone ou baixe o código do projeto para o seu ambiente local.

Estrutura do Projeto
--------------------

*   **WebScraping.java**: Classe principal que executa o processo de scraping e chama as outras classes.
    
*   **FileDownloader.java**: Classe responsável por baixar os arquivos PDF.
    
*   **FileCompressor.java**: Classe responsável por compactar os arquivos PDF em um arquivo ZIP.
