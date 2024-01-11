# AString
[![Build][build-shield]][build]
[![Download][download-shield]][download]
[![API][api-shield]][api]
###### A context aware parcelable string abstraction for _Android_

    interface AString : Parcelable {
        fun invoke(context: Context): CharSequence?
    }

The main use-case is to provide a generic string data type for
inter-layer communication and state management. Therefore every
implementation should provide meaningful `equals` and `hashCode`
implementations to be efficient.

If an API does not guaranty this efficiency, it is be annotated with
the `@InefficientAStringApi` opt-in marker.

## Installation

All packages are available in _Maven Central_.
Once a single dependency is declared with a specific version,
the version for every other dependency is provided implicitly with a BOM.

    dependencies {
        implementation "xyz.tynn.astring:astring:$astringVersion"
    }

## Usage

    val Context.string by AString("value")

The `AString` itself should be used in place of a `CharSequence`,
just like string resource would be used by the framework.

    textView.setText(aString)

If `null`, `ID_NULL` or `0` are used to create an `AString` instance,
the `AString.Null` singleton is returned.

### `AString` Resources and Providers

Probably the most common use-case is providing string resources to the UI.
So there are equivalents for `getString()` and `getText()` provided by

    StringResource(R.string.res_id)
    StringResource(R.string.res_id, "arg")
    TextResource(R.string.res_id)

and equivalents for `getQuantityString()` and `getQuantityText()` provided by

    QuantityStringResource(R.plurals.res_id, 0)
    QuantityStringResource(R.plurals.res_id, 1, "arg")
    QuantityTextResource(R.plurals.res_id, 2)

While `AppId` or `AppVersion` just provide the data from the `Context` itself.

### `AString` Transformers

Since the `AString` represent any generic `CharSequence`, it might be useful
to transform the value before its use.

    aString.format("arg", AppId)
    aString.ifNull("value")
    aString.nullIfBlank()
    aString.mapToString()
    aString.trim()

### `AString` Reducers

If multiple instances of `AString` are provided, it is also possible to reduce
these to a single and single `AString`.

    aStrings.firstNonBlank()
    aStrings.joinNonNull(',')

The provided values of the `Iterable` are provided lazily.

### `@InefficientAStringApi AString` usages

Generic `AString.Provider`, `AString.Transformer` and `AString.Reducer` are
supported but do not provide comparability or readable string representations. 

    AString { it.packageName }
    aString.map { it?.toString() }
    aStrings.reduce { it.singleOrNull() }

### _Jetpack_ Compose extension functions
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

### _Android_ `View` widget extension functions

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

Some of the core extension functions are annotated with `@BindingAdapter`
and therefore provide simple setters for one-way data binding.

    android:text="@{aString}"

Since `AString` is not directly intended for input methods, these functions
are not optimized for two-way data binding and should not be used in that context.
To support two-way data binding use the `AStringBinding` utility instead.

    android:text="@={AStringBinding.load(context, aString)}"

#### Artifact

    dependencies {
        implementation 'xyz.tynn.astring:binding'
    }

### Testing

Custom `AString` implementations require an efficient `Parcelable`
implementation. To simplify testing these, the `AStringAssert` provides
static assertions for different levels of efficiency.

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

    Copyright (C) 2020-2024 Christian Schmitz

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
