package xyz.terrific

import xyz.terrific.utils.Logger
import xyz.terrific.utils.OSHelper
import xyz.terrific.utils.UnzipUtility
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

object StartGame {

    /**
     * Launches Minecraft (or more specific, it starts the Client.jar file)
     */
    fun start() {
        val logger = Logger("GAME")
        // logger.warning("Downloading Game!");
        val minecraft_directory = File(OSHelper.getOS().mc)
        val minecraft_assets = File(minecraft_directory.toString() + "assets")

        /*
        File natives = new File(System.getProperty("user.dir") + File.separator + "natives.zip");
        File libraries = new File(System.getProperty("user.dir") + File.separator + "libraries.zip");
        File jar = new File(System.getProperty("user.dir") + File.separator + "client.jar");

        FileUtils.copyURLToFile(new URL("https://anonfiles.com/Feg1T5k8yc/natives_zip"), natives);
        FileUtils.copyURLToFile(new URL(""), libraries);
        FileUtils.copyURLToFile(new URL(""), jar);
        logger.success("Downloaded Game!");
        */
        val natives = File("D:\\Java_Projects\\minecraft_launcher\\misc\\libraries.zip")
        val libraries = File("D:\\Java_Projects\\minecraft_launcher\\misc\\natives.zip")
        val jar = File("D:\\Java_Projects\\minecraft_launcher\\misc\\client.jar")
        val unzip = UnzipUtility()
        unzip.unzip(natives.toString(), System.getProperty("user.dir") + File.separator + "natives")
        unzip.unzip(libraries.toString(), System.getProperty("user.dir") + File.separator + "libraries")
        // natives.delete();
        // libraries.delete();
        Main.frame.progressbar.isVisible = true
        Thread {
            for (i in 0..100) {
                if (Main.frame.progressbar.value >= 100) break
                Main.frame.progressbar.value = i
                try {
                    Thread.sleep((100 + Math.random() * (30 - 10 + 1)).toLong())
                } catch (e: InterruptedException) {
                    logger.error("LoadingBar Thread:  " + e.message)
                }
            }
        }.start()
        Thread {
            logger.warning("Starting Game!")
            var process: Process? = null
            try {
                process = Runtime.getRuntime().exec(
                    "java "
                            + "-Xms1024M "
                            + "-Xmx4096M "
                            + "-Djava.library.path=\"" + System.getProperty("user.dir") + File.separator + "natives" + "\" "
                            + "-cp \"" + System.getProperty("user.dir") + File.separator + "libraries" + File.separator + "*" + ";" + jar + "\" "
                            + "net.minecraft.client.main.Main " // TODO Actual Username and Password
                            + "--Username Test "
                            + "--gameDir " + minecraft_directory + " "
                            + "assetsIndex 1.8.8 "
                            + "uuid N/A "
                            + "--accessToken aeef7bc935f9420eb6314dea7ad7e1e5 "
                            + "--version 1.8.8"
                            + "--userType mojang"
                )
            } catch (e: IOException) {
                logger.error("Failed to start Game:  " + e.message)
            }
            logger.success("Started Game!")
            val stdin = BufferedReader(InputStreamReader(process!!.inputStream))
            val stderr = BufferedReader(InputStreamReader(process.errorStream))
            val millis = intArrayOf(0)
            val timeout = 5000
            try {
                Thread {
                    while (millis[0] < timeout) {
                        millis[0]++
                        try {
                            Thread.sleep(1)
                        } catch (e: InterruptedException) {
                            logger.error("Millis counter Failed:  " + e.message)
                        }
                    }
                    Thread.currentThread().stop()
                }.start()
                var s: String? = null
                while (stdin.readLine().also { s = it } != null) {
                    Main.frame.progressbar.value = 100
                    logger.info(s)
                    if (millis[0] >= timeout) {
                        Main.frame.dispose()
                    }
                }
                while (stderr.readLine().also { s = it } != null) {
                    logger.error(s)
                }
            } catch (e: IOException) {
                logger.error(e.message)
            }
        }.start()
    }
}
