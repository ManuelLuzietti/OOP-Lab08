package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    private String currentString;
    private List<String> history;
    
     public ControllerImpl() {
        history = new ArrayList<>();
    }
    @Override
    public void setString(final String newString) throws NullPointerException {
        if (newString == null) {
            throw new NullPointerException();
        }
        this.currentString = newString;
    }

    @Override
    public String getString() {
        return this.currentString;
    }

    @Override
    public List<String> getHistory() {
        return List.copyOf(history);
    }

    @Override
    public  void print() throws IllegalArgumentException {
        if (this.currentString == null) {
            throw new IllegalArgumentException();
        }
        System.out.println(this.currentString);
        history.add(this.currentString);
    }

}
