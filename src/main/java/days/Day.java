package days;

public abstract class Day {
    private final String[] results = {"", ""};

    public void run() {
        this.challenge01();
        this.challenge02();
        printResults();
    }

    public abstract void executeChallenge(executeChallengeSpecifics function);

    public abstract void challenge01();

    public abstract void challenge02();

    public void printResults() {
        for (String result : this.results) {
            System.out.println(result);
        }
    }

    public void setFirstResult(String result) {
        this.results[0] = result;
    }

    public void setSecondResult(String result) {
        this.results[1] = result;
    }
}
