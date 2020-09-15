package tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileTools {

    private FileTools(){
        throw new IllegalStateException("Utility class");
    }
    /**
     * Delete a folder
     * WARNING: Recursive call, maybe it's not a good idea for large folder with lots of subfolder.
     * (created to clean the output folder when report is generated with sonarqube plugin).
     * @param folder
     */
    public static void deleteFolder(File folder){
        File[] files =  folder.listFiles();

        for(File toDelete: files != null ? files : new File[0]){

            if (toDelete.isDirectory()) {
                deleteFolder(toDelete);
            }
            else {
                try {
                    Files.deleteIfExists(toDelete.toPath());
                } catch (IOException e) {
                    System.out.println("FileTools error:" + e); 
                }
            }
        }
        try {
            Files.deleteIfExists(folder.toPath());
        } catch (IOException e) {
            System.out.println("FileTools error (2):" + e);
        }
    }
}
