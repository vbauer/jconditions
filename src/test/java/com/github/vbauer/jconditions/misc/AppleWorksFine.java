package com.github.vbauer.jconditions.misc;

import com.github.vbauer.jconditions.annotation.SocketIsOpened;
import com.github.vbauer.jconditions.annotation.UrlIsReachable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vladislav Bauer
 */

@SocketIsOpened(host = "apple.com", port = 80)
@UrlIsReachable({ "http://apple.com", "https://itunes.apple.com" })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
public @interface AppleWorksFine {
}
