import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.at;
import be.tarsos.dsp.io.
import be.tarsos.dsp.io.
import be.tarsos.dsp.onsets.OnsetHandler;
import be.tarsos.dsp.onsets.PercussionOnsetDetector;
import be.tarsos.dsp.util.AudioDispatcherFactory;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class RhythmAnalyzer {

    public static void main(String[] args) {
        File audioFile = new File("/Users/aiden/Desktop/Java/Music2.mp3");

        try {
            AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(audioFile, 2048, 0);

            // Create a PercussionOnsetDetector with a custom onset handler
            PercussionOnsetDetector onsetDetector = new PercussionOnsetDetector(dispatcher.getFormat(), 2048, 1024,
                    new OnsetHandler() {
                        @Override
                        public void handleOnset(double time, double salience) {
                            // Handle onset event, e.g., update game elements
                            System.out.println("Onset detected at time: " + time);
                        }
                    });

            dispatcher.addAudioProcessor(onsetDetector);

            dispatcher.run();
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
