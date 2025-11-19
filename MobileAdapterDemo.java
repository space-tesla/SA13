// Volt class
class Volt {
    private int volts;

    public Volt(int v) {
        volts = v;
    }

    public int getVolts() {
        return volts;
    }
}

// Adaptee: Socket (120V supply)
class Socket {
    public Volt getVolt() {
        return new Volt(120); // default socket voltage
    }
}

// Target interface for Adapter
interface SocketAdapter {
    Volt get3Volt();
    Volt get12Volt();
    Volt get120Volt();
}

// Class Adapter (extends Socket + implements SocketAdapter)
class SocketAdapterImpl extends Socket implements SocketAdapter {

    @Override
    public Volt get3Volt() {
        return convertVolt(getVolt(), 40); // 120/40 = 3V
    }

    @Override
    public Volt get12Volt() {
        return convertVolt(getVolt(), 10); // 120/10 = 12V
    }

    @Override
    public Volt get120Volt() {
        return getVolt(); // unchanged
    }

    private Volt convertVolt(Volt v, int divisor) {
        return new Volt(v.getVolts() / divisor);
    }
}

// Main class
public class MobileAdapterDemo {
    public static void main(String[] args) {

        SocketAdapter adapter = new SocketAdapterImpl();

        System.out.println("Default Voltage: " + adapter.get120Volt().getVolts() + "V");
        System.out.println("3 Volt: " + adapter.get3Volt().getVolts() + "V");
        System.out.println("12 Volt: " + adapter.get12Volt().getVolts() + "V");
    }
}
