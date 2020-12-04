package days;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day02 extends Day {

    private int numberOfGoodPassword = 0;
    private int firstPosition;
    private int secondPosition;
    private int numberOfPasswordChar;
    private char passwordChar;
    private String[] splitLine = {"", "", ""};

    public void executeChallenge(executeChallengeSpecifics function) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/day02.txt"), StandardCharsets.UTF_8);

            for (String line : lines) {
                this.splitLine = line.split(" ");
                this.firstPosition = Integer.parseInt(this.splitLine[0].substring(0, this.splitLine[0].indexOf('-')));
                this.secondPosition = Integer.parseInt(
                        this.splitLine[0].substring(this.splitLine[0].indexOf('-') + 1, this.splitLine[0].length())
                );
                this.passwordChar = this.splitLine[1].charAt(0);
                function.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
            this.numberOfPasswordChar =
                    this.splitLine[2].length() - this.splitLine[2].replaceAll(
                            String.valueOf(passwordChar), ""
                    ).length();

            if (this.numberOfPasswordChar >= firstPosition && this.numberOfPasswordChar <= this.secondPosition) {
                this.numberOfGoodPassword++;
            }
        });
        this.setFirstResult(String.valueOf(this.numberOfGoodPassword));
    }

    @Override
    public void challenge02() {
        this.numberOfGoodPassword = 0;
        this.executeChallenge(() -> {
            if (this.splitLine[2].charAt(this.firstPosition - 1) == this.passwordChar) {
                this.numberOfPasswordChar++;
            }

            if (this.splitLine[2].charAt(this.secondPosition - 1) == this.passwordChar) {
                this.numberOfPasswordChar++;
            }

            if (this.numberOfPasswordChar == 1) {
                this.numberOfGoodPassword++;
            }

            this.numberOfPasswordChar = 0;
        });
        this.setSecondResult(String.valueOf(this.numberOfGoodPassword));
    }
}
