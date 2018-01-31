package apps.shark.guestmode;

import android.graphics.drawable.Drawable;

/**
 * Created by Harsha on 1/31/2018.
 */

public class AppList {

    private String name;
    Drawable icon;
    public String packageName;

    public AppList(String name, Drawable icon, String  packageName) {
        this.name = name;
        this.icon = icon;
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getPackageName() { return packageName; }
}