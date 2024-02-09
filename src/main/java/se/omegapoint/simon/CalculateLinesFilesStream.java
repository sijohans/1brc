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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CalculateLinesFilesStream {

    private static final String FILE = "./measurements.txt";

    public static void main(String[] args) {

        try {
            long fileCount = countFiles(FILE);
            System.out.println("Number of files listed in the text file: " + fileCount);
        }
        catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static long countFiles(String filePath) throws IOException {

        return Files.lines(Path.of(filePath))
                .count();
    }

}
