package com.scuilion.exampleproject;

import com.scuilion.documenter.Document;

@Document(priority=100)
class SomeClass{
    
    @Document(priority=200)
    public void SomeClass(){
    }

    @Document(priority=300)
    String really = "foo";
    
    @Document(priority=400) 
    public Integer someMethod(){

        @Document(priority=-1)
        Integer foo = 3;

        @Document(priority=-1)
        Integer bar = 2;

        return foo + bar;
    }

    @Document(priority=500) 
    public Integer anotherMethod(){
        return 5;
    }

}
