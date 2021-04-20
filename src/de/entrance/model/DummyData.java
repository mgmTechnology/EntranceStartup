package de.entrance.model;

public class DummyData {
    String in;
    String out;

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public DummyData(String in, String out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public String toString() {
        return "DummyData{" + "in='" + in + '\'' + ", out='" + out + '\'' + '}';
    }
}
