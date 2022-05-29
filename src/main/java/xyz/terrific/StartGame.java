package xyz.terrific;

import xyz.terrific.utils.Logger;
import xyz.terrific.utils.OSHelper;
import xyz.terrific.utils.UnzipUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static xyz.terrific.Main.frame;


public class StartGame {

    /**
     * Launches Minecraft (or more specific, it starts the Client.jar file)
     * @throws IOException
     */
    public static void start() throws IOException {
        Logger logger = new Logger("GAME");
        // logger.warning("Downloading Game!");


        File minecraft_directory = new File(OSHelper.getOS().getMc());
        File minecraft_assets = new File(minecraft_directory + "assets");

        /*
        File natives = new File(System.getProperty("user.dir") + File.separator + "natives.zip");
        File libraries = new File(System.getProperty("user.dir") + File.separator + "libraries.zip");
        File jar = new File(System.getProperty("user.dir") + File.separator + "client.jar");

        FileUtils.copyURLToFile(new URL("https://anonfiles.com/Feg1T5k8yc/natives_zip"), natives);
        FileUtils.copyURLToFile(new URL(""), libraries);
        FileUtils.copyURLToFile(new URL(""), jar);
        logger.success("Downloaded Game!");
        */

        File natives = new File("D:\\Java_Projects\\minecraft_launcher\\misc\\libraries.zip");
        File libraries = new File("D:\\Java_Projects\\minecraft_launcher\\misc\\natives.zip");
        File jar = new File("D:\\Java_Projects\\minecraft_launcher\\misc\\client.jar");

        UnzipUtility unzip = new UnzipUtility();
        unzip.unzip(natives.toString(), System.getProperty("user.dir") + File.separator + "natives");
        unzip.unzip(libraries.toString(), System.getProperty("user.dir") + File.separator +  "libraries");
        // natives.delete();
        // libraries.delete();

        frame.progressbar.setVisible(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<101; i++) {
                    if (frame.progressbar.getValue() >= 100) break;

                    frame.progressbar.setValue(i);
                    try {
                        Thread.sleep((long) (100 + (Math.random() * (20 - 1 + 1))));
                    } catch (InterruptedException e) {
                        logger.error("LoadingBar Thread:  " + e.getMessage());
                    }
                }
            }
        }).start();

        File finalJar = jar;
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.warning("Starting Game!");
                Process process = null;
                try {
                    process = Runtime.getRuntime().exec("java "
                            + "-Xms1024M "
                            + "-Xmx4096M "
                            + "-Djava.library.path=\"" + System.getProperty("user.dir") + File.separator + "natives" + "\" "
                            + "-cp \"" + System.getProperty("user.dir") + File.separator + "libraries" + File.separator + "*" + ";" + finalJar + "\" "
                            + "net.minecraft.client.main.Main "
                            + "--width 854 "
                            + "--height 400 "
                            // TODO Actual Username and Password
                            + "--Username Test "

                            + "--gameDir " + minecraft_directory + " "
                            + "assetsIndex 1.8.8 "
                            + "uuid N/A "
                            + "--accessToken aeef7bc935f9420eb6314dea7ad7e1e5 "
                            + "--version 1.8.8"
                            + "--userType mojang"
                    );
                } catch (IOException e) {
                    logger.error("Failed to start Game:  " + e.getMessage());
                }

                logger.success("Started Game!");


                BufferedReader stdin = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                final int[] millis = {0};
                int timeout = 5000;
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            while (!(millis[0] >= timeout)) {
                                millis[0]++;
                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException e) {
                                    logger.error("Millis counter Failed:  " + e.getMessage());
                                }
                            }

                            Thread.currentThread().stop();
                        }
                    }).start();

                    String s = null;
                    while ((s = stdin.readLine()) != null) {
                        frame.progressbar.setValue(100);
                        logger.info(s);
                        if (millis[0] >= timeout) {
                            frame.dispose();
                        }
                    }
                    while ((s = stderr.readLine()) != null) {
                        logger.error(s);
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }).start();

    }

}
