package edu.postech.csed409h.file;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter {
    public boolean error = false;
    public void write() {
        try (Writer writer = this.createBufferedWriter()) {
            writer.write("Hello World!\n");
        } catch(IOException ex) {
            System.err.println("Problem with file system: " + ex.getMessage());
            error = true;
        }
    }

    Writer createBufferedWriter() throws IOException {
        Path file = Paths.get("/tmp/hello-world.txt");
        Charset charset = Charset.forName("US-ASCII");
        return Files.newBufferedWriter(file, charset);
    }
}
