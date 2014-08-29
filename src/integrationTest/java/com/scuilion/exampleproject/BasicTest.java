package com.scuilion.exampleproject;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class BasicTest{

    @Test
    public void sup(){
        try{
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); 

            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList("/home/kevino/projects/documenter/src/integrationTest/resources/projectRoot/main/java/com/scuilion/SomeClass.java"));
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
            boolean success = task.call();

            for (Diagnostic diagnostic : diagnostics.getDiagnostics()){
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

