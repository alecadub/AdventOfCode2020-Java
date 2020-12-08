package days;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 extends Day {
    private int acc = 0;
    List<String> lines;
    Map<Integer, String> map = new HashMap<>();

    @Override
    public void executeChallenge(ExecuteChallengeSpecifics function) {
        try {
            this.lines = Files.readAllLines(Paths.get("src/main/resources/day08.txt"), StandardCharsets.UTF_8);
            function.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String instruction = line.substring(0, 3);
                int value = Integer.parseInt(line.substring(4));

                if (map.containsKey(i)) {
                    break;
                } else {
                    i = executeInstruction(i, line, instruction, value);
                }
            }
        });
        setFirstResult(String.valueOf(this.acc));
    }

    @Override
    public void challenge02() {
        this.acc = 0;

        this.executeChallenge(() -> {
            int infineLoopCounter = 0;
            boolean wasInfineLoop = false;

            for (int j = 0; j < lines.size(); j++) {
                String line = lines.get(j);
                String instruction = line.substring(0, 3);
                String stringValue = line.substring(4);

                if (instruction.equals("nop")) {
                    lines.set(j, "jmp" + " " + stringValue);
                }

                if (instruction.equals("jmp")) {
                    lines.set(j, "nop" + " " + stringValue);
                }

                for (int i = 0; i < lines.size(); i++) {
                    String line2 = lines.get(i);
                    String instruction2 = line2.substring(0, 3);
                    int value2 = Integer.parseInt(line2.substring(4));

                    if (map.containsKey(i)) {
                        if (infineLoopCounter > 10) {
                            infineLoopCounter = 0;
                            wasInfineLoop = true;
                            break;
                        } else {
                            infineLoopCounter++;
                        }
                    } else {
                        i = executeInstruction(i, line2, instruction2, value2);
                    }
                }

                if (!wasInfineLoop) {
                    setSecondResult(String.valueOf(this.acc));
                    break;
                } else {
                    this.acc = 0;

                    if (instruction.equals("nop")) {
                        lines.set(j, "nop" + " " + stringValue);
                    }

                    if (instruction.equals("jmp")) {
                        lines.set(j, "jmp" + " " + stringValue);
                    }

                    map.clear();
                    wasInfineLoop = false;
                }
            }
        });
    }

    private int executeInstruction(int i, String line, String instruction, int value) {
        switch (instruction) {
            case "acc":
                this.acc += value;
                break;
            case "jmp":
                i += value - 1;
                break;
            default:
                break;
        }
        map.put(i, line);
        return i;
    }
}
