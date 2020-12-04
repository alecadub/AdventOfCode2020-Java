package days;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day01 extends Day {

    private final int sum = 2020;
    private int firstNbr = 0;
    private int secondNbr = 0;
    private int thirdNbr = 0;
    private String[] lines;

    @Override
    public void executeChallenge(executeChallengeSpecifics function) {
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
            for (int i = 0; i < this.lines.length; i++) {
                this.firstNbr = Integer.parseInt(this.lines[i]);
                for (int j = i + 1; j < this.lines.length; j++) {
                    this.secondNbr = Integer.parseInt(this.lines[j]);

                    if (this.firstNbr + this.secondNbr == this.sum) {
                        setFirstResult(String.valueOf(this.firstNbr * this.secondNbr));
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void challenge02() {
        this.executeChallenge(() -> {
            for (int i = 0; i < this.lines.length; i++) {
                firstNbr = Integer.parseInt(this.lines[i]);
                for (int j = i + 1; j < this.lines.length; j++) {
                    secondNbr = Integer.parseInt(this.lines[j]);

                    for (int k = j + 1; k < this.lines.length; k++) {
                        thirdNbr = Integer.parseInt(this.lines[k]);

                        if (firstNbr + secondNbr + thirdNbr == this.sum) {
                            setSecondResult(String.valueOf(firstNbr * secondNbr * thirdNbr));
                            return;
                        }
                    }
                }
            }
        });
    }
}
