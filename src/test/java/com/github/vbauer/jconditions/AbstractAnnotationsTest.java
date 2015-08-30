package com.github.vbauer.jconditions;

import com.github.vbauer.jconditions.annotation.*;
import com.github.vbauer.jconditions.checker.IfJavaVersionChecker;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.misc.Always;
import com.github.vbauer.jconditions.misc.AppleWorksFine;
import com.github.vbauer.jconditions.util.FSUtils;
import com.github.vbauer.jconditions.util.PropUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;
import java.net.URLConnection;

/**
 * @author Vladislav Bauer
 */

@Ignore
public abstract class AbstractAnnotationsTest {

    public final boolean isSatisfiedInnerCheck = false;


    @Test
    @IgnoreIf(Always.class)
    public void testIgnoreIf() {
        Assert.fail();
    }

    @Test
    @RunIf(InnerClass.class)
    public void testInnerClass() {
        Assert.fail();
    }

    @Test
    @RunIf(StaticNestedClass.class)
    public void testStaticNestedClass() {
        Assert.fail();
    }

    @Test
    @AppIsInstalled({ "ls", "uname" })
    public void testAppIsInstalled() throws Exception {
        exec("ls");
        exec("uname");
    }

    @Test
    @ExistsOnFS("pom.xml")
    public void testFileExists() throws Exception {
        Assert.assertTrue(FSUtils.fileExists("pom.xml"));
    }

    @Test
    @ExistsOnFS(value = "src", type = ExistsOnFS.Type.DIRECTORY)
    public void testDirectoryExists() throws Exception {
        Assert.assertTrue(FSUtils.directoryExists("src"));
    }

    @Test
    @SocketIsOpened(host = "apple.com", port = 80)
    public void testSocketIsOpened() throws Exception {
        checkSite("http://apple.com");
    }

    @Test
    @UrlIsReachable("http://apple.com")
    public void testUrlIsReachable() throws Exception {
        checkSite("http://apple.com");
    }

    @Test
    @RunningOnOS({
        RunningOnOS.AIX,
        RunningOnOS.HP_UX,
        RunningOnOS.IRIX,
        RunningOnOS.LINUX,
        RunningOnOS.MAC,
        RunningOnOS.MAC_OSX,
        RunningOnOS.SOLARIS,
        RunningOnOS.SUN_OS
    })
    public void testRunningOnOS() throws Exception {
        exec("ls");
    }

    @Test
    @HasClass("org.junit.Assert")
    public void testHasClass() throws Exception {
        Assert.assertNotNull(Class.forName("org.junit.Assert"));
    }

    @Test
    @HasPackage("com.github.vbauer")
    public void testHasPackage() throws Exception {
        Assert.assertNotNull(Package.getPackage("com.github.vbauer"));
    }

    @Test
    @PropertyIsDefined(keys = "os.name")
    public void testEnvVarIsDefined() {
        Assert.assertNotNull(PropUtils.getSystemProperty("os.name"));
    }

    @Test
    @AppleWorksFine
    public void testCustomAnnotation() throws Exception {
        checkSite("http://apple.com");
    }

    @Test
    @IfScript("test.isSatisfiedInnerCheck")
    public void testIfScript() {
        Assert.fail();
    }

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

    @Test
    @IfJavaVersion(IfJavaVersion.JAVA_7)
    public void testIfJavaVersion7() {
        Assert.assertTrue(IfJavaVersionChecker.javaVersion().contains("7"));
    }

    @Test
    @IfJavaVersion(IfJavaVersion.JAVA_8)
    public void testIfJavaVersion8() {
        Assert.assertTrue(IfJavaVersionChecker.javaVersion().contains("8"));
        Assert.assertNotNull(play.Logger.of(AbstractAnnotationsTest.class));
    }

    private void checkSite(final String urlAddress) throws Exception {
        final URL url = new URL(urlAddress);
        final URLConnection connection = url.openConnection();
        connection.connect();
        Assert.assertNotNull(connection);
    }

    private void exec(final String ls) throws Exception {
        final Runtime runtime = Runtime.getRuntime();
        final Process process = runtime.exec(ls);
        Assert.assertEquals(0, process.waitFor());
    }


    /**
     * @author Vladislav Bauer
     */
    private class InnerClass implements ConditionChecker {
        @Override
        public boolean isSatisfied(final CheckerContext context) throws Exception {
            return isSatisfiedInnerCheck;
        }
    }

    /**
     * @author Vladislav Bauer
     */
    private static class StaticNestedClass implements ConditionChecker {
        @Override
        public boolean isSatisfied(final CheckerContext context) throws Exception {
            return false;
        }
    }

}
