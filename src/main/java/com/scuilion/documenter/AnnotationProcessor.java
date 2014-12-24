package com.scuilion.documenter;

import javax.annotation.processing.*;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationMirror;
       
import javax.tools.*;
import javax.lang.model.SourceVersion;
import java.util.Set;
import java.util.Map;

@SupportedAnnotationTypes({"com.scuilion.documenter.Document"})
public class AnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Scanner scanner = new Scanner();
        scanner.scan(roundEnv.getRootElements(), null);
//         
//         SdmMap sdmMap = jointScanner.getSdmMap();
//         for (Map.Entry<String, SDMType> entry : sdmMap.getSdmMap().entrySet()) {
//             System.out.print("****** ");
//             System.out.print(entry.getKey());
//             System.out.println(" ******");
//             SDMType sdmType = entry.getValue();
//             for(ImportContentType importContentType : sdmType.getImportContentTypes()){
//                 System.out.println(importContentType.toString());
//             }
//             for(ActionType actionType : sdmType.getActions()){
//                 System.out.println(actionType.toString());
//             }
//             for(ParameterType parameterType : sdmType.getParamaterTypes()){
//                 System.out.println(parameterType.toString());
//             }
//         }
// 
        System.out.println("in process");
        return true;
//         ---
// 
//         Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Document.class);
//         for (Element te : elements) {
//             for(AnnotationMirror annotationMirror : te.getAnnotationMirrors()){
//                 System.out.println("--------");
//                 for (Map.Entry<? extends ExecutableElement,? extends AnnotationValue> entry : annotationMirror.getElementValues().entrySet()) {
//                     ExecutableElement key = entry.getKey();
//                     AnnotationValue value = entry.getValue();
//                     System.out.println(key);
//                     System.out.println(value);
//                 }
//                 System.out.println("--------");
//             }
//             System.out.println(te.getClass());
//             System.out.println(te.getSimpleName());
//             System.out.println("");
//         }
// 
//         return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.latestSupported();
    }
}
