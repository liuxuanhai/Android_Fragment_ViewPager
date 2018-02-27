# ViewPager + TabLayout + Fragment（禁止预加载）
在 ViewPager 和 Fragment 配合使用的时候，ViewPager 会使用预加载机制，使得我们在没有切换到到对应页面时，就已经加载好了，这是个非常不好的用户体验。所以所谓的懒加载就诞生了。

关键字： setUserVisibleHint

# Binaries

```groovy
implementation 'com.android.support:appcompat-v7:27.0.2'
implementation 'com.android.support:design:27.0.2'
implementation 'com.android.support:recyclerview-v7:27.0.2'
implementation 'com.android.support:cardview-v7:27.0.2'

implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
implementation 'io.reactivex.rxjava2:rxjava:2.1.10'

implementation 'com.squareup.retrofit2:retrofit:2.3.0'
implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'

implementation 'com.github.bumptech.glide:glide:4.6.1'
implementation 'com.github.bumptech.glide:compiler:4.6.1'
annotationProcessor 'com.github.bumptech.glide:compiler:4.6.1'
```

* RxJava: <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex.rxjava2%22%20a%3A%22rxjava%22'><img src='http://img.shields.io/maven-central/v/io.reactivex.rxjava2/rxjava.svg'></a>

* RxAndroid: <a href='http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex.rxjava2%22%20a%3A%22rxandroid%22'><img src='http://img.shields.io/maven-central/v/io.reactivex.rxjava2/rxandroid.svg'></a>

* Retrofit: <a href='http://search.maven.org/#artifactdetails%7Ccom.squareup.retrofit2%7Cretrofit-mock%7C2.3.0%7C'><img src='https://img.shields.io/maven-central/v/com.squareup.retrofit2/retrofit.svg'></a>

* Glide: <a href='http://search.maven.org/#artifactdetails%7Ccom.github.bumptech.glide%7Cglide%7C4.6.1%7C'><img src='https://img.shields.io/maven-central/v/com.github.bumptech.glide/glide.svg'></a>

Additional binaries and dependency information for can be found at [http://search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.reactivex.rxjava2%22%20a%3A%22rxandroid%22).
