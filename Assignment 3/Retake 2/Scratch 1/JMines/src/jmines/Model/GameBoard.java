package jmines.Model;

import java.util.Collection;
import java.util.Map;

/**
 * The class used to do all the business calculations.
 *
 * @author Zleurtor
 */

public class GameBoard {

    //==========================================================================
    // Static attributes
    //==========================================================================
    /**
     * All the supported shapes identifiers.
     */
    public final Collection<Byte> SUPPORTED_SHAPES;
    /**
     * The filling ratio for each button shape that doesn't have a filling
     * ratio equals to 1.
     */
    private final Map<Byte, Float> FILLING_RATIO;

    private GameBoardShape gameBoardShape;

    public GameBoard(GameBoardShape gameBoardShape) {
        this.gameBoardShape = gameBoardShape;
    }
}