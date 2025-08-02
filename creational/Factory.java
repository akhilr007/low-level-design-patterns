package creational;

interface Logger {
    void log(String message);
}

class FileLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("File logger : " + message);
    }
}

class ConsoleLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("Console logger : " + message);
    }
}

class DbLogger implements Logger {

    @Override
    public void log(String message) {
        System.out.println("Database logger : " + message);
    }
}

class LoggerFactory {

    public static Logger getLogger(String type) {

        switch (type.toLowerCase()) {
            case "console":
                return new ConsoleLogger();
            case "file":
                return new FileLogger();
            case "db":
                return new DbLogger();
            default:
                throw new IllegalArgumentException("Unknown logger type: " + type);
        }
    }
}

public class Factory {
    public static void main(String[] args) {
        
        Logger fileLogger = LoggerFactory.getLogger("file");
        fileLogger.log("This is a message to the file");

        Logger consoleLogger = LoggerFactory.getLogger("console");
        consoleLogger.log("This is a message to the file");

        Logger dbLogger = LoggerFactory.getLogger("db");
        dbLogger.log("This is a message to the file");


    }
}
