# AString
[![Build][build-shield]][build]
[![Download][download-shield]][download]
[![API][api-shield]][api]
###### A context aware parcelable string abstraction for _Android_

    interface AString extends Parcelable {
        fun invoke(context: Context): CharSequence?
    }

The library is implemented with _Kotlin_ for _Java_, thus
the _Kotlin Standard Library_ is not required to use _AString_.


## Installation

All packages are available in _Maven Central_.
Once a dependency is declared with a specific version,
every other dependency version is provided implicitly with a BOM.

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation "xyz.tynn.astring:astring:$astringVersion"
    }


## Usage

The `AStringFactory` provides functions to create all basic `AString`
implementations.

    AString.Null
    "aString".asAString()
    StringResource(R.string.app_name)

    AStringFactory.createFromCharSequence("aString")

If `null`, `ID_NULL` or `0` is used to create an `AString`,
the `AString.Null` singleton is returned.

The `AString` itself should be used in place of a `CharSequence`,
just like string resource would be used.
In _Kotlin_ this is simplified with extension functions.

    textView.setText(aString)

    textView.setText(aString.invoke(textView.context))
    AStringTextView.setText(textView, aString)


### `AString` wrappers and delegates

There are several _standard_ implementations of `AString` for:

 * `CharSequence?` wrapper

 * `getString()` and `getQuantityString()` delegates
 * `getText()` and `getQuantityText()` delegates

 * the `getPackageName()` delegate
 * the `PackageInfo.versionName` resolver


### `AString` string format and `toString()` wrapper

While `AString` represent any generic `CharSequence`, it might be useful to use
it as a `String` only or even use it as a format string.

    aString.format(1, "2")
    aString.format(Locale.UK, 1, "2")

    aString.mapToString()

This behavior is similar to the approaches taken for `getText(int)`,
`getString(int)` and `getString(int, Object...)` string resource accessors.


### _Jetpack_ Compose
[![API][compose-shield]][compose]

Within a `@Composable` function the `asString()` extension functions
automatically converts an `AString` to a `String`.

    Text(text = aString.asString())

The `@ExperimentalTextApi asAnnotatedString()` extension functions converts
an `AString` to an `AnnotatedString`, including as many built-in spans as
possible.

    Text(text = aString.asAnnotatedString())

#### Artifact

    dependencies {
        implementation 'xyz.tynn.astring:compose'
    }


### _Kotlin_ extension functions

There are several extension functions for widget methods taking a
`CharSequence?` as an argument.
_Core_, _AppCompat_ and _Material_ components should be fully covered.

#### Artifacts

    dependencies {
        implementation 'xyz.tynn.astring:core'
        implementation 'xyz.tynn.astring:appcompat'
        implementation 'xyz.tynn.astring:material'
    }


### _Android_ Data Binding

Some of the extension functions are annotated with `@BindingAdapter` and
therefore provide simple setters for one-way data binding.

    android:text="@{aString}"

Since `AString` is not directly intended as input method, these functions
are not optimized for two-way data binding and should not used in that context. 
To support two-way data binding use the `AStringBinding` utility instead.

    android:text="@={AStringBinding.load(context, aString)}"

#### Artifact

    dependencies {
        implementation 'xyz.tynn.astring:binding'
    }


### Testing

Custom `AString` implementations require an efficient `Parcelable`
implementation. To simplify testing these, the `AStringAssert` provides
static assertions for different levels of efficients.

    assertParcelableAStringIdentity(aString)
    assertParcelableAStringEquality(aString)
    assertParcelableAStringInvocation(aString)

While testing for equality or identity ensures that both instances are equal or
the same, `assertParcelableAStringInvocation()` ensures that at least the
result of the recovered `AString` is equal to the original result.

#### Artifact

    dependencies {
        androidTestImplementation textFixtures('xyz.tynn.astring:astring')
    }


## License

    Copyright (C) 2020-2023 Christian Schmitz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


  [api]: https://asapi.gigalixirapp.com/xyz.tynn.astring/astring
  [api-shield]: https://img.shields.io/endpoint?url=https://asapi.gigalixirapp.com/xyz.tynn.astring/astring@json
  [build]: https://github.com/tynn-xyz/AString/actions
  [build-shield]: https://img.shields.io/github/actions/workflow/status/tynn-xyz/AString/build.yml
  [compose]: https://asapi.gigalixirapp.com/xyz.tynn.astring/compose
  [compose-shield]: https://img.shields.io/endpoint?url=https://asapi.gigalixirapp.com/xyz.tynn.astring/compose@json
  [download]: https://search.maven.org/search?q=xyz.tynn.astring
  [download-shield]: https://img.shields.io/maven-central/v/xyz.tynn.astring/core
