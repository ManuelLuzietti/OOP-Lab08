package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import org.yaml.snakeyaml.Yaml;

import sun.jvm.hotspot.oops.BooleanField;



/**
 *
 */
public final class DrawNumberImpl implements DrawNumber {

    private int choice;
    private  final int min;
    private  final int max;
    private  final int attempts;
    private int remainingAttempts;
    private final Random random = new Random();

    /**
     * @param min
     *            minimum number
     * @param max
     *            maximum number
     * @param attempts
     *            the maximum number of attempts
     */
    public DrawNumberImpl(final int min, final int max, final int attempts) {
        this.min = min;
        this.max = max;
        this.attempts = attempts;
        this.reset();
    }
//    public DrawNumberImpl() {
//        boolean minBool = false;
//        boolean maxBool = false;
//        boolean attemptsBool = false;
//        try (InputStream stream = ClassLoader.getSystemResourceAsStream("config.yml");
//                BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
//            String line;
//            StringTokenizer tokenizer;
//            while ((line = reader.readLine()) != null) {
//                tokenizer = new StringTokenizer(line, ": ");
//                while (tokenizer.hasMoreTokens()) {
//                    String nextToken = tokenizer.nextToken();
//                    if ("minimum".equals(nextToken) && !minBool) {
//                        this.min = Integer.parseInt(tokenizer.nextToken());
//                        minBool = true;
//                    } else if ("maximum".equals(nextToken) && !maxBool) {
//                        this.max = Integer.parseInt(tokenizer.nextToken());
//                        maxBool = true;
//                    } else if ("attempts".equals(nextToken) && !attemptsBool) {
//                        this.attempts = Integer.parseInt(tokenizer.nextToken());
//                        attemptsBool = true;
//                    }
//                }
//            }
//            if (!minBool | !maxBool | !attemptsBool) {
//                throw new RuntimeException();
//            }
//        } catch (IOException | IllegalArgumentException | SecurityException e) {
//            e.getStackTrace();
//            System.out.println("qualcosa è andato storto. (config file errato?)");
//            throw new RuntimeException();
//        }
//        this.reset();
//    }
    public DrawNumberImpl()  {
        Yaml yaml = new Yaml();
        try (InputStream stream = ClassLoader.getSystemResourceAsStream("config.yaml")){
            Map<String, Integer> map = yaml.load(stream);
            this.min = map.get("minimum");
            this.max = map.get("maximum");
            this.attempts = map.get("attempts");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } 
    }


    @Override
    public void reset() {
        this.remainingAttempts = this.attempts;
        this.choice = this.min + random.nextInt(this.max - this.min + 1);
    }

    @Override
    public DrawResult attempt(final int n) throws AttemptsLimitReachedException {
        if (this.remainingAttempts <= 0) {
            throw new AttemptsLimitReachedException();
        }
        if (n < this.min || n > this.max) {
            throw new IllegalArgumentException("The number is outside boundaries");
        }
        remainingAttempts--;
        if (n > this.choice) {
            return DrawResult.YOURS_HIGH;
        }
        if (n < this.choice) {
            return DrawResult.YOURS_LOW;
        }
        return DrawResult.YOU_WON;
    }

}
