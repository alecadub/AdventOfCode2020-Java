package days;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day01 extends Day {

    private final int sum = 2020;
    private int firstNbr = 0;
    private int secondNbr = 0;
    private String[] lines;

    @Override
    public void executeChallenge(ExecuteChallengeSpecifics function) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/day01.txt"), StandardCharsets.UTF_8);
            String[] linesArray = new String[lines.size()];
            this.lines = lines.toArray(linesArray);

            function.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
            Map<Integer, Integer> numMap = new HashMap<>();
            for (int i = 0; i < this.lines.length; i++) {
                int line = Integer.parseInt(this.lines[i]);
                int complement = this.sum - line;
                if (numMap.containsKey(complement)) {
                    setFirstResult(String.valueOf(line * complement));
                    break;
                } else {
                    numMap.put(line, i);
                }
            }
        });
    }

    @Override
    public void challenge02() {
        this.executeChallenge(() -> {
            for (int i = 0; i < this.lines.length; i++) {
                this.firstNbr = Integer.parseInt(this.lines[i]);
                int currentTarget = this.sum - firstNbr;
                Set<Integer> existingNums = new HashSet<>();
                for (int j = i + 1; j < this.lines.length; j++) {
                    this.secondNbr = Integer.parseInt(this.lines[j]);
                    if (existingNums.contains(currentTarget - this.secondNbr)) {
                        setSecondResult(String.valueOf(this.firstNbr * this.secondNbr * (currentTarget - this.secondNbr)));
                        break;
                    } else {
                        existingNums.add(Integer.parseInt(this.lines[j]));
                    }
                }
            }
        });
    }
}
