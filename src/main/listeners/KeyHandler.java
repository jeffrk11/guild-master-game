package main.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements  KeyListener{

    private static List<Inputable> subscribers = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        notifySubs(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        notifySubs(e.getKeyCode(), false);
    }
    
    private void notifySubs(int key,boolean pressed){
        subscribers.forEach(e -> e.input(key,pressed));
    }

    public void addSubscriber(Inputable inputable){
        subscribers.add(inputable);
    }
}
