package com.github.vbauer.jconditions.checker;

import com.github.vbauer.jconditions.annotation.SocketIsOpened;
import com.github.vbauer.jconditions.core.CheckerContext;
import com.github.vbauer.jconditions.core.ConditionChecker;
import com.github.vbauer.jconditions.util.InOutUtils;
import com.github.vbauer.jconditions.util.PropUtils;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Vladislav Bauer
 */

public class SocketIsOpenedChecker implements ConditionChecker<SocketIsOpened> {

    @Override
    public boolean isSatisfied(final CheckerContext<SocketIsOpened> context) throws Exception {
        final SocketIsOpened annotation = context.getAnnotation();
        final String host = PropUtils.injectProperties(annotation.host());
        final int port = annotation.port();
        final int timeout = annotation.timeout();
        return isSocketOpened(host, port, timeout);
    }


    private boolean isSocketOpened(
        final String host, final int port, final int timeout
    ) throws Exception {
        Socket socket = null;
        try {
            socket = new Socket();
            socket.bind(null);
            socket.connect(new InetSocketAddress(host, port), timeout);
            return true;
        } finally {
            InOutUtils.closeQuietly(socket);
        }
    }

}
