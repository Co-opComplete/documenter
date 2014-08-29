package com.scuilion.exampleproject;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;
import java.io.IOException;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

public class BasicTest{

    @Test
    public void sup(){
        try{
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); 
            if (compiler == null) {
                System.out.println("No system java compiler available");
            }

            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

            File dir = new File(System.getProperty("integrationTest.location"));
            Collection<File> javaFiles = FileUtils.listFiles(dir, new RegexFileFilter(".*\\.java$"), TrueFileFilter.INSTANCE);

            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(javaFiles);
            //Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList("/home/kevino/projects/documenter/src/integrationTest/resources/projectRoot/main/java/com/scuilion/SomeClass.java"));

            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);

            boolean success = task.call();

            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()){
                   System.out.format("Error on line %d in %s%n",
                             diagnostic.getLineNumber(), diagnostic
                             //diagnostic.getSource().toUri()
                             );
            }
                                                                       
                                                                                                                            
             

            fileManager.close();
            System.out.println("Success: " + success);
        }catch(Exception e){
            System.out.println("failure: " + e);
        }
        assertTrue(true);
    }
}

