package geekbrains.study.enums;

public enum DotType {
    X, O, EMPTY;

    public static DotType getEnemyType(DotType playerType){
        if (playerType ==X) {
            return O;
        }else {
            return X;
        }

    }
}
