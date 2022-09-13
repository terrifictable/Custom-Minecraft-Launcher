package xyz.terrific.utils

class Logger(ap: String) {
    private var ap = "Unknown"

    /**
     * The Logger
     * @param ap This will be displayed in the brackets, to indicate where this logger is located
     */
    init {
        this.ap = ap
    }

    /**
     * @param message Sends: "[`AP`] ( {GREEN} `INFO`)   message"
     */
    fun success(message: String?) {
        System.out.printf("[%s] (\u001b[92mINFO\u001b[0m)  %s\n", ap, message)
    }

    /**
     * @param message Sends: "[`AP`] ( {GRAY} `INFO`)   message"
     */
    fun info(message: String?) {
        System.out.printf("[%s] (\u001b[90mINFO\u001b[0m)  %s\n", ap, message)
    }

    /**
     * @param message Sends: "[`AP`] ( {YELLOW} `WARNING`)   message"
     */
    fun warning(message: String?) {
        System.out.printf("[%s] (\u001b[93mWARNING\u001b[0m)  %s\n", ap, message)
    }

    /**
     * @param message Sends: "[`AP`] ( {RED} `ERROR`)   message"
     */
    fun error(message: String?) {
        System.out.printf("[%s] (\u001b[91mERROR\u001b[0m)  %s\n", ap, message)
    }
}
