package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFolder {
    private ZipFolder(){
        throw new IllegalStateException("Utility class");
    }
    /**
     * Create a zip file from a directory
     *
     * @param sourceDirPath directory you want to compress
     * @param zipFilePath output path
     * @throws IOException
     */

    public static void pack(String sourceDirPath, String zipFilePath) throws IOException {
        Path p = Files.createFile(Paths.get(zipFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
              .filter(path -> !Files.isDirectory(path))
              .forEach(path -> {
                  ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                  try {
                      zs.putNextEntry(zipEntry);
                      Files.copy(path, zs);
                      zs.closeEntry();
                } catch (IOException e) {
                    System.err.println("Error: ZipFolder " + e);
                }
              });
        } catch (Exception e2) { 
            System.out.println("Error: ZipFolder2 " + e2);
        }
    }
}
