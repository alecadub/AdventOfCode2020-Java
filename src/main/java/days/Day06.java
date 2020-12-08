package days;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day06 extends Day {

    private int sum = 0;
    private int lineCount = 0;
    private final Map<Character, int[]> charMap = new HashMap<Character, int[]>();

    @Override
    public void executeChallenge(ExecuteChallengeSpecifics function) {
        Scanner sc = null;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("src/main/resources/day06.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            assert sc != null;
            if (!sc.hasNextLine()) break;
            String line = sc.nextLine();
            char[] charLine = line.toCharArray();

            if (line.trim().isEmpty()) {
                function.run();
                this.lineCount = 0;
                this.charMap.clear();
                continue;
            }

            this.lineCount++;

            for (char c : charLine) {
                if (this.charMap.containsKey(c)) {
                    int count = this.charMap.get(c)[0];
                    int countLine = this.charMap.get(c)[1];
                    if (countLine != this.lineCount) {
                        this.charMap.put(c, new int[]{count + 1, this.lineCount});
                    }
                } else {
                    int[] countLine = {1, this.lineCount};
                    this.charMap.put(c, countLine);
                }
            }

        }
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
            this.sum += this.charMap.size();
        });
        this.sum += this.charMap.size();
        setFirstResult(String.valueOf(this.sum));
    }

    @Override
    public void challenge02() {
        this.sum = 0;
        this.charMap.clear();
        this.lineCount = 0;
        this.executeChallenge(() -> {
            for (Map.Entry<Character, int[]> entry : this.charMap.entrySet()) {
                if (entry.getValue()[0] == this.lineCount) {
                    this.sum += 1;
                }
            }
        });

        for (Map.Entry<Character, int[]> entry : this.charMap.entrySet()) {
            if (entry.getValue()[0] == this.lineCount) {
                this.sum += 1;
            }
        }
        setSecondResult(String.valueOf(this.sum));
    }
}
