package com.example.reproduce_robo_autocomplete

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import radiography.Radiography

private const val VIEW_TAG = "autoCompleteTextView"

@RunWith(AndroidJUnit4::class)
class AutoCompleteTextViewAndroidTest {
    @Test
    fun autoCompleteList_whenItemClicked_setsText() {
        val scenario = launchFragmentInContainer<TestFragment>()

        onView(withTagValue(Matchers.equalTo(VIEW_TAG)))
            .perform(typeText("Bel"))

        onView(withTagValue(Matchers.equalTo(VIEW_TAG)))
            .check(matches(withText("Bel")))

        scenario.onFragment {
            val underTest: AutoCompleteTextView = it.view!!.findViewWithTag(VIEW_TAG)
            Assert.assertEquals(1, underTest.adapter.count)
        }

        println("View after popup " + Radiography.scan())

        onData(AutoCompleteResultWithItem("Belgium"))
            .inRoot(isPlatformPopup())
            .perform(click())

        onView(withTagValue(Matchers.equalTo(VIEW_TAG)))
            .check(matches(withText("Belgium")))
    }

    class TestFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val container = FrameLayout(requireContext())
            container.background = ColorDrawable(Color.valueOf(0f, 1f, 1f).toArgb())
            val adapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                arrayOf("Belgium", "France", "Italy", "Germany", "Spain")
            )
            val textView = AutoCompleteTextView(context)
            textView.tag = VIEW_TAG
            textView.setAdapter(adapter)
            textView.isEnabled = true
            textView.minWidth = 400
            textView.minHeight = 200

            container.addView(
                textView,
                FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .apply {
                        this.gravity = Gravity.CENTER
                    })
            return container
        }
    }

    class AutoCompleteResultWithItem(private val targetItem: String) : BaseMatcher<String>() {
        override fun describeTo(description: Description) {
            description.appendText("Auto Complete result with item: $targetItem")
        }

        override fun matches(item: Any): Boolean {
            return item == targetItem
        }
    }
}
