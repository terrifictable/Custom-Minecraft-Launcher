package xyz.terrific.utils;

public class Logger {
    private String ap = "Unknown";

    /**
     * The Logger
     * @param ap This will be displayed in the brackets, to indicate where this logger is located
     */
    public Logger(String ap) {
        this.ap = ap;
    }


    /**
     * @param message Sends: "[`AP`] ( {GREEN} `INFO`)   message"
     */
    public void success(String message) {
        System.out.printf("[%s] (\033[92mINFO\033[0m)  %s\n", this.ap, message);
    }

    /**
     * @param message Sends: "[`AP`] ( {GRAY} `INFO`)   message"
     */
    public void info(String message) {
        System.out.printf("[%s] (\033[90mINFO\033[0m)  %s\n", this.ap, message);
    }

    /**
     * @param message Sends: "[`AP`] ( {YELLOW} `WARNING`)   message"
     */
    public void warning(String message) {
        System.out.printf("[%s] (\033[93mWARNING\033[0m)  %s\n", this.ap, message);
    }

    /**
     * @param message Sends: "[`AP`] ( {RED} `ERROR`)   message"
     */
    public void error(String message) {
        System.out.printf("[%s] (\033[91mERROR\033[0m)  %s\n", this.ap, message);
    }

}
