
# JConditions

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-JConditions-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2414)
[![Build Status](https://travis-ci.org/vbauer/jconditions.svg)](https://travis-ci.org/vbauer/jconditions)
[![Coverage Status](https://coveralls.io/repos/vbauer/jconditions/badge.svg?branch=master)](https://coveralls.io/r/vbauer/jconditions?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/562e9efb36d0ab001600163a/badge.svg?style=flat)](https://www.versioneye.com/user/projects/562e9efb36d0ab001600163a)
[![Maven](https://img.shields.io/github/tag/vbauer/jconditions.svg?label=maven)](https://jitpack.io/#vbauer/jconditions)

<img align="right" style="margin-left: 15px" width="200" height="200" src="misc/logo.png" />

> There is an exception to every rule.

**JConditions** is an extension for [JUnit](http://junit.org) framework, which allows to mark test methods with specific conditional annotations.
This annotations allow to permit or restrict execution of test methods.
It helps to keep clean your test methods using declarative programming and prevents a lot of unnecessary code (see [Assume](http://junit.sourceforge.net/javadoc/org/junit/Assume.html) class).


## 10 second example

A picture paints a thousand words:

```java
@RunWith(ConditionTestRunner.class)
public class ExampleTest {
    @Test
    @RunningOnOS(LINUX)
    public void testRunningOnOS() throws Exception {
        // Check some Linux app
        assertTrue(exec("some-linux-program"));
    }    
    @Test
    @IfJavaVersion(JAVA_8)
    public void testIfJavaVersion8() {
        // Check some Java 8 specific code
        assertTrue(com.foo.Java8Class.isCorrect());
    }   
}
```


## Features

**JConditions** has the following conditional annotations:

<ul>
    <li><a href="#appisinstalled">@AppIsInstalled</a></li>
    <li><a href="#existsonfs">@ExistsOnFS</a></li>
    <li><a href="#hasclass">@HasClass</a></li>
    <li><a href="#hasfreespace">@HasFreeSpace</a></li>
    <li><a href="#haspackage">@HasPackage</a></li>
    <li><a href="#ifjavaversion">@IfJavaVersion</a></li>
    <li><a href="#ifscript">@IfScript</a></li>
    <li><a href="#ignoreif">@IgnoreIf</a></li>
    <li><a href="#propertyisdefined">@PropertyIsDefined</a></li>
    <li><a href="#resourceisavailable">@ResourceIsAvailable</a></li>
    <li><a href="#runif">@RunIf</a></li>
    <li><a href="#runningonos">@RunningOnOS</a></li>
    <li><a href="#socketisopened">@SocketIsOpened</a></li>
    <li><a href="#urlisreachable">@UrlIsReachable</a></li>
</ul>

This annotations could be used with test methods or/and classes (it will allow to run checks before each test method).
You can also write <a href="#custom-annotations">custom annotations</a> or make <a href="#composite-annotations">composite annotations</a>. 

## Setup

**JConditions** uses [JitPack.io](https://jitpack.io) for distribution, so
you need to configure JitPack's Maven repository to fetch artifacts (dependencies).

### Maven

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependencies>
    <dependency>
        <groupId>com.github.vbauer</groupId>
        <artifactId>jconditions</artifactId>
        <version>1.2.0</version>
    </dependency>
</dependencies>
```

### Gradle

```groovy
repositories {
    mavenCentral()
    maven {
        url 'https://jitpack.io'
    }
}

dependencies {
    testCompile 'com.github.vbauer:jconditions:1.2.0'
}
```


## Annotations

Some things that you need to know before exploring an examples:

* Each described annotation has a simple example how to use it. Examples are very simple, they are existed only to show the main idea of usage.
* Most of them could use injections to insert environment variables. This injections/substitutions could be useful to parametrize tests or to organize profiles.
**EX:** "${java.io.tmpdir}/test.html"

### @AppIsInstalled
**@AppIsInstalled** checks that specified application(s) is installed.
It could be useful when developed application has an optional integrations with some external tools/apps and 
it will be good to check this integrations in tests.

```java
@Test
@AppIsInstalled({ "ls", "uname" })
public void testAppIsInstalled() throws Exception {
    exec("ls");
    exec("uname");
}
```

### @ExistsOnFS
**@ExistsOnFS** checks that specified file or directory is existed on file system.
It is also possible to configure multiple values.

Available parameters:

* **value** - file(s) or directory(ies).
* **type** - type(s) of FS element (FILE / DIRECTORY / SYMLINK).

```java
@Test
@ExistsOnFS("pom.xml")
public void testFileExists() throws Exception {
    Assert.assertTrue(FSUtils.fileExists("pom.xml"));
}
```

### @HasClass
**@HasClass** checks that specified class(es) is available in classpath.
It could be useful to check integration with optional features.

```java
@Test
@HasClass("org.junit.Assert")
public void testHasClass() throws Exception {
    Assert.assertNotNull(Class.forName("org.junit.Assert"));
}
```

### @HasFreeSpace
**@HasFreeSpace** checks that FS element(s) / disk(s) has free space.
It could be useful to check if it is possible to:

* download some big some file from remote server
* generate and store on FS some data

Available parameters:

* **value** - disk or disks that should be checked (ex: "C:\\").
* **min** - minimum amount of available free space on disk in bytes.
* **max** - maximum amount of available free space on disk in bytes.

```java
@Test
@HasFreeSpace(value = { "/", "C:\\" }, min = 1024)
public void testHasFreeSpace() {
    // Download some file
}
```

### @HasPackage
**@HasPackage** checks that specified package(s) is available in classpath.
It could be useful to check integration with optional features.

```java
@Test
@HasPackage("org.junit")
public void testHasPackage() throws Exception {
    Assert.assertNotNull(Package.getPackage("org.junit"));
}
```

### @IfJavaVersion
**@IfJavaVersion** checks that test is run on the specific version(s) of JVM.

**EX:** Some features could use external libraries and work only for the specific version of JVM.
It is possible to check it, if you use full names for classes (class loader will load them in runtime).

```java
@Test
@IfJavaVersion(IfJavaVersion.JAVA_8)
public void testIfJavaVersion8() {
    Assert.assertTrue(IfJavaVersionChecker.javaVersion().contains("8"));
    // Javaslang project works only on Java 8
    Assert.assertNotNull(javaslang.Tuple0.instance());
}
```

### @IfScript

**@IfScript** allows to write custom conditional rules using [JSR 223: Scripting for the JavaTM Platform](https://www.jcp.org/en/jsr/detail?id=223).
JavaScript engine is available by default (it is part of JVM).
All other JSR233-compatible languages will be included automatically if they are available in classpath.

Available parameters:

* **values** - script or scripts that should be executed. Return value will be converted to boolean type (even `String` and `Number`s).
* **engine** - type of script engine (default value is `"js"`).
* **context** - context provider which provides an extra data in script as `"context"` variable.


Parameters which are available in script context:

* **test** - current instance of running test class.
* **env** - environment variables (`System.getenv()`).
* **props** - system properties (`System.getProperties()`).
* **console** - console object (`System.console()`).
* **context** - extra data which could be created using `context` provider.
 
```java
@Test
@IfScript("test.isSatisfiedInnerCheck")
public void testIfScriptNegative() {
    Assert.fail();
}
```

### @IgnoreIf
**@IgnoreIf** allows to skip some test method using specific `ConditionChecker` class.
It will skip test, if checker return true and execute method otherwise.
`ConditionChecker` could be separate class, or nested static class, or even inner class.
It also works fine with private classes.

```java
@Test
@IgnoreIf(Always.class)
public void testIgnoreIfAlways() {
    Assert.fail();
}
```

### @PropertyIsDefined

**@PropertyIsDefined** checks if environment or system property is defined.
 It could be useful to simulate test profiles or to check some cases which are dependent on them (and could be optionals).
 
 Available parameters:
 
 * **keys** - keys of environment or system variables
 * **values** - values that should correspond to keys (optional parameter).

```java
@Test
@PropertyIsDefined(keys = "os.name")
public void testEnvVarIsDefined() {
    Assert.assertNotNull(PropUtils.getSystemProperty("os.name"));
}
```

### @ResourceIsAvailable

**@ResourceIsAvailable** allows to minimize code which is necessary to download some document/file via HTTP/HTTPS.
It is also possible to cache downloaded resource between test executions, otherwise it will be remove after test.

Available parameters:

* **source** - HTTP/HTTPS file or document.
* **target** - path to file where content should be saved.
* **cache** - flag to configure cache option.
* **timeout** - maximum time for connection to the `source` (default value is 10sec).

```java
@Test
@ResourceIsAvailable(
    source = "http://apple.com",
    target = "${java.io.tmpdir}/apple-homepage.html",
    cache = false
)
public void testResourceIsAvailable() {
    final String path = PropUtils.injectProperties("${java.io.tmpdir}/apple-homepage.html");
    Assert.assertTrue(FSUtils.fileExists(path));
}
```

### @RunIf

**@RunIf** is an opposite annotation to **@IgnoreIf**. It will run test method if `ConditionChecker` returns `true`.

```java
@Test
@RunIf(SomeInnerClassCheck.class)
public void testInnerClass() {
    Assert.fail();
}
```

### @RunningOnOS
**@RunningOnOS** checks the current operation system and runs test method only when it is specified and `value` parameter.
It is also possible to configure multiple variants (to run test method, even one of them should be fine).

```java
@Test
@RunningOnOS({
    RunningOnOS.LINUX,
    RunningOnOS.MAC,
})
public void testRunningOnOS() throws Exception {
    Assert.assertTrue(exec("ls"));
}
```

### @SocketIsOpened
**@SocketIsOpened** checks that specified socket is opened.

Available parameters:

* **host** - host address (default value is "0.0.0.0").
* **port** - socket's port number.
* **timeout** - maximum time for connection to the socket (default value is 10sec).

```java
@Test
@SocketIsOpened(host = "apple.com", port = 80)
public void testSocketIsOpened() throws Exception {
    checkSite("http://apple.com");
}
```

### @UrlIsReachable

**@UrlIsReachable** checks that specified URL address is reachable (available via `URLConnection`).
It also possible to configure multiple URLs.

Available parameters:

* **value** - URL address(s) that should be checked.
* **timeout** - maximum timeout for URL connection (default value is 10sec).

```java
@Test
@UrlIsReachable("http://apple.com")
public void testUrlIsReachable() throws Exception {
    checkSite("http://apple.com");
}
```


## Custom annotations
It is possible use `@IfRun` or `@IgnoreIf` to run custom `ConditionalChecker`,
but it is also possible to write your own annotation (like `@HasPackage` or `@IfScript`).

All JConditions out-of-box annotations was created using unified extension mechanism.
This mechanism is centered around 3 main things:

* Custom annotation which can has additional parameters that could be used as input data.
* Conditional checker which make decision to permit or restrict running of test method.
* `@Condition` annotation which allows to glue custom annotation and conditional checker.

Let's write an annotation which emulates standard JUnit's `@org.junit.Ignore`:

```java
@Condition(IgnoreItChecker.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD })
public @interface IgnoreIt {
}

public class IgnoreItChecker implements ConditionChecker<IgnoreIt> {
    @Override
    public boolean isSatisfied(final CheckerContext<IgnoreIt> context) {
        return false;
    }

}
```

That's all! Now, you can mark test classes or test methods with `@IgnoreIt` to skip test(s).


## Composite annotations

Sometimes it could be useful to have possibility to resolve the following cases (to prevent unnecessary code):

* Specify all needed parameter for some existed annotation and do not copy-paste them.
* Glue some conditional annotations into one annotation.

It is possible, because JConditions extension mechanism resolves all hierarchy of classes and annotations.

Let's make an annotation which allows to detect if our MySQL database works fine:

```java
@SocketIsOpened(host = "localhost", port = 3306)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface MySQLWorks {
}
```

Let's make an annotation which allows to run tests only on Linux machines with Java 8:

```java
@RunningOnOS(LINUX)
@IfJavaVersion(JAVA_8)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface OnLinuxWithJava8 {
}
```


## Building from source

JConditions uses [Maven](https://maven.apache.org) for its build. To build project, run:

```bash
mvn clean install -P strict
```

from the root of the project directory.
Profile `strict` is necessary to check code style and to run static code analysis.


## Might also like

* [jackdaw](https://github.com/vbauer/jackdaw) - Java Annotation Processor which allows to simplify development.
* [caesar](https://github.com/vbauer/caesar) - Library that allows to create async beans from sync beans.
* [houdini](https://github.com/vbauer/houdini) - Type conversion system for Spring framework.
* [herald](https://github.com/vbauer/herald) - Logging annotation for Spring framework.
* [commons-vfs2-cifs](https://github.com/vbauer/commons-vfs2-cifs) - SMB/CIFS provider for Commons VFS.
* [avconv4java](https://github.com/vbauer/avconv4java) - Java interface to avconv tool.


## License

Copyright 2015 Vladislav Bauer

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

See [LICENSE](LICENSE) file for details.
