package SsgExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class SSG {
    public static class Data {
        private final int posX;
        private final int posZ;
        private final long seed;
        private final double distanceToSpawn;
        private final int spawnX;
        private final int spawnZ;

        public Data(long seed, int posX, int posZ, int spawnX, int spawnZ,double distanceToSpawn) {
            this.posX = posX;
            this.posZ = posZ;
            this.seed = seed;
            this.spawnX = spawnX;
            this.spawnZ = spawnZ;
            this.distanceToSpawn = distanceToSpawn;
        }

        public double getDistanceToSpawn() {
            return distanceToSpawn;
        }
        public int getPosX() {
            return posX;
        }
        public int getPosZ() {
            return posZ;
        }
        public long getSeed() {
            return seed;
        }

        public int getSpawnX() {
            return spawnX;
        }

        public int getSpawnZ() {
            return spawnZ;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "posX=" + posX +
                    ", posZ=" + posZ +
                    ", seed=" + seed +
                    ", distance to spawn=" + distanceToSpawn +
                    ", spawnX=" + spawnX +
                    ", spawnZ=" + spawnZ +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        List<Data> seeds = new ArrayList<>();
        InputStream in = SSG.class.getResourceAsStream("all_12_eyes_sorted_ring_1_first.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        reader.readLine(); // skip header
        for (Object line : reader.lines().toArray()) {
            String[] lines = ((String) line).trim().split(Pattern.quote(","));
            // -6912834668082326109,240,-244,656,-1020,882
            seeds.add(new Data(Long.parseLong(lines[0]),
                    Integer.parseInt(lines[1]),
                    Integer.parseInt(lines[2]),
                    Integer.parseInt(lines[3]),
                    Integer.parseInt(lines[4]),
                    Double.parseDouble(lines[5])));
        }
        seeds.sort(Comparator.comparingDouble(Data::getDistanceToSpawn));
        for (int i = 0; i < 10000; i++) {
            System.out.println(seeds.get(i));
        }
    }
}
