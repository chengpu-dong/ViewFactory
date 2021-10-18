# CustomViewFactory
some custom view for developer

## ClockView  (自定义时钟View)
- Effect achieved

![](https://github.com/chengpu-dong/CustomViewFactory/blob/main/app/src/main/res/raw/clock_view.gif)

- Support customized content


```
    <declare-styleable name="ClockView">
        <attr name="backgroundColor" format="color"/>
        <attr name="hoursHandColor" format="color"/>
        <attr name="minuteHandColor" format="color"/>
        <attr name="secondHandColor" format="color"/>
        <attr name="dialColor" format="color"/>
        <attr name="scaleColor" format="color"/>
        <attr name="scaleRadios" format="dimension"/>
        <attr name="scalePading" format="dimension"/>
        <attr name="anixRadio" format="dimension"/>
        <attr name="hourHandLength" format="dimension"/>
        <attr name="minuteHandLength" format="dimension"/>
        <attr name="secondHandLength" format="dimension"/>
        <attr name="hourHandWidth" format="dimension"/>
        <attr name="minuteHandWidth" format="dimension"/>
        <attr name="secondHandWidth" format="dimension"/>
    </declare-styleable>
```

## BubbleTextView （自定义气泡TextView）
- Effect achieved

![](https://github.com/chengpu-dong/CustomViewFactory/blob/main/app/src/main/res/drawable-xhdpi/bubble_text_view.png)

- Support customized content


```
    <declare-styleable name="bubble_text_view" tools:ignore="ResourceName">
        <attr name="bgColor" format="color" />
        <attr name="textColor" format="color" />
        <attr name="textSize" format="dimension" />
        <attr name="text" format="string" />
        <attr name="arrowLocation" format="enum">
            <enum name="top" value="0" />
            <enum name="right" value="1" />
            <enum name="bottom" value="2" />
            <enum name="left" value="3" />
        </attr>
        <attr name="perpendicularLength" format="dimension" />
        <attr name="bottomEdgeLength" format="dimension" />
        <attr name="cornerRadius" format="dimension" />
    </declare-styleable>
```
