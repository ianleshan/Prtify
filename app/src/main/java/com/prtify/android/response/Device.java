package com.prtify.android.response;

/**
 * Created by satya on 11/4/17.
 */

class Device {
    final String id;
    final boolean is_active;
    final boolean is_restricted;
    final String name;
    final String type;
    final int volume_percent;

    Device(String id, boolean is_active, boolean is_restricted, String name, String type, int volume_percent) {
        this.id = id;
        this.is_active = is_active;
        this.is_restricted = is_restricted;
        this.name = name;
        this.type = type;
        this.volume_percent = volume_percent;
    }

    public String getId() {
        return id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public boolean isIs_restricted() {
        return is_restricted;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getVolume_percent() {
        return volume_percent;
    }
}
