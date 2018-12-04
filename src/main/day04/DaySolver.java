package main.day04;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.AbstractSolver;
import utils.AoCFileReader;
import utils.Day4DateTimeString;
import utils.Day4Guard;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void solvePart1() {
        List<Day4DateTimeString> input = AoCFileReader.readGuardTimes(new File(this.inputFile1));

        Collections.sort(input);

        Map<Integer, List<Day4DateTimeString>> guards = new HashMap<>();
        Integer guardIdx = -1;
        for (Day4DateTimeString val : input) {
            if (val.entry.startsWith("Guard")) {
                guardIdx = Integer.parseInt(val.entry.split(" ")[1].substring(1));
                if (!guards.containsKey(guardIdx)) {
                    guards.put(guardIdx, new ArrayList<>());
                }
                continue;
            }
            guards.get(guardIdx).add(val);
        }

        List<Day4Guard> res = new ArrayList<>();

        for (Entry<Integer, List<Day4DateTimeString>> entry : guards.entrySet()) {
            Day4Guard guard = new Day4Guard(entry.getKey());

            if (!res.contains(guard)) {
                res.add(guard);
            }

            int idx = res.indexOf(guard);

            for (int i = 0; i < (entry.getValue().size()); i += 2) {
                res.get(idx).addTime(entry.getValue().get(i).time, entry.getValue().get(i + 1).time);
            }
        }

        Day4Guard max = res.get(0);

        for (Day4Guard guard : res) {
            if (max.cntSleepingMinutes() < guard.cntSleepingMinutes()) {
                max = guard;
            }
        }

        System.out.println(max.guardId * max.getMaxSleepingMinute());

    }

    @SuppressWarnings("unchecked")
    @Override
    public void solvePart2() {
        List<Day4DateTimeString> input = AoCFileReader.readGuardTimes(new File(this.inputFile1));

        Collections.sort(input);

        Map<Integer, List<Day4DateTimeString>> guards = new HashMap<>();
        Integer guardIdx = -1;
        for (Day4DateTimeString val : input) {
            if (val.entry.startsWith("Guard")) {
                guardIdx = Integer.parseInt(val.entry.split(" ")[1].substring(1));
                if (!guards.containsKey(guardIdx)) {
                    guards.put(guardIdx, new ArrayList<>());
                }
                continue;
            }
            guards.get(guardIdx).add(val);
        }

        List<Day4Guard> res = new ArrayList<>();

        for (Entry<Integer, List<Day4DateTimeString>> entry : guards.entrySet()) {
            Day4Guard guard = new Day4Guard(entry.getKey());

            if (!res.contains(guard)) {
                res.add(guard);
            }

            int idx = res.indexOf(guard);

            for (int i = 0; i < (entry.getValue().size()); i += 2) {
                res.get(idx).addTime(entry.getValue().get(i).time, entry.getValue().get(i + 1).time);
            }
        }

        Day4Guard max = res.get(0);

        for (Day4Guard guard : res) {
            if (max.getMaxSleepingMinute2() < guard.getMaxSleepingMinute2()) {
                max = guard;
            }
        }

        System.out.println(max.guardId * max.getMaxSleepingMinute());
    }

}
