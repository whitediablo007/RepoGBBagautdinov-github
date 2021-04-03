package geekbrains.study.core.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public final class MatrixCoordinate {
    private final int rowIndex;
    private final int columnIndex;

}
