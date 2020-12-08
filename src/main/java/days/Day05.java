package days;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day05 extends Day {
    private int highestId = 0;
    private ArrayList<Integer> ids = new ArrayList<Integer>();

    @Override
    public void executeChallenge(ExecuteChallengeSpecifics function) {

        Scanner sc = null;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("src/main/resources/day05.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while (true) {
            int lowerHalf = 0;
            int upperHalf = 127;
            int row = 0;
            int col = 0;

            assert sc != null;
            if (!sc.hasNextLine()) break;

            String line = sc.nextLine();
            char[] rowInstructions = line.substring(0, 7).toCharArray();
            char[] columnInstructions = line.substring(7).toCharArray();

            loop:
            for (char c : rowInstructions) {
                int difference = upperHalf - lowerHalf;
                switch (c) {
                    case 'F':
                        if (difference == 1) {
                            row = lowerHalf;
                            break loop;
                        }
                        upperHalf = upperHalf - (difference / 2) - 1;
                        break;
                    case 'B':
                        if (difference == 1) {
                            row = upperHalf;
                            break loop;
                        }
                        lowerHalf = lowerHalf + (difference / 2) + 1;
                        break;
                    default:
                        break loop;
                }
            }

            upperHalf = 7;
            lowerHalf = 0;

            loop:
            for (char c : columnInstructions) {
                int difference = upperHalf - lowerHalf;
                switch (c) {
                    case 'L':
                        if (difference == 1) {
                            col = lowerHalf;
                            break loop;
                        }
                        upperHalf = upperHalf - (difference / 2) - 1;
                        break;
                    case 'R':
                        if (difference == 1) {
                            col = upperHalf;
                            break loop;
                        }
                        lowerHalf = lowerHalf + (difference / 2) + 1;
                        break;
                    default:
                        break loop;
                }
            }

            int result = row * 8 + col;
            ids.add(result);

            if (result > this.highestId) {
                this.highestId = result;
            }
        }

        function.run();
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
        });
        this.setFirstResult(String.valueOf(this.highestId));
    }

    @Override
    public void challenge02() {
        this.executeChallenge(() -> {
            Collections.sort(this.ids);

            for (int i = 0; i < this.ids.size(); i++) {
                int firstId = this.ids.get(i);
                int secondId = this.ids.get(i + 1);
                int difference = secondId - firstId;
                if (difference == 2) {
                    setSecondResult(String.valueOf(this.ids.get(i) + 1));
                    break;
                }
            }
        });
    }
}
