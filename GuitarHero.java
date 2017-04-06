/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {

    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; // 37-key keyboard
    private static int boardLength = keyboard.length();
    private static synthesizer.GuitarString[] CONCERT = new synthesizer.GuitarString[boardLength];


    public static void main(String[] args) {

        for(int i = 0; i < boardLength; i++)
        {
            CONCERT[i] = new synthesizer.GuitarString( 440.0 * Math.pow(2, (i - 24) / 12.0) );
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                if (keyboard.indexOf(key) >= 0)
                    CONCERT[keyboard.indexOf(key)].pluck();
            }

        /* compute the superposition of samples */
            double sample = 0;
            for(synthesizer.GuitarString GS: CONCERT)
            {
                sample += GS.sample();
            }

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            for(synthesizer.GuitarString GS: CONCERT)
            {
                GS.tic();
            }
        }


    }


}
