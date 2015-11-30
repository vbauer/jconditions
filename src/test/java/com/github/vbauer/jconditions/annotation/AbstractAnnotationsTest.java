package com.github.vbauer.jconditions.annotation;

import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.github.vbauer.jconditions.checker.IfJavaVersionChecker;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.misc.Always;
import com.github.vbauer.jconditions.misc.AppleWorksFine;
import com.github.vbauer.jconditions.misc.Never;
import com.github.vbauer.jconditions.util.FSUtils;
import com.github.vbauer.jconditions.util.PropUtils;

/**
 * @author Vladislav Bauer
 */

@IgnoreIf(Never.class)
public abstract class AbstractAnnotationsTest implements InterfaceAnnotationsTest {

    public final boolean isSatisfiedInnerCheck = false;


    @Test
    @RunIf(ExceptionClass.class)
    public void testIgnoreIfException() {
        Assert.fail();
    }

    @Test
    @IgnoreIf(Always.class)
    public void testIgnoreIfAlways() {
        Assert.fail();
    }

    @Test
    @IgnoreIf(Never.class)
    public void testIgnoreIfNever() {
        Assert.assertTrue(true);
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
    @AppIsInstalled({ "fake-app-12345" })
    public void testAppIsNotInstalled() throws Exception {
        Assert.fail();
    }

    @Test
    @ExistsOnFS("pom.xml")
    public void testFileExists() throws Exception {
        Assert.assertTrue(FSUtils.fileExists("pom.xml"));
    }

    @Test
    @ExistsOnFS(value = "src", type = { ExistsOnFS.Type.DIRECTORY, ExistsOnFS.Type.SYMLINK })
    public void testDirectoryExists() throws Exception {
        Assert.assertTrue(FSUtils.directoryExists("src"));
    }

    @Test
    @ExistsOnFS("pom.xml2")
    public void testFileNotExists() throws Exception {
        Assert.fail();
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
    @UrlIsReachable("http://it-is-a-wrong-url-address.com")
    public void testUrlIsNotReachable() throws Exception {
        Assert.fail();
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
    @HasClass("org.wrong.package.WrongClass")
    public void testHasClassNegative() throws Exception {
        Assert.fail();
    }

    @Test
    @HasPackage("org.junit")
    public void testHasPackage() throws Exception {
        Assert.assertNotNull(Package.getPackage("org.junit"));
    }

    @Test
    @HasPackage("org.wrong.package")
    public void testHasPackageNegative() throws Exception {
        Assert.fail();
    }

    @Test
    @PropertyIsDefined(keys = "os.name")
    public void testEnvVarIsDefined() {
        Assert.assertNotNull(PropUtils.getSystemProperty("os.name"));
    }

    @Test
    @PropertyIsDefined(keys = "unknown.env.parameter")
    public void testEnvVarHasWrongKey() {
        Assert.fail();
    }

    @Test
    @PropertyIsDefined(keys = "os.name", values = "Linux2")
    public void testEnvVarHasWrongValue() {
        Assert.fail();
    }

    @Test
    @AppleWorksFine
    public void testCustomAnnotation() throws Exception {
        checkSite("http://apple.com");
    }

    @Test
    @IfScript("true")
    public void testIfScript() {
        Assert.assertTrue(true);
    }

    @Test
    @IfScript("test.isSatisfiedInnerCheck")
    public void testIfScriptNegative() {
        Assert.fail();
    }

    @Test
    @IfScript(value = "0", engine = "application/javascript")
    public void testIfScriptMimeType() {
        Assert.fail();
    }

    @Test
    @IfScript(value = "true", engine = "unknown")
    public void testIfScriptWrongEngine() {
        Assert.fail();
    }

    @Test
    @IfScript(value = "context == true", context = ExtraContext.class)
    public void testIfScriptContext() {
        Assert.fail();
    }

    @Test
    @ResourceIsAvailable(
        source = "http://apple.com",
        target = "${java.io.tmpdir}/apple-homepage.html",
        cache = false
    )
    public void testResourceIsAvailable() {
        checkTempFile("apple-homepage.html");
    }

    @Test
    @ResourceIsAvailable(source = "google.com", target = "${java.io.tmpdir}/google.html")
    public void testResourceIsAvailableAutoSchema() {
        checkTempFile("google.html");
    }

    @Test
    @ResourceIsAvailable(source = "google.com", target = "${java.io.tmpdir}/google.html")
    public void testResourceIsAvailableCache() {
        checkTempFile("google.html");
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
        Assert.assertNotNull(javaslang.Tuple0.instance());
    }

    @Test
    @HasFreeSpace(value = { "/", "C:\\" }, min = 1024)
    public void testHasFreeSpace() {
        Assert.assertTrue(true);
    }

    @Test
    @HasFreeSpace(value = { "/", "C:\\" }, min = Long.MAX_VALUE)
    public void testHasNotMinFreeSpace() {
        Assert.fail();
    }

    @Test
    @HasFreeSpace(value = { "/", "C:\\" }, max = 1024)
    public void testHasNotMaxFreeSpace() {
        Assert.fail();
    }

    @Test
    @Ignore
    public void testStandardIgnore() {
        Assert.fail("Standard @Ignore annotation was broken");
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

    private void checkTempFile(final String filePath) {
        final String path = PropUtils.injectProperties("${java.io.tmpdir}/" + filePath);
        Assert.assertTrue(FSUtils.fileExists(path));
    }


    /**
     * @author Vladislav Bauer
     */
    private static final class ExceptionClass<T> implements ConditionChecker<T> {
        @Override
        public boolean isSatisfied(final CheckerContext<T> context) throws Exception {
            throw new RuntimeException();
        }
    }

    /**
     * @author Vladislav Bauer
     */
    private class InnerClass<T> implements ConditionChecker<T> {
        @Override
        public boolean isSatisfied(final CheckerContext<T> context) throws Exception {
            return isSatisfiedInnerCheck;
        }
    }

    /**
     * @author Vladislav Bauer
     */
    private static class StaticNestedClass<T> implements ConditionChecker<T> {
        @Override
        public boolean isSatisfied(final CheckerContext<T> context) throws Exception {
            return false;
        }
    }

    /**
     * @author Vladislav Bauer
     */
    private static class ExtraContext implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            return false;
        }
    }

}
