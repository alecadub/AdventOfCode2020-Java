import days.Day01;
import days.Day02;
import days.Day03;

public class Main {
    public static void main(String[] args) {
        Day01 day01 = new Day01();
        System.out.println("Day 1 results:");
        day01.run();

        Day02 day02 = new Day02();
        System.out.println("Day 2 results:");
        day02.run();

        Day03 day03 = new Day03();
        System.out.println("Day 3 results:");
        day03.run();
    }
}
