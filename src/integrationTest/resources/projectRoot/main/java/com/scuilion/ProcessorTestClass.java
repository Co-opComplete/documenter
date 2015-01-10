package projectRoot.main.java.com.scuilion;

import com.scuilion.documenter.Document;

@Document(key="class", priority=100)
class ProcessorTestClass{

    @Document(key="constructor", priority=200)
    public ProcessorTestClass(){
    }

    @Document(key="instance variable", priority=300)
    String really = "foo";
    
    @Document(key="someMethod", priority=400) 
    public Integer someMethod(){

        @Document(key="field", priority=-1)
        Integer foo = 3;

        @Document(key="field", priority=-2)
        Integer bar = 2;

        return foo + bar;
    }

    @Document(key="anotherMethod", priority=500) 
    public Integer anotherMethod(){
        return 5;
    }
    
    public String withParameter(@Document(key="type use", priority=700)String with) {
        return with;
    }

}
