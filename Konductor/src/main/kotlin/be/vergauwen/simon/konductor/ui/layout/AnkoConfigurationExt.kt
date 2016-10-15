package be.vergauwen.simon.konductor.ui.layout

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.res.Configuration
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.Orientation
import org.jetbrains.anko.ScreenSize
import org.jetbrains.anko.UiMode
import org.jetbrains.anko.internals.AnkoInternals
import timber.log.Timber


/**
 * Execute [f] if the device configuration matches all given constraints.
 * You can use named arguments to provide only the relevant constraints.
 * All null constraints are ignored.
 *
 * @param screenSize the required screen size.
 * @param density the required screen density.
 * @param language the required system language.
 * @param orientation the current screen orientation.
 * @param long true, if the screen layout is long. See [Configuration.SCREENLAYOUT_LONG_YES] for more information.
 * @param fromSdk the minimal SDK version for code to execute.
 * @param sdk the target SDK version. Code will not be executed if the device Android SDK version is different
 *        (lower or higher than the given value).
 * @param uiMode the required interface mode.
 * @param nightMode true, if the device should be in the night mode, false if should not.
 * @param rightToLeft true, if the device locale should be a right-to-left one, false if should not.
 * @param smallestWidth the required smallest width of the screen.
 * @param smallestWidthInInch the required smallest width in inch of the physical screen
 * @param largestWidthInInch the largest width in inch of the physical screen
 */
inline fun <T : Any> Context.configuration(
        screenSize: ScreenSize? = null,
        density: ClosedRange<Int>? = null,
        language: String? = null,
        orientation: Orientation? = null,
        long: Boolean? = null,
        fromSdk: Int? = null,
        sdk: Int? = null,
        uiMode: UiMode? = null,
        nightMode: Boolean? = null,
        rightToLeft: Boolean? = null,
        smallestWidth: Int? = null,
        smallestWidthInInch: Float? = null,
        largestWidthInInch: Float? = null,
        f: () -> T
): T? = if (testConfiguration(this, screenSize, density, language, orientation, long,
        fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth, smallestWidthInInch,largestWidthInInch)) f() else null

inline fun <T : Any> Activity.configuration(
        screenSize: ScreenSize? = null,
        density: ClosedRange<Int>? = null,
        language: String? = null,
        orientation: Orientation? = null,
        long: Boolean? = null,
        fromSdk: Int? = null,
        sdk: Int? = null,
        uiMode: UiMode? = null,
        nightMode: Boolean? = null,
        rightToLeft: Boolean? = null,
        smallestWidth: Int? = null,
        smallestWidthInInch: Float? = null,
        largestWidthInInch: Float? = null,
        f: () -> T
): T? = if (testConfiguration(this, screenSize, density, language, orientation, long,
        fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth, smallestWidthInInch,largestWidthInInch)) f() else null

inline fun <T : Any> AnkoContext<*>.configuration(
        screenSize: ScreenSize? = null,
        density: ClosedRange<Int>? = null,
        language: String? = null,
        orientation: Orientation? = null,
        long: Boolean? = null,
        fromSdk: Int? = null,
        sdk: Int? = null,
        uiMode: UiMode? = null,
        nightMode: Boolean? = null,
        rightToLeft: Boolean? = null,
        smallestWidth: Int? = null,
        smallestWidthInInch: Float? = null,
        largestWidthInInch: Float? = null,
        f: () -> T
): T? = if (testConfiguration(ctx, screenSize, density, language, orientation, long,
        fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth, smallestWidthInInch,largestWidthInInch)) f() else null

inline fun <T : Any> Fragment.configuration(
        screenSize: ScreenSize? = null,
        density: ClosedRange<Int>? = null,
        language: String? = null,
        orientation: Orientation? = null,
        long: Boolean? = null,
        fromSdk: Int? = null,
        sdk: Int? = null,
        uiMode: UiMode? = null,
        nightMode: Boolean? = null,
        rightToLeft: Boolean? = null,
        smallestWidth: Int? = null,
        smallestWidthInInch: Float? = null,
        largestWidthInInch: Float? = null,
        f: () -> T
): T? {
    val act = activity
    return if (act != null) {
        if (testConfiguration(act, screenSize, density, language, orientation, long,
                fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth, smallestWidthInInch,largestWidthInInch)) f() else null
    } else null
}

fun testConfiguration(
        ctx: Context,
        screenSize: ScreenSize?,
        density: ClosedRange<Int>?,
        language: String?,
        orientation: Orientation?,
        long: Boolean?,
        fromSdk: Int?,
        sdk: Int?,
        uiMode: UiMode?,
        nightMode: Boolean?,
        rightToLeft: Boolean?,
        smallestWidth: Int?,
        smallestWidthInInch: Float?,
        largestWidthInInch: Float?
): Boolean {
    if (AnkoInternals.testConfiguration(ctx, screenSize, density, language, orientation, long, fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth)) {
        if (smallestWidthInInch != null) {
            Timber.e("smallestWidthInInch <= ctx.screenSize().width: $smallestWidthInInch <= ${ctx.screenSize().width}")
            if (smallestWidthInInch <= ctx.screenSize().width) {
                return true
            } else {
                return false
            }
        }

        if (largestWidthInInch != null){
            Timber.e("largestWidthInInch > ctx.screenSize().width: $largestWidthInInch < ${ctx.screenSize().width}")
            if (largestWidthInInch > ctx.screenSize().width){
                return true
            } else{
                return false
            }
        }
        return true
    }
    return false
}