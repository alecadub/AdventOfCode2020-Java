package days;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Day03 extends Day {
    private final int row = 323;
    private final int column = 31;
    char[][] slopeArray = new char[this.row][this.column];
    private int rowJump = 0;
    private int columnJump = 0;
    private int numberOfTrees = 0;

    @Override
    public void executeChallenge(ExecuteChallengeSpecifics function) {
        function.run();

        Scanner sc = null;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("src/main/resources/day03.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            assert sc != null;
            if (!sc.hasNextLine()) break;
            for (int i = 0; i < this.slopeArray.length; i++) {
                char[] line = sc.nextLine().toCharArray();
                for (int j = 0; j < line.length; j++) {
                    this.slopeArray[i][j] = line[j];
                }
            }
        }

        int columnIndex = 0;
        int rowIndex = 0;

        for (int i = 0; i < this.row - 1; i++) {
            columnIndex += this.columnJump;
            rowIndex += this.rowJump;

            if (columnIndex >= this.column) {
                columnIndex = columnIndex - this.column;
            }

            if (rowIndex > this.row) {
                break;
            }

            char currentChar = this.slopeArray[rowIndex][columnIndex];
            if (currentChar == '#') {
                this.numberOfTrees++;
            }
        }
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
            this.columnJump = 3;
            this.rowJump = 1;
        });

        setFirstResult(String.valueOf(this.numberOfTrees));
    }

    @Override
    public void challenge02() {
        this.executeChallenge(() -> {
            this.numberOfTrees = 0;
            this.columnJump = 1;
            this.rowJump = 1;
        });

        int first = this.numberOfTrees;

        this.executeChallenge(() -> {
            this.numberOfTrees = 0;
            this.columnJump = 3;
            this.rowJump = 1;
        });

        int second = this.numberOfTrees;

        this.executeChallenge(() -> {
            this.numberOfTrees = 0;
            this.columnJump = 5;
            this.rowJump = 1;
        });

        int third = this.numberOfTrees;

        this.executeChallenge(() -> {
            this.numberOfTrees = 0;
            this.columnJump = 7;
            this.rowJump = 1;
        });

        int fourth = this.numberOfTrees;

        this.executeChallenge(() -> {
            this.numberOfTrees = 0;
            this.columnJump = 1;
            this.rowJump = 2;
        });

        int fifth = this.numberOfTrees;

        this.setSecondResult(String.valueOf(first * second * third * fourth * fifth));
    }
}
