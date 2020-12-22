package br.al.mcz.agibank.filereader.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    public static List<String> readFiles() {
// TODO ALTERAR forma de pegar o arquivos
        try (Stream<Path> paths = Files.walk(Paths.get("/home/jonathas/homepath/")).parallel()) {

            return paths
                    .filter(Files::isRegularFile)
                    .map(FileUtil::readFilesFromPath)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static List<String> readFilesFromPath(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.toList());
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
