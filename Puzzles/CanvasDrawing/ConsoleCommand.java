import java.util.Scanner;

/**
 * Main class to execute this command line based program
 * @author th.nitendra@gmail.com
 */
public class ConsoleCommand {
    private ConsoleGraphicsManager gm = new ConsoleGraphicsManager();

    public void executeC(String[] args) {
        if (args.length == 3) {
            try {
                gm.resizeCanvas(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                gm.draw();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("#arguements(" + args.length +") != 3.");
        }
    }

    public void executeL(String[] args) {
        if (args.length == 5) {
            Point p1 = Point.getInstance(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            Point p2 = Point.getInstance(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            try {
                IShape line = gm.getLine(p1, p2);
                gm.addToCanvas(line).draw();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("#arguements(" + args.length +") != 5.");
        }
    }

    public void executeR(String[] args) {
        if (args.length == 5) {
            Point p1 = Point.getInstance(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            Point p2 = Point.getInstance(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
            try {
                IShape rectangle = gm.getRectangle(p1, p2);
                gm.addToCanvas(rectangle).draw();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("#arguements(" + args.length +") != 5.");
        }
    }

    public void executeB(String[] args) {
        if (args.length == 4) {
            try {
                gm.fillConnectedAreas(Point.getInstance(Integer.parseInt(args[1]), Integer.parseInt(args[2])), args[3]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("#arguements(" + args.length +") != 3.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Valid commands are: C, L, R, B. Enter Q to quit.");
        ConsoleCommand cmd = new ConsoleCommand();
        String argsLine = "";
        Scanner sc = new Scanner(System.in);
        do {
            argsLine = sc.nextLine();
            args = argsLine.split(" ");
            //args = new String[] {"R", "1", "1", "4", "4"};
            if (args.length == 0) {
                System.out.println("Input a command to start drawing. Valid commands are: C, L, R, B. Enter Q to quit.");
            }
            switch (args[0]) {
                case "C":
                    cmd.executeC(args);
                    break;
                case "L":
                    cmd.executeL(args);
                    break;
                case "R":
                    cmd.executeR(args);
                    break;
                case "B":
                    cmd.executeB(args);
                    break;
                case "Q": break;
                default:
                    System.out.println("Input Error! Unknown Command Type " + args[0] + ". Valid commands are: C, L, R, B");
            }
        } while(!"Q".equalsIgnoreCase(args[0]));
    }

}
