package days;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 extends Day {
    Map<String, String> passport = new HashMap<>();
    private List<String> lines;
    private int numberOfGoodPassport;

    @Override
    public void executeChallenge(ExecuteChallengeSpecifics function) {
        this.seedHashMap();
        try {
            this.lines = Files.readAllLines(Paths.get("src/main/resources/day04.txt"), StandardCharsets.UTF_8);
            for (String line : lines) {

                if (line.trim().isEmpty()) {
                    function.run();
                    this.seedHashMap();
                    continue;
                }

                String[] splitLine = line.split(" ");

                for (String split : splitLine) {
                    String passportAttribute = split.substring(0, 3);
                    String value = split.substring(4);

                    if (passport.get(passportAttribute) != null) {
                        this.passport.put(passportAttribute, value);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void challenge01() {
        this.executeChallenge(() -> {
            boolean valid = true;
            for (String value : this.passport.values()) {
                if (value.equals("")) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                this.numberOfGoodPassport++;
            }
        });
        this.setFirstResult(String.valueOf(this.numberOfGoodPassport));
    }

    @Override
    public void challenge02() {
        this.numberOfGoodPassport = 0;
        this.executeChallenge(() -> {
            boolean valid = true;
            loop:
            for (Map.Entry<String, String> entry : this.passport.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value.equals("")) {
                    valid = false;
                    break;
                }
                if (key.equals("byr")) {
                    int year = Integer.parseInt(value);
                    if (year < 1920 || year > 2002) {
                        valid = false;
                        break;
                    }
                }
                if (key.equals("iyr")) {
                    int issue = Integer.parseInt(value);
                    if (issue < 2010 || issue > 2020) {
                        valid = false;
                        break;
                    }
                }
                if (key.equals("eyr")) {
                    int expiration = Integer.parseInt(value);
                    if (expiration < 2020 || expiration > 2030) {
                        valid = false;
                        break;
                    }
                }
                if (key.equals("hgt")) {
                    String type = value.substring(value.length() - 2);
                    int typeValue = Integer.parseInt(value.substring(0, value.indexOf(type)));
                    switch (type) {
                        case "cm":
                            if (typeValue < 150 || typeValue > 193) {
                                valid = false;
                                break loop;
                            }
                            break;
                        case "in":
                            if (typeValue < 59 || typeValue > 76) {
                                valid = false;
                                break loop;
                            }
                            break;
                        default:
                            valid = false;
                            break loop;
                    }
                }
                if (key.equals("hcl")) {
                    Pattern p = Pattern.compile("[#][a-f0-9]{6}");
                    Matcher m = p.matcher(value);
                    if (!m.matches()) {
                        valid = false;
                        break;
                    }
                }

                if (key.equals("ecl")) {
                    if (value.matches("amb|blu|brn|gry|grn|hzl|oth")) {
                        continue;
                    } else {
                        valid = false;
                        break;
                    }
                }

                if (key.equals("pid")) {
                    Pattern p = Pattern.compile("[0-9]{9}");
                    Matcher m = p.matcher(value);
                    if (!m.matches()) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) {
                this.numberOfGoodPassport++;
            }
        });
        this.setSecondResult(String.valueOf(this.numberOfGoodPassport));
    }

    private void seedHashMap() {
        this.passport.put("byr", "");
        this.passport.put("iyr", "");
        this.passport.put("eyr", "");
        this.passport.put("hgt", "");
        this.passport.put("hcl", "");
        this.passport.put("ecl", "");
        this.passport.put("pid", "");
    }
}
