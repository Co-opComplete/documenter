package projectRoot.main.java.com.scuilion;

import java.io.*;
import java.util.*;

import com.scuilion.documenter.*;

@Document(key="class.decleration", priority=100)
class ProcessorTestClass{
    
    static int dumb;

    static {
        dumb = 0;
    }

    @Document(key="this.constructor", priority=200)
    public ProcessorTestClass(@Document(key="constructor.parameter", priority=350)int parm1){
    }

    @Document(key="instance.variable", priority=300)
    String really = "foo";
    
    @Document(key="some.method", priority=400) 
    public Integer someMethod(){

        @Document(key="local.variable.one", priority=-1)
        Double foo = 3.0;

        @Document(key="local.variable.two", priority=-2)
        Integer bar = 2;

        return bar + Integer.valueOf(foo.toString());
    }

    @Document(key="another.method", priority=500) 
    public float anotherMethod(){
        try (@Document(key="resource.variable", priority=-3)PhonyResource r = new PhonyResource()) {

        } catch (@Document(key="exception.variable", priority=-4)IOException e) {
            e.printStackTrace();
        }
		return 0.0f;
    }
    
    public List<@Document(key="type.parameter", priority=800)String> withParameter(@Document(key="type.use", priority=700)boolean with) {
        return new ArrayList<>();
    }

}
