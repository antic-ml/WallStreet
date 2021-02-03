package russianroulette;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;

public class Run {
    private DataInputStream din;
    private Random rnd;

    private static final String TITLE =
            "\n" +
                    "\n" +
                    "   ___               _             ___            __    __  __     \n" +
                    "  / _ \\__ _____ ___ (_)__ ____    / _ \\___  __ __/ /__ / /_/ /____ \n" +
                    " / , _/ // (_-<(_-</ / _ `/ _ \\  / , _/ _ \\/ // / / -_) __/ __/ -_)\n" +
                    "/_/|_|\\_,_/___/___/_/\\_,_/_//_/ /_/|_|\\___/\\_,_/_/\\__/\\__/\\__/\\__/ \n" +
                    "                                                                   \n" +
                    "\n";


    public Run() {

        din = new DataInputStream(System.in);
        rnd = new Random();
    }

    public void printInstructions() {
        out(TITLE);
        out("\n  Written by Mario Gianota (gianotamario@gmail.com) November 2020\n\n");
        out("This is a game of >>>>>>Russian Roulette.\n");
    }

    public void loop() {
        out("\n\nPress ENTER to continue.");
        getString();

        boolean play = true;
        while( play == true ) {
            out("\nNext victim...\n");
            out("Here is a revolver.\n");
            out("Type '1' to spin chamber and pull trigger.\n");
            out("Type '2' to give up.\n\n");
            play = play();
        }
        out("\n\nOk.  Until next time.\n\n");
    }

    private boolean play() {
        boolean play = false;
        boolean dead = false;

        for(int i=1; i<=5; i++) {
            out("Spin, or give up [1, or 2] ? ");
            String s = getString();
            if ("1".equals(s)) {
                dead = spin();
                if( dead )
                    break;
            } else if ("2".equals(s)) {
                out("     Chicken!\n");
                dead = true; // Hack
                break;
            }
        }
        if( ! dead ) {
            out("You win!!! Let someone else blow their brains out.\n\n");
        }

        out("\n\nAnother game [Y:N] ? ");
        String ans = getString();
        if( "y".equalsIgnoreCase(ans))
            play = true;


        return play;
    }
    private boolean spin() {
        boolean dead = false;
        double r = rnd.nextDouble();
        if( r > .83333) {
            out("          BANG!!!        You're dead!\n");
            out("Condolences will be sent to your relatives.\n");
            dead = true;
        } else {
            out("\n\n   --- CLICK! ---\n\n");
        }
        return dead;
    }
    private void out(String s) {
        System.out.print(s);
    }

    private String getString() {
        try {
            return din.readLine();
        } catch(IOException ioe) {
            return "";
        }
    }
    public static void main(String[] args) {
        Run r = new Run();
        r.printInstructions();
        r.loop();
    }
}
