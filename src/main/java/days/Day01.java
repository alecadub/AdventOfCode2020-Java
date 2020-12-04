package days;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day01 extends Day {
    @Override
    public void challenge01() {
        try {
            int year = 2020;
            int firstNbr = 0;
            int secondNbr = 0;
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/day01.txt"), StandardCharsets.UTF_8);

            for (int i = 0; i < lines.size(); i++) {
                firstNbr = Integer.parseInt(lines.get(i));
                for (int j = i + 1; j < lines.size(); j++) {
                    secondNbr = Integer.parseInt(lines.get(j));

                    if (firstNbr + secondNbr == year) {
                        setFirstResult(String.valueOf(firstNbr * secondNbr));
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void challenge02() {
        try {
            int year = 2020;
            int firstNbr = 0;
            int secondNbr = 0;
            int thirdNbr = 0;
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/day01.txt"), StandardCharsets.UTF_8);

            for (int i = 0; i < lines.size(); i++) {
                firstNbr = Integer.parseInt(lines.get(i));
                for (int j = i + 1; j < lines.size(); j++) {
                    secondNbr = Integer.parseInt(lines.get(j));

                    for (int k = j + 1; k < lines.size(); k++) {
                        thirdNbr = Integer.parseInt(lines.get(k));

                        if (firstNbr + secondNbr + thirdNbr == year) {
                            setSecondResult(String.valueOf(firstNbr * secondNbr * thirdNbr));
                            return;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
