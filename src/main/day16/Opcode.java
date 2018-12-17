package main.day16;

public final class Opcode {

    public enum CODES {
        addr, addi, mulr, muli, banr, bani, borr, bori, setr, seti, gtir, gtri, gtrr, eqir, eqri, eqrr
    }

    public static int[] operate(int[] registers, int[] operations, CODES code) {
        int[] res = registers.clone();

        boolean aReg = true;
        boolean bReg = true;

        if (operations[1] >= registers.length) {
            aReg = false;
        }

        if (operations[2] >= registers.length) {
            bReg = false;
        }

        switch (code) {
        case addr:
            if (aReg && bReg) {
                res[operations[3]] = registers[operations[1]] + registers[operations[2]];
            } else {
                return null;
            }
            break;
        case addi:
            if (aReg) {
                res[operations[3]] = registers[operations[1]] + operations[2];
            } else {
                return null;
            }
            break;
        case mulr:
            if (aReg && bReg) {
                res[operations[3]] = registers[operations[1]] * registers[operations[2]];
            } else {
                return null;
            }
            break;
        case muli:
            if (aReg) {
                res[operations[3]] = registers[operations[1]] * operations[2];
            } else {
                return null;
            }
            break;
        case banr:
            if (aReg && bReg) {
                res[operations[3]] = registers[operations[1]] & registers[operations[2]];
            } else {
                return null;
            }
            break;
        case bani:
            if (aReg) {
                res[operations[3]] = registers[operations[1]] & operations[2];
            } else {
                return null;
            }
            break;
        case borr:
            if (aReg && bReg) {
                res[operations[3]] = registers[operations[1]] | registers[operations[2]];
            } else {
                return null;
            }
            break;
        case bori:
            if (aReg) {
                res[operations[3]] = registers[operations[1]] | operations[2];
            } else {
                return null;
            }
            break;
        case setr:
            if (aReg) {
                res[operations[3]] = registers[operations[1]];
            } else {
                return null;
            }
            break;
        case seti:
            res[operations[3]] = operations[1];
            break;
        case gtir:
            if (bReg) {
                res[operations[3]] = operations[1] > registers[operations[2]] ? 1 : 0;
            } else {
                return null;
            }
            break;
        case gtri:
            if (aReg) {
                res[operations[3]] = registers[operations[1]] > operations[2] ? 1 : 0;
            } else {
                return null;
            }
            break;
        case gtrr:
            if (aReg && bReg) {
                res[operations[3]] = registers[operations[1]] > registers[operations[2]] ? 1 : 0;
            } else {
                return null;
            }
            break;
        case eqir:
            if (bReg) {
                res[operations[3]] = operations[1] == registers[operations[2]] ? 1 : 0;
            } else {
                return null;
            }
            break;
        case eqri:
            if (aReg) {
                res[operations[3]] = registers[operations[1]] == operations[2] ? 1 : 0;
            } else {
                return null;
            }
            break;
        case eqrr:
            if (aReg && bReg) {
                res[operations[3]] = registers[operations[1]] == registers[operations[2]] ? 1 : 0;
            } else {
                return null;
            }
            break;
        default:
            return null;
        }

        return res;
    }

}
