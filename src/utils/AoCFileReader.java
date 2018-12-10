package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.day03.Day3CoordinateEntry;
import main.day04.Day4DateTimeString;
import main.day06.Day6Point;
import main.day07.Day7Node;
import main.day10.Day10Point;

public class AoCFileReader {
    public static List<Integer> readIntegerLineVertical(File f) {
        List<Integer> res = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<List<String>> readListOfCharList(File f) {
        List<List<String>> res = new ArrayList<List<String>>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> charLine = new ArrayList<String>();
                for (char c : line.toCharArray()) {
                    charLine.add(String.valueOf(c));
                }
                res.add(charLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Day3CoordinateEntry> readCoordinateList(File f) {
        List<Day3CoordinateEntry> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.add(new Day3CoordinateEntry(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Day4DateTimeString> readGuardTimes(File f) {
        List<Day4DateTimeString> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.add(new Day4DateTimeString(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String readOneLine(File f) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            line = br.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static List<String> readMulitpleLines(File f) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<Day6Point> readPointList(File f) {
        List<Day6Point> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            char c = 'A';
            while ((line = br.readLine()) != null) {
                res.add(new Day6Point(line, c++));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Day7Node> readNodeSet(File f) {
        List<Day7Node> res = new ArrayList<>();
        char c = 'A';
        for (int i = 0; i < 26; ++i) {
            res.add(new Day7Node(c++));
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                Day7Node node = new Day7Node(line);
                if (res.contains(node)) {
                    int idx = res.indexOf(node);
                    res.get(idx).preConditions.addAll(node.preConditions);
                } else {
                    res.add(new Day7Node(line));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<Day10Point> readPointVelList(File f) {
        List<Day10Point> res = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.add(new Day10Point(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
