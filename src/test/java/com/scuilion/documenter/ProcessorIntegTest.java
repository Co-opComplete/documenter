package com.scuilion.documenter;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import javax.tools.*;

import org.apache.commons.io.*;
import org.apache.commons.io.filefilter.*;
import org.junit.*;

//runs annotation processor on src/test/resource/projectRoot/main/java
public class ProcessorIntegTest {

    @Test
    public void createCompilerTest() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new RuntimeException("No system java compiler available.\n Verify that you are running using the jdk, NOT the jre.");
        }
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        File dir = getFilesToCompile();
        Collection<File> javaFiles = FileUtils.listFiles(dir, new RegexFileFilter(".*\\.java$"), TrueFileFilter.INSTANCE);
//        Collection<File> javaFiles = FileUtils.listFiles(dir, new RegexFileFilter("SingleMethod.java$"), TrueFileFilter.INSTANCE);

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(javaFiles);

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, getOptions(), null, compilationUnits);

        assertTrue(task.call());

        fileManager.close();
    }

    private File getRootDir() {
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toFile();
    }

    private File getFilesToCompile() {
        return new File(getRootDir(), "src/test/resources/projectRoot");
    }

    private List<String> getOptions() throws IOException {
        List<String> options = new ArrayList<>();
        options.add("-d");
        options.add(getBuildDir());
        return options;

    }

    private String getBuildDir() {
        File buildDir = new File(getRootDir(), "build");
        if (!buildDir.exists()) {
            buildDir.mkdir();
        }
        return buildDir.getAbsolutePath().toString();
    }
}
