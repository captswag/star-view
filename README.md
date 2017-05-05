# 🌟 StarView

[ ![Download](https://api.bintray.com/packages/4k3r/StarView/com.anjith.starview/images/download.svg) ](https://bintray.com/4k3r/StarView/com.anjith.starview/_latestVersion)

🎉 A super simple rating star view for Android! 🎉

StarView accepts ratings from 0.0 - 10.0 with 0 being the least ⭐ & 10 being the highest 🌟. StarView will render bordered star, half filled star or full filled star based on the rating that is set. StarView has 3 levels of rating and accepts double value.

- Any rating less than 3.3 is considered as low rating.
- Any rating between 3.3 and 6.6 inclusive of both is considered as medium rating.
- Any rating greater than 6.6 is considered as high rating.

## How to use

**1. Add gradle dependency**

```gradle
dependencies {
    compile 'com.anjith.starview:starview:1.0.1'
}
```

**2. Add view to layout**

```xml
<com.anjith.starview.StarView
    android:id="@+id/star_view"
    android:layout_width="22dp"
    android:layout_height="22dp"
    app:rating="3.4"/>
```

**3. Usage**

```java
StarView starView = (StarView) findViewById(R.id.star_view);
starView.setRating(9.3);
```

That's it.

## Spread the word

If you like this library, please tell others about it 🌟. Contributions are always welcome. 

- Visit my [**website**](http://www.anjithsasindran.in)
- Contact me on [**LinkedIn**](http://in.linkedin.com/in/anjithsasindran)

## MIT License

StarView is released under MIT license. See file LICENSE