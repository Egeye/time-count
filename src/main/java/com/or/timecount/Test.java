package com.or.timecount;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(findJar(System.getProperty("user.dir") + File.separator + "class.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<String> findJar(String txtPth) throws IOException {
        final List<String> list = new ArrayList<String>();
        // Files.readAllLines(Paths.get(txtPth), Charset.forName("GBK")).forEach(s -> {
        //     if (s.contains("time-count_jar") && !list.contains(s.split("time-count_jar")[1])) {
        //         list.add(s.split("time-count_jar")[1]);
        //     }
        // });
        return list;
    }
}
