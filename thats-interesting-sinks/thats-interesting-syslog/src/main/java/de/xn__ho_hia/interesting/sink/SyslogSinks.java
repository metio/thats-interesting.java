package de.xn__ho_hia.interesting.sink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.graylog2.syslog4j.Syslog;
import org.graylog2.syslog4j.SyslogIF;

/**
 * Factory for syslog based sinks. "unix_syslog", "unix_socket"
 */
public class SyslogSinks {

    /** Constant for the UDP based protocol name used by syslog. */
    public static final String                        UDP_PROTOCOL         = "udp";                                     //$NON-NLS-1$

    /** Constant for the TCP based protocol name used by syslog. */
    public static final String                        TCP_PROTOCOL         = "tcp";                                     //$NON-NLS-1$

    /** Constant for the Unix syslog based protocol name used by syslog. */
    public static final String                        UNIX_SYSLOG_PROTOCOL = "unix_syslog";                             //$NON-NLS-1$

    /** Constant for the Unix socket protocol name used by syslog. */
    public static final String                        UNIX_SOCKET_PROTOCL  = "unix_socket";                             //$NON-NLS-1$

    private static final BiConsumer<SyslogIF, String> INFO_LOGGER          = (syslog, message) -> syslog.info(message);
    private static final BiConsumer<SyslogIF, String> DEBUG_LOGGER         = (syslog, message) -> syslog.debug(message);
    private static final BiConsumer<SyslogIF, String> NOTICE_LOGGER        = (syslog, message) -> syslog
            .notice(message);
    private static final BiConsumer<SyslogIF, String> WARN_LOGGER          = (syslog, message) -> syslog.warn(message);
    private static final BiConsumer<SyslogIF, String> ERROR_LOGGER         = (syslog, message) -> syslog.error(message);
    private static final BiConsumer<SyslogIF, String> CRITICAL_LOGGER      = (syslog, message) -> syslog
            .critical(message);
    private static final BiConsumer<SyslogIF, String> ALERT_LOGGER         = (syslog, message) -> syslog.alert(message);
    private static final BiConsumer<SyslogIF, String> EMERGENCY_LOGGER     = (syslog, message) -> syslog
            .emergency(message);

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static final Consumer<String> udpSyslogInfo() {
        return udpSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static final Consumer<String> udpSyslogDebug() {
        return udpSyslog(DEBUG_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static final Consumer<String> udpSyslogNotice() {
        return udpSyslog(NOTICE_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static final Consumer<String> udpSyslogWarn() {
        return udpSyslog(WARN_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static final Consumer<String> udpSyslogError() {
        return udpSyslog(ERROR_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static final Consumer<String> udpSyslogCritical() {
        return udpSyslog(CRITICAL_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static final Consumer<String> udpSyslogAlert() {
        return udpSyslog(ALERT_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static final Consumer<String> udpSyslogEmergency() {
        return udpSyslog(EMERGENCY_LOGGER);
    }

    /**
     * @param sink
     *            The sink to use.
     * @return A consumer that uses syslog
     */
    public static final Consumer<String> udpSyslog(final BiConsumer<SyslogIF, String> sink) {
        return syslog(UDP_PROTOCOL, sink);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static final Consumer<String> tcpSyslogInfo() {
        return tcpSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static final Consumer<String> tcpSyslogDebug() {
        return tcpSyslog(DEBUG_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static final Consumer<String> tcpSyslogNotice() {
        return tcpSyslog(NOTICE_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static final Consumer<String> tcpSyslogWarn() {
        return tcpSyslog(WARN_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static final Consumer<String> tcpSyslogError() {
        return tcpSyslog(ERROR_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static final Consumer<String> tcpSyslogCritical() {
        return tcpSyslog(CRITICAL_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static final Consumer<String> tcpSyslogAlert() {
        return tcpSyslog(ALERT_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static final Consumer<String> tcpSyslogEmergency() {
        return tcpSyslog(EMERGENCY_LOGGER);
    }

    /**
     * @param sink
     *            The sink to use.
     * @return A consumer that uses syslog
     */
    public static final Consumer<String> tcpSyslog(final BiConsumer<SyslogIF, String> sink) {
        return syslog(TCP_PROTOCOL, sink);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static final Consumer<String> unixSyslogInfo() {
        return unixSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static final Consumer<String> unixSyslogDebug() {
        return unixSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static final Consumer<String> unixSyslogNotice() {
        return unixSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static final Consumer<String> unixSyslogWarn() {
        return unixSyslog(WARN_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static final Consumer<String> unixSyslogError() {
        return unixSyslog(ERROR_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static final Consumer<String> unixSyslogCritical() {
        return unixSyslog(CRITICAL_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static final Consumer<String> unixSyslogAlert() {
        return unixSyslog(ALERT_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static final Consumer<String> unixSyslogEmergency() {
        return unixSyslog(EMERGENCY_LOGGER);
    }

    /**
     * @param sink
     *            The sink to use.
     * @return A consumer that uses syslog
     */
    public static final Consumer<String> unixSyslog(final BiConsumer<SyslogIF, String> sink) {
        return syslog(UNIX_SYSLOG_PROTOCOL, sink);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static final Consumer<String> unixSocketSyslogInfo() {
        return unixSocketSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static final Consumer<String> unixSocketSyslogDebug() {
        return unixSocketSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static final Consumer<String> unixSocketSyslogNotice() {
        return unixSocketSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static final Consumer<String> unixSocketSyslogWarn() {
        return unixSocketSyslog(WARN_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static final Consumer<String> unixSocketSyslogError() {
        return unixSocketSyslog(ERROR_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static final Consumer<String> unixSocketSyslogCritical() {
        return unixSocketSyslog(CRITICAL_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static final Consumer<String> unixSocketSyslogAlert() {
        return unixSocketSyslog(ALERT_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static final Consumer<String> unixSocketSyslogEmergency() {
        return unixSocketSyslog(EMERGENCY_LOGGER);
    }

    /**
     * @param sink
     *            The sink to use.
     * @return A consumer that uses syslog
     */
    public static final Consumer<String> unixSocketSyslog(final BiConsumer<SyslogIF, String> sink) {
        return syslog(UNIX_SOCKET_PROTOCL, sink);
    }

    /**
     * @param protocol
     *            The syslog protocol to use.
     * @param sink
     *            The sink to use.
     * @return A consumer that uses syslog
     */
    @SuppressWarnings("null")
    public static final Consumer<String> syslog(final String protocol, final BiConsumer<SyslogIF, String> sink) {
        return msg -> sink.accept(Syslog.getInstance(protocol), msg);
    }

    /**
     * @param syslog
     *            The syslog interface to use.
     * @param sink
     *            The sink to use.
     * @return A consumer that uses syslog
     */
    public static final Consumer<String> syslog(final SyslogIF syslog, final BiConsumer<SyslogIF, String> sink) {
        return msg -> sink.accept(syslog, msg);
    }

}
