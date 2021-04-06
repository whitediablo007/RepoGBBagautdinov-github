package geekbrains.study.participant;

import geekbrains.study.skills.Bouncy;
import geekbrains.study.skills.Runnable;

public class Cat implements Runnable, Bouncy {
    private String name;
    private int runDistance;
    private int jumpHeight;

    public Cat(String name, int runDistance, int jumpHeight) {
        this.name = name;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public boolean jump(int height) {
        System.out.println("Кот " + name + " оценивает возможности прыжка.");
        if (height <= jumpHeight) {
            System.out.println("Кот " + name + " перепрыгнул стену " + height);

            return true;
        } else {
            System.out.println(name + " не может прыгать на высоту " + height + " метра(ов)!");

            return false;
        }
    }

    @Override
    public boolean run(int distance) {
        System.out.println("Кот " + name + " оценил возможность бега");
        if (distance <= runDistance) {
            System.out.println("Кот " + name + " пробежал " + distance);

            return true;
        } else {
            System.out.println(name + " не может бегать на расстояние " + distance + " метра (ов)!");

            return false;
        }
    }
}
