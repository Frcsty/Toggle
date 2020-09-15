package com.github.frcsty.toggle.handle.object;

import java.util.HashMap;
import java.util.Map;

public final class InformationPlayer {

    private final Map<String, Boolean> toggleStatus = new HashMap<>();

    public void withStatus(final String key, final boolean value) {
        this.toggleStatus.put(key, value);
    }

    public boolean getStatus(final String key) {
        return toggleStatus.get(key) == null ? false : toggleStatus.get(key);
    }

    public Map<String, Boolean> getData() {
        return this.toggleStatus;
    }
}
