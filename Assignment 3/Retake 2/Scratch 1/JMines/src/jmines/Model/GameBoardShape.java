package jmines.Model;

public enum GameBoardShape {
    SHAPE_UNDEFINED(-1),
    SHAPE_TRIANGULAR(3),
    SHAPE_TRIANGULAR_14(14),
    SHAPE_SQUARE(4),
    SHAPE_PENTAGONAL(5),
    SHAPE_HEXAGONAL(6),
    SHAPE_OCTOSQUARE(8),
    SHAPE_PARQUET('P'),
    SUPPORTED_SHAPES();

    private final byte identifier;

    GameBoardShape(byte identifier) {
        this.identifier = identifier;
    }


}
