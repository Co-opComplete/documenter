package com.scuilion.exampleproject;

import com.scuilion.documenter.Document;

@Document()
class SomeClass{
    
    @Document()
    public void SomeClass(){
    }

    @Document 
    String really = "foo";
    
    @Document(priority=100) 
    public Integer someMethod(){

        @Document 
        Integer foo = 3;

        @Document 
        Integer bar = 2;

        return foo + bar;
    }

    @Document() 
    public Integer anotherMethod(){
        return 5;
    }

}
