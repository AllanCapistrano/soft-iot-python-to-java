package python.to.java.models;

import python.to.java.services.IService;

public class Service implements IService {
    
    @Override
    public String hello(String name) {
        return "Hello"+ name +"!";
    }
    
}