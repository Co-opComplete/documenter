package projectRoot.main.java.com.scuilion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scuilion.documenter.Document;

@Document(key="class", priority=100)
class ProcessorTestClass{
    
    int dumb;
    {
        dumb = 0;
    }

    @Document(key="constructor", priority=200)
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
        try (@Document(key="resource.variable", priority=450)PhonyResource r = new PhonyResource()) {

        } catch (@Document(key="exception.variable", priority=550)IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return 0.0f;
    }
    
    public List<@Document(key="type.parameter", priority=800)String>
        withParameter(@Document(key="type.use", priority=700)boolean with) {
        return new ArrayList<>();
    }

}
