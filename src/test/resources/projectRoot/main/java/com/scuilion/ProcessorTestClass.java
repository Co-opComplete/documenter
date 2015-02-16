package projectRoot.main.java.com.scuilion;

import java.util.ArrayList;
import java.util.List;

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
        Double foo = 3.0;

        @Document(key="field", priority=-2)
        Integer bar = 2;

        return bar + Integer.valueOf(foo.toString());
    }

    @Document(key="anotherMethod", priority=500) 
    public float anotherMethod(){
		return 0.0f;
    }
    
    public List<String> withParameter(@Document(key="type use", priority=700)boolean with) {
        return new ArrayList<>();
    }

}
