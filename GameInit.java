import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dor Sror
 * The GameInit Class - holds a main method for running the game.
 */
public class GameInit {
    /**
     * The main method of GameInit.
     * Creates a game instance and sets the levels order according to args.
     * @param args The arguments sent to the class - if there are no arguments,
     *             the game initializes a list with all levels in order.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        Sleeper sleeper = new Sleeper();
        AnimationRunner runner = new AnimationRunner(gui, 60, sleeper);
        List<LevelInformation> levelInfoList = new ArrayList<>();
        if (args.length == 0 || (args.length == 1 && args[0].equals("${args}"))) {
            levelInfoList.add(new DirectHit());
            levelInfoList.add(new WideEasy());
            levelInfoList.add(new Green3());
            levelInfoList.add(new FinalFour());
        }
        for (String arg : args) {
            if (isInteger(arg)) {
                switch (Integer.parseInt(arg)) {
                    case 1:
                        levelInfoList.add(new DirectHit());
                        break;
                    case 2:
                        levelInfoList.add(new WideEasy());
                        break;
                    case 3:
                        levelInfoList.add(new Green3());
                        break;
                    case 4:
                        levelInfoList.add(new FinalFour());
                        break;
                    default:
                        break;
                }
            }
        }
        GameFlow gameFlow = new GameFlow(runner, ks, gui);
        gameFlow.runLevels(levelInfoList);
        gui.close();
    }

    /**
     * Determines if a string is an integer or not by trying to convert it to an integer.
     * @param s the string we want to check.
     * @return true if succeded in converting the string to an integer, false otherwise.
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
