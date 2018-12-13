package jarvis.com.nestedtouchscrollinglayout;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

/**
 * @author Jarvis.
 * @since 12-05-2018
 */
public class DisplayUtils {

    public static int getScreenWidthPixels(final Context pContext) {
        if (pContext == null) {
            return 0;
        }

        return pContext.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeightPixels(final Context pContext) {
        if (pContext == null) {
            return 0;
        }

        return pContext.getResources().getDisplayMetrics().heightPixels;
    }


    public static int getActionBarHeightPixels(final Context pContext) {
        final TypedValue typedValue = new TypedValue();

        if (pContext != null && pContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, pContext.getResources().getDisplayMetrics());
        }

        return 0;
    }

    public static boolean hasNavigationBar(final Context pContext) {
        final int id = pContext.getResources().getIdentifier("config_showNavigationBar", "bool", "android");

        if (id > 0) {
            return pContext.getResources().getBoolean(id);
        } else {
            return !ViewConfiguration.get(pContext).hasPermanentMenuKey() && !KeyCharacterMap.deviceHasKey(KeyEvent
                    .KEYCODE_BACK);
        }
    }

    public static int getNavigationBarHeight(final Context pContext) {
        if (!DisplayUtils.hasNavigationBar(pContext)) {
            return 0;
        }

        final Resources resources = pContext.getResources();

        final int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");

        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }

        return 0;
    }

    public static int pixelToDp(final Context pContext, final float pPixels) {
        if (pContext == null) {
            return 0;
        }

        final float density = pContext.getResources().getDisplayMetrics().density;

        return (int) ((pPixels / density) + 0.5);
    }

    public static int dpToPixel(final Context pContext, final float pDp) {
        if (pContext == null) {
            return 0;
        }

        final float density = pContext.getResources().getDisplayMetrics().density;

        return (int) ((pDp * density) + 0.5f);
    }

    public static int spToPixel(final Context pContext, final float pSp) {
        if (pContext == null) {
            return 0;
        }

        return (int) (pSp * pContext.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }
}