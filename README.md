
# JConditions [![Build Status](https://travis-ci.org/vbauer/jconditions.svg)](https://travis-ci.org/vbauer/jconditions) [![Coverage Status](https://coveralls.io/repos/vbauer/jconditions/badge.svg?branch=master)](https://coveralls.io/r/vbauer/jconditions?branch=master) [![Maven](https://img.shields.io/github/tag/vbauer/jconditions.svg?label=maven)](https://jitpack.io/#vbauer/jconditions)

> There is an exception to every rule.

**JConditions** is an extension for [JUnit](http://junit.org) framework, which allows to mark test methods with some specific conditional annotations.
It helps to keep clean your test method's code and prevents a lot of unnecessary code (with [Assume](http://junit.sourceforge.net/javadoc/org/junit/Assume.html) class).


## 10 second example

A picture paints a thousand words:

```java
@RunWith(ConditionTestRunner.class)
public class ExampleTest {
    @Test
    @RunningOnOS(LINUX)
    public void testRunningOnOS() throws Exception {
        assertTrue(exec("some-linux-program"));
    }    
    @Test
    @IfJavaVersion(JAVA_8)
    public void testIfJavaVersion8() {
        // Check some Java 8 specific code
    }   
}
```


## Features

**JConditions** has the following conditional annotations:

<ul>
    <li><a href="#appisinstalled">@AppIsInstalled</a></li>
    <li><a href="#existsonfs">@ExistsOnFS</a></li>
    <li><a href="#hasclass">@HasClass</a></li>
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
        <version>1.0.0</version>
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
    testCompile 'com.github.vbauer:jconditions:1.0.0'
}
```


## Annotations

Some things that you need to know before exploring an examples:

* Each described annotation has a simple example how to use it. Examples are very simple and existed only to show the idea of usage.
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
* **type** - type of FS element (FILE / DIRECTORY).

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
**@IfJavaVersion** checks that test is run on the specific version of JVM.

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

**@IfScript** allows to write custom conditional rules using [JSR223: JSR 223: Scripting for the JavaTM Platform](https://www.jcp.org/en/jsr/detail?id=223).
JavaScript engine is available by default (it is part of JVM).
All other JSR233-compatible languages will be included automatically if they are available in classpath.

Available parameters:

* **values** - script or scripts that should be executed. Return value will be converted to boolean type (even `String` and `Number`s).
* **engine** - type of script engine (default value is "js").

Parameters which are available in script context:

* **test** - current instance of running test.
* **env** - environment variables (`System.getenv()`).
* **props** - system properties (`System.getProperties()`).
* **console** - console object (`System.console()`).
 
```java
@Test
@IfScript("test.isSatisfiedInnerCheck")
public void testIfScriptNegative() {
    Assert.fail();
}
```

### @IgnoreIf
### @PropertyIsDefined
### @ResourceIsAvailable
### @RunIf
### @RunningOnOS
### @SocketIsOpened
### @UrlIsReachable


## Building from source

JConditions uses Maven for its build. To build project, run:

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
