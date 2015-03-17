package projectRoot.main.java.com.scuilion;

import com.scuilion.documenter.*;

public class SingleMethod {

    @Document(key="a.single.method", priority=111)
    public void aSingleMethod(@Document(key="parameter.of.a.single.method", priority=222)int something){
    }
}
