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

    @Document(key="this.constructor", priority=200) //yes
    public ProcessorTestClass(@Document(key="constructor.parameter", priority=350)int param1){//no
    }

    @Document(key="instance.variable", priority=300)//yes
    String really = "foo";
    
    @Document(key="some.method", priority=400)  //yes
    public Integer someMethod(){

        @Document(key="local.variable.one", priority=-1) //of course not
        Double foo = 3.0;

        @Document(key="local.variable.two", priority=-2) //of course not
        Integer bar = 2;

        return bar + Integer.valueOf(foo.toString());
    }

    @Document(key="another.method", priority=500) //yes
    public float anotherMethod(){
        try (@Document(key="resource.variable", priority=-3)PhonyResource r = new PhonyResource()) {//no

        } catch (@Document(key="exception.variable", priority=-4)IOException e) {//no
            e.printStackTrace();
        }
		return 0.0f;
    }
    
    //no  and  yes
    public List<@Document(key="type.parameter", priority=800)String> withParameter(@Document(key="type.use", priority=700)boolean with) {
        return new ArrayList<>();
    }

}
