package com.scuilion.exampleproject;

import com.scuilion.documenter.Documenter;

@Document("SOME_CLASS")
class SomeClass{
    
    @Document("SOME_CONSTRUCTOR")
    public void SomeClass(){
    }

    @Document("SOME_METHOD") 
    public String someMethod(){
        @Document("FOO") 
        String foo = "foo";
        @Document("BAR") 
        Integer bar = 2;
    }
}
