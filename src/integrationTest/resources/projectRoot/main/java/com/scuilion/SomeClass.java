package com.scuilion.exampleproject;

import com.scuilion.documenter.Document;

@Document()
class SomeClass{
    
    @Document()
    public void SomeClass(){
    }

    @Document() 
    public String someMethod(){
        @Document() 
        String foo = "foo";
        @Document() 
        Integer bar = 2;
        return "sup";
    }
}
