package geekbrains.study.participant;

import geekbrains.study.skills.Bouncy;
import geekbrains.study.skills.Runnable;

public class Human implements Runnable, Bouncy {
    private String name;
    private int runDistance;
    private int jumpHeight;

    public Human(String name, int runDistance, int jumpHeight) {
        this.name = name;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public boolean jump(int height) {
        System.out.println("Человек " + name + " оценивает возможности прыжка.");
        if (height <= jumpHeight) {
            System.out.println("Человек " + name + " перепрыгнул стену " + height + " метра(ов)!");

            return true;
        } else {
            System.out.println(name + " не может прыгать на высоту " + height + " метра(ов)!");

            return false;
        }
    }

    @Override
    public boolean run(int distance) {
        System.out.println("Человек " + name + " оценил возможность бега");
        if (distance <= runDistance) {
            System.out.println("Человек " + name + " пробежал " + distance + " метра(ов)!");

            return true;
        } else {
            System.out.println(name + " не может бегать на расстояние " + distance + " метра(ов)!");

            return false;
        }
    }
}
