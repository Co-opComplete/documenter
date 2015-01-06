package com.scuilion.documenter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@Target({
ElementType.ANNOTATION_TYPE,
ElementType.CONSTRUCTOR,
ElementType.FIELD,
ElementType.METHOD,
ElementType.PARAMETER,
ElementType.TYPE,
ElementType.LOCAL_VARIABLE,
ElementType.TYPE_PARAMETER
 })
public @interface Document{
    int priority();
}



