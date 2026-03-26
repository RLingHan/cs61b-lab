package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12.0));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            double sample = 0.0;
            for (GuitarString s : strings) {
                sample += s.sample();
            }

            StdAudio.play(sample);

            for (GuitarString s : strings) {
                s.tic();
            }
        }
    }
}

