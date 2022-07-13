import java.util.Scanner;

public class Main {
    private static char[][] grid = new char[3][3];
    private static int intCoords1 = 0;
    private static int intCoords2 = 0;
    private static boolean stringTestPass = false;
    private static boolean numbersRangeTestPass = false;
    private static boolean occupiedTestPass = false;
    private static boolean player = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '_';
            }
        }

        printGrid();

        while (mainFunc()) {
            while (!stringTestPass || !numbersRangeTestPass || !occupiedTestPass) {
                System.out.print("Enter the coordinates: ");
                String coords1 = scanner.next();
                String coords2 = scanner.next();
                if (!(stringTest(coords1, coords2))) {
                    continue;
                } else stringTestPass = true;
                if (!(numbersRangeTest())) {
                    continue;
                } else numbersRangeTestPass = true;
                if (!(occupiedTest(grid))) {
                    //continue;
                } else occupiedTestPass = true;
            }

            stringTestPass = false;
            numbersRangeTestPass = false;
            occupiedTestPass = false;
            if (!player) {
                grid[intCoords1 - 1][intCoords2 - 1] = 'X';
                player = !player;
            } else {
                grid[intCoords1 - 1][intCoords2 - 1] = 'O';
                player = !player;
            }
            printGrid();
        }
    }

    private static void printGrid() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean stringTest(String coords1, String coords2) {
        try {
            intCoords1 = Integer.parseInt(coords1);
            intCoords2 = Integer.parseInt(coords2);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }

    }

    public static boolean numbersRangeTest() {
        if (!(intCoords1 >= 1 && intCoords1 <= 3)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (!(intCoords2 >= 1 && intCoords2 <= 3)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        return true;
    }

    public static boolean occupiedTest(char[][] chars) {
        char temp = chars[intCoords1 - 1][intCoords2 - 1];
        if (!(temp == '_')) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    public static boolean mainFunc() {
        int xs = 0; // numbers of X in input
        int os = 0; // numbers of O in input
        boolean xWin = false;       // x Wins
        boolean oWin = false;       // o Wins
        boolean draw = false;       // Draw
        boolean impossible = false; // Impossible
        boolean notFinish = false; // Game not finished

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X') {
                    xs += 1;
                } else if (grid[i][j] == 'O') {
                    os += 1;
                }
            }
        }

        char ch0 = grid[0][0];
        char ch1 = grid[0][1];
        char ch2 = grid[0][2];
        char ch3 = grid[1][0];
        char ch4 = grid[1][1];
        char ch5 = grid[1][2];
        char ch6 = grid[2][0];
        char ch7 = grid[2][1];
        char ch8 = grid[2][2];

        int i0 = ch0 + ch1 + ch2;
        int i1 = ch3 + ch4 + ch5;
        int i2 = ch6 + ch7 + ch8;
        int i3 = ch0 + ch3 + ch6;
        int i4 = ch1 + ch4 + ch7;
        int i5 = ch2 + ch5 + ch8;
        int i6 = ch0 + ch4 + ch8;
        int i7 = ch2 + ch4 + ch6;

        //'X' + 'X' + 'X'   => 264
        //'O' + 'O' + 'O'   => 237

        if (i0 == 264 || i1 == 264 || i2 == 264 || i3 == 264 || i4 == 264 || i5 == 264 || i6 == 264 || i7 == 264) {
            xWin = true;
        }
        if (i0 == 237 || i1 == 237 || i2 == 237 || i3 == 237 || i4 == 237 || i5 == 237 || i6 == 237 || i7 == 237) {
            oWin = true;
        }

        draw = xs + os == 9;
        if (xWin == oWin || Math.abs(xs - os) > 1) {
            impossible = true;
        }
        if ((ch0 == 95 || ch1 == 95 || ch2 == 95 || ch3 == 95 || ch4 == 95 || ch5 == 95 ||
                ch6 == 95 || ch7 == 95 || ch8 == 95)) {
            notFinish = true;
        }

        if (xWin && !oWin && !impossible) {
            System.out.println("X wins");
            return false;
        }
        if (oWin && !xWin && !impossible) {
            System.out.println("O wins");
            return false;
        }
        if (draw && !notFinish) {
            System.out.println("Draw");
            return false;
        }
        if (Math.abs(xs - os) > 1) {
            System.out.println("Impossible");
            return false;
        }
        if (notFinish && !xWin && !oWin) {
            //System.out.println("Game not finished");
            return true;
        }
        System.out.println("Impossible");
        return false;
    }
}