package dk.dbc.mail;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, String> headers = new HashMap<String, String>();

    public Headers withHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public Map<String, String> build() {
        return headers;
    }
}
