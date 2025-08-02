package creational;

// eager loading
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        // private constructor
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}

// lazy loading (not thread safe)
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

// lazy loading thread safe
class LazySingletonMethodThreadSafe {

    private static LazySingletonMethodThreadSafe instance;

    private LazySingletonMethodThreadSafe() {};

    public synchronized static LazySingletonMethodThreadSafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonMethodThreadSafe();
        }
        return instance;
    }
}

class LazySingletonBlockThreadSafe {
    private static volatile LazySingletonBlockThreadSafe instance;

    private LazySingletonBlockThreadSafe() {}

    public static LazySingletonBlockThreadSafe getInstance() {
        if (instance == null) {
            synchronized(LazySingletonBlockThreadSafe.class) {
                if (instance == null) {
                    instance = new LazySingletonBlockThreadSafe();
                }
            }
        }
        return instance;
    }
}

class SingletonBillPugh {

    private SingletonBillPugh() {}

    private static class Inner {
        private static final SingletonBillPugh instance = new SingletonBillPugh();
    }

    public static SingletonBillPugh getInstance() {
        return Inner.instance;
    }
}

public class Singleton {
    public static void main(String[] args) {
        
        System.out.println(EagerSingleton.getInstance());
        System.out.println(LazySingleton.getInstance());
        System.out.println(LazySingletonMethodThreadSafe.getInstance());
        System.out.println(LazySingletonBlockThreadSafe.getInstance());
        System.out.println(SingletonBillPugh.getInstance());
    }
}
