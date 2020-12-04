package days;

public abstract class Day {
    private String result = "";

    public abstract void run();

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
