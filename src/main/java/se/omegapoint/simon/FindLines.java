/*
 *  Copyright 2023 The original authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package se.omegapoint.simon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FindLines {

    private static final String FILE = "./measurements.txt";

    public static void main(String[] args) {
        File file = new File(FILE);
        long fileSize = file.length();

        System.out.println("File size: " + fileSize + " bytes");

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {

            long parts = 8;
            long jump = fileSize/parts;
            long[] starts = new long[(int) parts];
            starts[0] = 0; // lol

            for (int i = 1; i < parts; ++i) {
                randomAccessFile.seek(jump * i);
                starts[i] = findNearestNewline(randomAccessFile);
            }

            /*for (int i = 0; i < parts; ++i) {
                randomAccessFile.seek(starts[i]);
            }*/

            Arrays.stream(starts)
                    .forEach(i -> {
                        System.out.println("New line start at: " + i);
                    });

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static long findNearestNewline(RandomAccessFile randomAccessFile) throws IOException {
        long position = randomAccessFile.getFilePointer();
        while (position < randomAccessFile.length()) {
            int readByte = randomAccessFile.read();
            if (readByte == '\n') {
                return randomAccessFile.getFilePointer();
            }
            position++;
            randomAccessFile.seek(position);
        }
        // No newline found, return end of file
        return randomAccessFile.length();
    }

}
