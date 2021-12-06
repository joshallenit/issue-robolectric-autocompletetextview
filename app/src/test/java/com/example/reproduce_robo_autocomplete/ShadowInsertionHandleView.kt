package com.example.reproduce_robo_autocomplete

import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject
import org.robolectric.shadow.api.Shadow
import org.robolectric.shadows.ShadowView

/**
 * Work around for
 * [Crash when clicking or typing on text in an EditText with text](https://github.com/robolectric/robolectric/issues/5345)
 */
@Implements(className = "android.widget.Editor\$InsertionHandleView")
class ShadowInsertionHandleView : ShadowView() {
    @RealObject
    private lateinit var realInsertionHandleView: Object

    @Suppress("unused", "ProtectedInFinal") // Used by Robolectric and protected recommended to limit api surface.
    @Implementation
    protected fun getCurrentCursorOffset(): Int {
        return try {
            Shadow.directlyOn(
                realInsertionHandleView,
                "android.widget.Editor\$InsertionHandleView",
                "getCurrentCursorOffset"
            )
        } catch (ex: Exception) {
            0
        }
    }
}
