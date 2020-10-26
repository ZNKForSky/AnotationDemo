package com.luffy.compiler;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes("com.luffy.anotationdemo.ZnkAnnotation")
public class LuffyProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //注解处理器的日志要借助 AbstractProcessor中的 processingEnv打印
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "=====================我的注解======================");
        messager.printMessage(Diagnostic.Kind.NOTE, "=====================你的注解======================");
        return false;
    }
}
