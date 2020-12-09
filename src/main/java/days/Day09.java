package days;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day09 extends Day {

    List<String> lines;
    long goal;

    @Override
    public void executeChallenge(ExecuteChallengeSpecifics function) {
        try {
            this.lines = Files.readAllLines(Paths.get("src/main/resources/day09.txt"), StandardCharsets.UTF_8);
            function.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
            for (int i = 25; i < lines.size(); i++) {
                int startIndex = i - 25;
                long value = Long.parseLong(lines.get(i));
                boolean goodValue = false;

                for (int j = startIndex; j < i; j++) {
                    for (int k = j + 1; k < i; k++) {
                        if (Long.parseLong(lines.get(j)) + Long.parseLong(lines.get(k)) == value) {
                            goodValue = true;
                        }
                    }
                }
                if (!goodValue) {
                    setFirstResult(String.valueOf(value));
                    this.goal = value;
                    break;
                }
            }
        });
    }

    @Override
    public void challenge02() {
        this.executeChallenge(() -> {
            ArrayList<Long> numbers = new ArrayList<Long>();
            loop: for (int i = 0; i < lines.size(); i++) {
                long sum = 0;
                for (int j = i + 1; j < lines.size(); j++) {
                    long nbr = Long.parseLong(lines.get(j));
                    sum += nbr;
                    numbers.add(nbr);
                    if (sum == this.goal) {
                        long smallest = numbers.get(0);
                        long largest = numbers.get(0);
                        for (Long number : numbers) {
                            if (number < smallest) {
                                smallest = number;
                            }
                            if (number > largest) {
                                largest = number;
                            }
                        }
                        setSecondResult(String.valueOf(smallest + largest));
                        break loop;
                    }
                    if (sum > this.goal) {
                        numbers.clear();
                        break;
                    }
                }

            }
        });
    }
}
