package mc.sweng888.psu.edu.testfirebase;

import java.io.Serializable;

public class UserData implements Serializable {

    private String name;
    private String provider;

    public UserData(String name, String provider) {
        this.name = name;
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
