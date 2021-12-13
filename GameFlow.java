import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * @author Dor Sror - 207271875
 * The GameFlow class - runs the game in a flow according to a list of levels.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;

    /**
     * The GameFlow constructor.
     * @param ar The animation runner of the game.
     * @param ks The keyboard sensor of the game.
     * @param gui The GUI of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
    }

    /**
     * This method runs a list of levels, and saves the score between levels.
     * Runs an EndScreen animation when the game is over (i.e. all balls fell off the screen or all levels have
     * been cleared).
     * @param levels the list of levels that compose the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        int score = 0;
        boolean hasWon = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.gui, score);
            level.initialize();
            while (level.getBlocksInt() != 0 && level.getBallsInt() != 0) {
                level.run();
            }
            score = level.getScore();
            if (level.getBallsInt() == 0) {
                hasWon = false;
                break;
            }
        }
        Animation endAnimation = new EndScreen(score, hasWon);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                endAnimation));
    }
}