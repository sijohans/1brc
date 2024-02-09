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

public class CalculateLines {

    private static final String FILE = "./measurements.txt";

    public static void main(String[] args) {

        // Chat GPT version

        try {
            int fileCount = countFiles(FILE);
            System.out.println("Number of files listed in the text file: " + fileCount);
        }
        catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static int countFiles(String filePath) throws IOException {
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
            }
        }

        return count;
    }

}
