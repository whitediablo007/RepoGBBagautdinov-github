package geekbrains.study;

import geekbrains.study.obstacles.Track;
import geekbrains.study.obstacles.Wall;
import geekbrains.study.participant.Cat;
import geekbrains.study.participant.Human;
import geekbrains.study.participant.Robot;

public class Main {
    public static void main(String[] args) {

        Object[] participants = {new Human("Василий", 100, 2),
                new Cat("Барсик", 200, 3),
                new Robot("R2D2", 150, 4)};

        Track track = new Track(160);
        Wall wall = new Wall(2);

        for(Object o : participants){
            System.out.println(o);
            System.out.println(track.doRun(o));
            System.out.println(wall.doJump(o));
        }


    }

}

