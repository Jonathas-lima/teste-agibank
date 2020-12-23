package br.al.mcz.agibank.filereader.util;

import br.al.mcz.agibank.filereader.exceptions.ArquivoNaoEncontradoException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    public static List<String> readFiles(String path) throws ArquivoNaoEncontradoException {

        try (Stream<Path> paths = Files.walk(Paths.get(path)).parallel()) {

            return paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".dat"))
                    .map(FileUtil::readFilesFromPath)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

        } catch (NoSuchFileException e) {
            throw new ArquivoNaoEncontradoException();
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private static List<String> readFilesFromPath(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.toList());
        }catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public static void writeFile(String path, String titulo, String texto) throws ArquivoNaoEncontradoException {
        try {
            FileWriter fileWriter = new FileWriter(path);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("**** " + titulo + "****");
            printWriter.println(texto);
            printWriter.close();
        } catch (NoSuchFileException e){
            throw new ArquivoNaoEncontradoException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
