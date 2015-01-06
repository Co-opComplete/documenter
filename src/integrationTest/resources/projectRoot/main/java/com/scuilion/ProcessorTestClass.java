package projectRoot.main.java.com.scuilion;

import com.scuilion.documenter.Document;

@Document(priority=100)
class ProcessorTestClass{

    @Document(priority=200)
    public ProcessorTestClass(){
    }

    @Document(priority=300)
    String really = "foo";
    
    @Document(priority=400) 
    public Integer someMethod(){

        @Document(priority=-1)
        Integer foo = 3;

        @Document(priority=-2)
        Integer bar = 2;

        return foo + bar;
    }

    @Document(priority=500) 
    public Integer anotherMethod(){
        return 5;
    }
    
    public String withParameter(@Document(priority=700)String with) {
        return with;
    }

}
