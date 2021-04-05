package geekbrains.study.participant;

import geekbrains.study.skills.Bouncy;
import geekbrains.study.skills.Runnable;

public class Robot implements Runnable, Bouncy {
    private String name;
    private int runDistance;
    private int jumpHeight;

    public Robot(String name, int runDistance, int jumpHeight) {
        this.name = name;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public boolean jump(int height) {
        System.out.println("Робот " + name + " оценивает возможности прыжка.");
        if (height <= jumpHeight) {
            System.out.println("Робот " + name + " перепрыгнул стену " + height);

            return true;
        } else {
            System.out.println(name + " не может прыгать на высоту " + height + " метра(ов)!");

            return false;
        }
    }

    @Override
    public int getHeightOfJump() {
        return jumpHeight;
    }

    @Override
    public boolean run(int distance) {
        System.out.println("Робот " + name + " оценил возможность бега");
        if (distance <= runDistance) {
            System.out.println("Робот " + name + " пробежал " + distance);

            return true;
        } else {
            System.out.println(name + " не может бегать на расстояние " + distance + " метра (ов)!");

            return false;
        }
    }

    @Override
    public int getDistanceOfRun() {
        return runDistance;
    }
}
