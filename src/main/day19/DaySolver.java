package main.day19;

import java.io.File;
import java.util.List;

import main.AbstractSolver;
import main.day19.Opcode.CODES;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @Override
    public void solvePart1() {
        List<String[]> input = AoCFileReader.readProgramLines(new File(this.inputFile1));

        int ip = 0;
        long[] registers = {0, 0, 0, 0, 0, 0};
        int[] operations = new int[4];
        
        while (ip < input.size()) {
            String[] prog = input.get(ip);
            operations[1] = Integer.parseInt(prog[1]);
            operations[2] = Integer.parseInt(prog[2]);
            operations[3] = Integer.parseInt(prog[3]);
            registers = Opcode.operate(registers, operations, CODES.valueOf(prog[0]));
            registers[1] = registers[1] + 1;
            ip = (int) registers[1];
        }
        System.out.println(registers[0]);
    }

    @Override
    public void solvePart2() {
        int res = 0;
        int divisor = 10551370; // got from output below
        for (int i = 1; i <= divisor; ++i) {
            if (divisor % i == 0) {
                res+=i;
            }
        }
        
        System.out.println(res);
        
//        List<String[]> input = AoCFileReader.readProgramLines(new File(this.inputFile2));
//
//        int ip = 0;
//        long[] registers = {1, 0, 0, 0, 0, 0};
//        int[] operations = new int[4];
//        while (ip < input.size()) {
//            String[] prog = input.get(ip);
//            operations[1] = Integer.parseInt(prog[1]);
//            operations[2] = Integer.parseInt(prog[2]);
//            operations[3] = Integer.parseInt(prog[3]);
//            
////            if (prog[0].equals("eqrr")) {
////                registers[5] = registers[4];
////            }
//            
//            registers = Opcode.operate(registers, operations, CODES.valueOf(prog[0]));
//            registers[1] = registers[1] + 1;
//            ip = (int) registers[1];
//
////            for (int i = 0; i < registers.length; ++i) {
////                System.out.print(registers[i] + " ");
////            }
////            System.out.println();
//        }
//        System.out.println(registers[0]);
    }

}
