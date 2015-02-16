package com.scuilion.documenter;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.junit.Test;

public class ProcessorIntegTest {

    @Test
    public void createCompilerTest() throws IOException {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new RuntimeException("No system java compiler available.\n Verify that you are running using the jdk, NOT the jre.");
        }
        Properties props = System.getProperties();
        props.setProperty("exec.mainClass", "org.jboss.weld.environment.se.StartMain");

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

        File dir = getFilesToCompile();
        Collection<File> javaFiles = FileUtils.listFiles(dir, new RegexFileFilter(".*\\.java$"), TrueFileFilter.INSTANCE);

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
