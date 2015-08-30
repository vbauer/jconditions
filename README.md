
# JConditions [![Build Status](https://travis-ci.org/vbauer/jconditions.svg)](https://travis-ci.org/vbauer/jconditions) [![Coverage Status](https://coveralls.io/repos/vbauer/jconditions/badge.svg?branch=master)](https://coveralls.io/r/vbauer/jconditions?branch=master) [![Maven](https://img.shields.io/github/tag/vbauer/jconditions.svg?label=maven)](https://jitpack.io/#vbauer/jconditions)

> There is an exception to every rule.

**JConditions** is an extension for [JUnit](http://junit.org) framework, which allows to mark test methods with some specific conditional annotations.
It helps to keep clean your test method's code and prevents a lot of unnecessary code (with [Assume](http://junit.sourceforge.net/javadoc/org/junit/Assume.html) class).


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

### @AppIsInstalled
### @ExistsOnFS
### @HasClass
### @HasPackage
### @IfJavaVersion
### @IfScript
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
