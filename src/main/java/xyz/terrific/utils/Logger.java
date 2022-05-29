package xyz.terrific.utils;

public class Logger {
    private String ap = "Unknown";

    public Logger(String ap) {
        this.ap = ap;
    }


    public void success(String message) {
        System.out.printf("[%s] (\033[92mINFO\033[0m)  %s\n", this.ap, message);
    }
    public void info(String message) {
        System.out.printf("[%s] (\033[90mINFO\033[0m)  %s\n", this.ap, message);
    }
    public void warning(String message) {
        System.out.printf("[%s] (\033[93mWARNING\033[0m)  %s\n", this.ap, message);
    }
    public void error(String message) {
        System.out.printf("[%s] (\033[91mERROR\033[0m)  %s\n", this.ap, message);
    }

}
