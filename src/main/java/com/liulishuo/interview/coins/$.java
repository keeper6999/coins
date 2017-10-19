package com.liulishuo.interview.coins;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class $ {

    private static String getPid() throws IllegalStateException {
        String name = ManagementFactory.getRuntimeMXBean().getName();

        int index = name.indexOf("@");

        if (index == -1) {
            throw new IllegalStateException("Could not get pid of " + name);
        } else {
            return name.substring(0, index);
        }
    }


    public static String getJstack() throws Exception {
        String[] command = {"jstack", "-l", getPid()};

        Process process = Runtime.getRuntime().exec(command);

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            reader.lines().forEach(
                    line -> stringBuilder.append(line).append("<br>")
            );
        }

        return stringBuilder.toString();
    }
}
