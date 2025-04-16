package edu.postech.csed409h.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterTest {
    static class test extends FileWriter{
        Writer createBufferedWriter() throws IOException {
            throw new IOException();
        }
    }
    @Test
    void write() {
        test t = new test();
        t.write();
        assert t.error;
    }
}