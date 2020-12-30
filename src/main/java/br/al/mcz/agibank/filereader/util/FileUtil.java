package br.al.mcz.agibank.filereader.util;

import br.al.mcz.agibank.filereader.exceptions.ArquivoNaoEncontradoException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            createOutputFilePath(path);
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

    private static Path createOutputFilePath(String outputFile) throws IOException {
        Path outputFilePath = Paths.get(outputFile);

        criarDiretorioPublico(outputFilePath);

        if (!Files.exists(outputFilePath)) {
            Files.createFile(outputFilePath);
        }
        return outputFilePath;
    }

    private static void criarDiretorioPublico(Path outputFilePath) throws IOException {
        Files.createDirectories(outputFilePath.getParent());
        setPublicPermission(outputFilePath.getParent());
    }

    private static void setPublicPermission(Path path) throws IOException {
        Set<PosixFilePermission> perms = new HashSet<>();

        //add owners permission
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        //add group permissions
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        //add others permissions
        perms.add(PosixFilePermission.OTHERS_READ);
        perms.add(PosixFilePermission.OTHERS_WRITE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);

        Files.setPosixFilePermissions(path, perms);
    }
}
