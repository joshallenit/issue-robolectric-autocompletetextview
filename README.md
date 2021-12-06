# Demonstrates Issue with Robolectric

This example project has a simple test that clicks on the auto-complete dropdown of an AutoCompleteTextView

Robolectric Issue #6912: [AutoCompleteTextView and Robolectric Limitations](https://github.com/robolectric/robolectric/issues/6912)

## Android Test

- Works correctly

```
View after popup com.example.reproduce_robo_autocomplete/androidx.fragment.app.testing.FragmentScenario$EmptyFragmentActivity:
window-focus:true
 DecorView { 1080×2220px }
 ├─ActionBarOverlayLayout { id:decor_content_parent, 1080×2220px }
 │ ├─FrameLayout { id:content, 1080×2022px }
 │ │ ╰─FrameLayout { 1080×2022px }
 │ │   ╰─AutoCompleteTextView { 400×200px, focused, text-length:3, ime-target }
 │ ├─ActionBarContainer { id:action_bar_container, 1080×132px }
 │ │ ├─ActionBarView { id:action_bar, 1080×132px }
 │ │ │ ├─LinearLayout { 798×132px, disabled }
 │ │ │ │ ├─HomeView { 110×132px }
 │ │ │ │ │ ├─ImageView { id:up, GONE, 0×0px }
 │ │ │ │ │ ╰─ImageView { id:home, 88×88px }
 │ │ │ │ ╰─LinearLayout { 688×67px }
 │ │ │ │   ├─TextView { id:action_bar_title, 666×67px, text-length:27 }
 │ │ │ │   ╰─TextView { id:action_bar_subtitle, GONE, 0×0px, text-length:0 }
 │ │ │ ╰─ActionMenuView { 0×132px }
 │ │ ╰─ActionBarContextView { id:action_context_bar, GONE, 0×0px }
 │ ╰─ActionBarContainer { id:split_action_bar, GONE, 0×0px }
 ╰─View { id:statusBarBackground, 1080×66px }
PopupWindow:235edaf:
window-focus:false
 PopupDecorView { 400×210px }
 ╰─PopupBackgroundView { 400×210px }
   ╰─DropDownListView { 378×176px, focused }
     ╰─TextView { id:text1, 378×176px, text-length:7 }
```

## Test

Same code is copied to /test and ran as Robolectric

- No popup is in the view hiearachy:

```
com.example.reproduce_robo_autocomplete/androidx.fragment.app.testing.FragmentScenario$EmptyFragmentActivity:
window-focus:true
 DecorView { 320×470px }
 ╰─ActionBarOverlayLayout { id:decor_content_parent, 320×470px }
   ├─FrameLayout { id:content, 320×422px }
   │ ╰─FrameLayout { 320×422px }
   │   ╰─AutoCompleteTextView { 320×200px, focused, text-length:3 }
   ├─ActionBarContainer { id:action_bar_container, 320×48px }
   │ ├─ActionBarView { id:action_bar, 320×48px }
   │ │ ├─LinearLayout { 75×48px }
   │ │ │ ├─HomeView { 40×48px }
   │ │ │ │ ├─ImageView { id:up, GONE, 0×0px }
   │ │ │ │ ╰─ImageView { id:home, 32×32px }
   │ │ │ ╰─LinearLayout { 35×41px }
   │ │ │   ├─TextView { id:action_bar_title, 27×41px, text-length:27 }
   │ │ │   ╰─TextView { id:action_bar_subtitle, GONE, 0×0px, text-length:0 }
   │ │ ╰─ActionMenuView { 0×48px }
   │ ╰─ActionBarContextView { id:action_context_bar, GONE, 0×0px }
   ╰─ActionBarContainer { id:split_action_bar, GONE, 0×0px }
```

And the test fails:

One interesting thing is that the the View Hierarchy in the stack trace is not in the View Hierarchy as reported by Radiography.

```
No views in hierarchy found matching: is assignable from class <class android.widget.AdapterView>

View Hierarchy:
+>PopupDecorView{id=-1, visibility=VISIBLE, width=80, height=64, has-focus=true, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params={(0,174)(80x64) gr=TOP START CENTER sim={state=unchanged} ty=APPLICATION_ABOVE_SUB_PANEL fmt=TRANSPARENT surfaceInsets=Rect(0, 0 - 0, 0) (manual)
  fl=NOT_FOCUSABLE LAYOUT_NO_LIMITS SPLIT_TOUCH HARDWARE_ACCELERATED FLAG_LAYOUT_ATTACHED_IN_DECOR
  pfl=WILL_NOT_REPLACE_ON_RELAUNCH LAYOUT_CHILD_WINDOW_IN_PARENT_FRAME
  bhv=DEFAULT
  fitTypes=STATUS_BARS NAVIGATION_BARS CAPTION_BAR}, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1} 
|
+->PopupBackgroundView{id=-1, visibility=VISIBLE, width=80, height=64, has-focus=true, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=android.widget.FrameLayout$LayoutParams@5bdb213c, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1} 
|
+-->LinearLayout{id=-1, visibility=VISIBLE, width=80, height=64, has-focus=true, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=android.widget.FrameLayout$LayoutParams@27927ccc, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1} 
|
+--->RelativeLayout{id=-1, visibility=VISIBLE, width=48, height=48, has-focus=true, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=true, is-focusable=true, is-layout-requested=false, is-selected=false, layout-params=android.widget.LinearLayout$LayoutParams@5e1cfe9a, tag=floating_toolbar, root-is-layout-requested=false, has-input-connection=false, x=16.0, y=8.0, child-count=1} 
|
+---->{id=-1, visibility=VISIBLE, width=48, height=48, has-focus=false, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=android.widget.RelativeLayout$LayoutParams@3b4b3d39, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1} 
|
+----->LinearLayout{id=-1, desc=Select all, visibility=VISIBLE, width=48, height=48, has-focus=false, has-focusable=true, has-window-focus=false, is-clickable=true, is-enabled=true, is-focused=false, is-focusable=true, is-layout-requested=false, is-selected=false, layout-params=android.widget.LinearLayout$LayoutParams@6c24b93a, tag=com.android.internal.widget.FloatingToolbar$MenuItemRepr@b49b0a05, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=2} 
|
+------>ImageView{id=16909017, res-name=floating_toolbar_menu_item_image, visibility=GONE, width=0, height=0, has-focus=false, has-focusable=false, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=true, is-selected=false, layout-params=android.widget.LinearLayout$LayoutParams@ee4f372, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0} 
|
+------>TextView{id=16909019, res-name=floating_toolbar_menu_item_text, visibility=VISIBLE, width=10, height=48, has-focus=false, has-focusable=false, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=android.widget.LinearLayout$LayoutParams@393772e4, tag=null, root-is-layout-requested=false, has-input-connection=false, x=19.0, y=0.0, text=Select all, input-type=0, ime-target=false, has-links=false} 
androidx.test.espresso.NoMatchingViewException: No views in hierarchy found matching: is assignable from class <class android.widget.AdapterView>

View Hierarchy:
+>PopupDecorView{id=-1, visibility=VISIBLE, width=80, height=64, has-focus=true, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params={(0,174)(80x64) gr=TOP START CENTER sim={state=unchanged} ty=APPLICATION_ABOVE_SUB_PANEL fmt=TRANSPARENT surfaceInsets=Rect(0, 0 - 0, 0) (manual)
  fl=NOT_FOCUSABLE LAYOUT_NO_LIMITS SPLIT_TOUCH HARDWARE_ACCELERATED FLAG_LAYOUT_ATTACHED_IN_DECOR
  pfl=WILL_NOT_REPLACE_ON_RELAUNCH LAYOUT_CHILD_WINDOW_IN_PARENT_FRAME
  bhv=DEFAULT
  fitTypes=STATUS_BARS NAVIGATION_BARS CAPTION_BAR}, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1} 
|
+->PopupBackgroundView{id=-1, visibility=VISIBLE, width=80, height=64, has-focus=true, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=android.widget.FrameLayout$LayoutParams@5bdb213c, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1} 
|
+-->LinearLayout{id=-1, visibility=VISIBLE, width=80, height=64, has-focus=true, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=android.widget.FrameLayout$LayoutParams@27927ccc, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1} 
|
+--->RelativeLayout{id=-1, visibility=VISIBLE, width=48, height=48, has-focus=true, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=true, is-focusable=true, is-layout-requested=false, is-selected=false, layout-params=android.widget.LinearLayout$LayoutParams@5e1cfe9a, tag=floating_toolbar, root-is-layout-requested=false, has-input-connection=false, x=16.0, y=8.0, child-count=1} 
|
+---->{id=-1, visibility=VISIBLE, width=48, height=48, has-focus=false, has-focusable=true, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=android.widget.RelativeLayout$LayoutParams@3b4b3d39, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=1} 
|
+----->LinearLayout{id=-1, desc=Select all, visibility=VISIBLE, width=48, height=48, has-focus=false, has-focusable=true, has-window-focus=false, is-clickable=true, is-enabled=true, is-focused=false, is-focusable=true, is-layout-requested=false, is-selected=false, layout-params=android.widget.LinearLayout$LayoutParams@6c24b93a, tag=com.android.internal.widget.FloatingToolbar$MenuItemRepr@b49b0a05, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0, child-count=2} 
|
+------>ImageView{id=16909017, res-name=floating_toolbar_menu_item_image, visibility=GONE, width=0, height=0, has-focus=false, has-focusable=false, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=true, is-selected=false, layout-params=android.widget.LinearLayout$LayoutParams@ee4f372, tag=null, root-is-layout-requested=false, has-input-connection=false, x=0.0, y=0.0} 
|
+------>TextView{id=16909019, res-name=floating_toolbar_menu_item_text, visibility=VISIBLE, width=10, height=48, has-focus=false, has-focusable=false, has-window-focus=false, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=android.widget.LinearLayout$LayoutParams@393772e4, tag=null, root-is-layout-requested=false, has-input-connection=false, x=19.0, y=0.0, text=Select all, input-type=0, ime-target=false, has-links=false} 
  at java.base/java.lang.Thread.getStackTrace(Thread.java:1607)
  at androidx.test.espresso.base.DefaultFailureHandler.getUserFriendlyError(DefaultFailureHandler.java:12)
  at androidx.test.espresso.base.DefaultFailureHandler.handle(DefaultFailureHandler.java:7)
  at androidx.test.espresso.ViewInteraction.waitForAndHandleInteractionResults(ViewInteraction.java:8)
  at androidx.test.espresso.ViewInteraction.desugaredPerform(ViewInteraction.java:11)
  at androidx.test.espresso.ViewInteraction.perform(ViewInteraction.java:4)
  at androidx.test.espresso.DataInteraction$DisplayDataMatcher$1.apply(DataInteraction.java:1)
  at androidx.test.espresso.DataInteraction$DisplayDataMatcher$1.apply(DataInteraction.java:2)
  at androidx.test.espresso.DataInteraction$DisplayDataMatcher.<init>(DataInteraction.java:7)
  at androidx.test.espresso.DataInteraction$DisplayDataMatcher.<init>(DataInteraction.java:13)
  at androidx.test.espresso.DataInteraction$DisplayDataMatcher.displayDataMatcher(DataInteraction.java:1)
  at androidx.test.espresso.DataInteraction.makeTargetMatcher(DataInteraction.java:2)
  at androidx.test.espresso.DataInteraction.perform(DataInteraction.java:1)
  at com.example.reproduce_robo_autocomplete.AutoCompleteTextViewTest.autoCompleteList_whenItemClicked_setsText(AutoCompleteTextViewTest.kt:65)
  at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  at java.base/java.lang.reflect.Method.invoke(Method.java:566)
  at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
  at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
  at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
  at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
  at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
  at org.robolectric.RobolectricTestRunner$HelperTestRunner$1.evaluate(RobolectricTestRunner.java:591)
  at org.robolectric.internal.SandboxTestRunner$2.lambda$evaluate$0(SandboxTestRunner.java:274)
  at org.robolectric.internal.bytecode.Sandbox.lambda$runOnMainThread$0(Sandbox.java:88)
  at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
  at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
  at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
  at java.base/java.lang.Thread.run(Thread.java:834)
```

## Question

Is Robolectric designed to work in this case? Or, is this a systemic limitation of Robolectric?

Thanks!
