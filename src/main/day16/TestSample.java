package main.day16;

import java.util.List;

import main.day16.Opcode.CODES;

public class TestSample {

    public int[] before;
    public int[] after;
    public int[] operations;

    public List<CODES> possibleCodes;

    public void setBefore(String input) {
        String[] res = input.substring(input.indexOf('[') + 1, input.indexOf(']')).split(", ");
        this.before = new int[res.length];
        for (int i = 0; i < res.length; ++i) {
            this.before[i] = Integer.parseInt(res[i]);
        }
    }

    public void setAfter(String input) {
        String[] res = input.substring(input.indexOf('[') + 1, input.indexOf(']')).split(", ");
        this.after = new int[res.length];
        for (int i = 0; i < res.length; ++i) {
            this.after[i] = Integer.parseInt(res[i]);
        }
    }

    public void setOperations(String input) {
        String[] res = input.split(" ");
        this.operations = new int[res.length];
        for (int i = 0; i < res.length; ++i) {
            this.operations[i] = Integer.parseInt(res[i]);
        }
    }
}
