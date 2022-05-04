# AString
[![Build][build-shield]][build]
[![Download][download-shield]][download]
[![API][api-shield]][api]
###### A context aware parcelable string abstraction for _Android_

```
public interface AString extends Parcelable {
    @Nullable
    CharSequence invoke(@NonNull Context context);
}
```

The library is implemented with _Kotlin_ for _Java_.
The _Kotlin Standard Library_ is not required to use _AString_.

## Installation

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation platform("xyz.tynn.astring:bom:$aStringVersion")

        implementation 'xyz.tynn.astring:core'
        implementation 'xyz.tynn.astring:appcompat'
        implementation 'xyz.tynn.astring:material'
    }


## Usage

To create a equatable `AString` instance use the `AStringFactory` methods in _Java_.

    AStringFactory.createFromCharSequence("aString")
    AStringFactory.createFromStringResource(R.string.app_name)

In Kotlin these methods are top level or extension functions.

    "aString".asAString()
     StringResource(R.string.app_name)

If `null`, `ID_NULL` or `0` is used to create an `AString`, an instance providing `null`
constantly is created.

    AStringFactory.nullAsAString

The `AString` itself should be used in place of a `CharSequence` or string resource.

    textView.setText(aString.invoke(textView.context))
    AStringTextView.setText(textView, aString)

In _Kotlin_ this is simplified with extension functions.

    textView.setText(aString)

### Supported `CharSequence` types

There are several _core_ implementations of `AString` for:

 * `null` wrapping
 * `CharSequence` wrapping

 * text resources delegation
 * string resources delegation
 * formatted string resources delegation

 * quantity text resources delegation
 * quantity string resources delegation
 * formatted quantity string resources delegation

### Supported _Android_ types

There are several (_Kotlin_) extension overloads for methods taking
a `CharSequence` as an argument.

_Feel free to provide a PR for missing methods._

#### Core module

Components provided by the _Android_ framework and _AndroidX_ core:

 `Activity`
 `View`
 `TextView`
 `Toobar`
 `Switch`
 `ToggleButton`
 `TextSwitcher`
 `Toast`
 `AlertDialog`
 `AlertDialog.Builder`

#### AppCompat module

Components provided by  _AndroidX_ appcompat:

 `Toobar`
 `AlertDialog`
 `AlertDialog.Builder`

#### Material module

Components provided by the _Material_ components by _Google_:

 `TextInputLayout`
 `Snackbar`
 `MaterialAlertDialogBuilder`


## License

    Copyright (C) 2020-2021 Christian Schmitz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


  [api]: https://asapi.herokuapp.com/xyz.tynn.astring/astring
  [api-shield]: https://asapi.herokuapp.com/xyz.tynn.astring/astring@svg
  [build]: https://github.com/tynn-xyz/AString/actions
  [build-shield]: https://img.shields.io/github/workflow/status/tynn-xyz/AString/Build
  [download]: https://search.maven.org/search?q=xyz.tynn.astring
  [download-shield]: https://img.shields.io/maven-central/v/xyz.tynn.astring/core
