package wtf.metio.ti.sink.syslog;

import org.graylog2.syslog4j.Syslog;
import org.graylog2.syslog4j.SyslogIF;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Factory for syslog based sinks. "unix_syslog", "unix_socket"
 */
public final class SyslogSinks {

    /**
     * Constant for the UDP based protocol name used by syslog.
     */
    public static final String UDP_PROTOCOL = "udp";

    /**
     * Constant for the TCP based protocol name used by syslog.
     */
    public static final String TCP_PROTOCOL = "tcp";

    /**
     * Constant for the Unix syslog based protocol name used by syslog.
     */
    public static final String UNIX_SYSLOG_PROTOCOL = "unix_syslog";

    /**
     * Constant for the Unix socket protocol name used by syslog.
     */
    public static final String UNIX_SOCKET_PROTOCOL = "unix_socket";

    private static final BiConsumer<SyslogIF, String> INFO_LOGGER = SyslogIF::info;
    private static final BiConsumer<SyslogIF, String> DEBUG_LOGGER = SyslogIF::debug;
    private static final BiConsumer<SyslogIF, String> NOTICE_LOGGER = SyslogIF::notice;
    private static final BiConsumer<SyslogIF, String> WARN_LOGGER = SyslogIF::warn;
    private static final BiConsumer<SyslogIF, String> ERROR_LOGGER = SyslogIF::error;
    private static final BiConsumer<SyslogIF, String> CRITICAL_LOGGER = SyslogIF::critical;
    private static final BiConsumer<SyslogIF, String> ALERT_LOGGER = SyslogIF::alert;
    private static final BiConsumer<SyslogIF, String> EMERGENCY_LOGGER = SyslogIF::emergency;

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static Consumer<String> udpSyslogInfo() {
        return udpSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static Consumer<String> udpSyslogDebug() {
        return udpSyslog(DEBUG_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static Consumer<String> udpSyslogNotice() {
        return udpSyslog(NOTICE_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static Consumer<String> udpSyslogWarn() {
        return udpSyslog(WARN_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static Consumer<String> udpSyslogError() {
        return udpSyslog(ERROR_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static Consumer<String> udpSyslogCritical() {
        return udpSyslog(CRITICAL_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static Consumer<String> udpSyslogAlert() {
        return udpSyslog(ALERT_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over UDP
     */
    public static Consumer<String> udpSyslogEmergency() {
        return udpSyslog(EMERGENCY_LOGGER);
    }

    /**
     * @param sink The sink to use.
     * @return A consumer that uses syslog
     */
    public static Consumer<String> udpSyslog(final BiConsumer<SyslogIF, String> sink) {
        return syslog(UDP_PROTOCOL, sink);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static Consumer<String> tcpSyslogInfo() {
        return tcpSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static Consumer<String> tcpSyslogDebug() {
        return tcpSyslog(DEBUG_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static Consumer<String> tcpSyslogNotice() {
        return tcpSyslog(NOTICE_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static Consumer<String> tcpSyslogWarn() {
        return tcpSyslog(WARN_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static Consumer<String> tcpSyslogError() {
        return tcpSyslog(ERROR_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static Consumer<String> tcpSyslogCritical() {
        return tcpSyslog(CRITICAL_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static Consumer<String> tcpSyslogAlert() {
        return tcpSyslog(ALERT_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over TCP
     */
    public static Consumer<String> tcpSyslogEmergency() {
        return tcpSyslog(EMERGENCY_LOGGER);
    }

    /**
     * @param sink The sink to use.
     * @return A consumer that uses syslog
     */
    public static Consumer<String> tcpSyslog(final BiConsumer<SyslogIF, String> sink) {
        return syslog(TCP_PROTOCOL, sink);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static Consumer<String> unixSyslogInfo() {
        return unixSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static Consumer<String> unixSyslogDebug() {
        return unixSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static Consumer<String> unixSyslogNotice() {
        return unixSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static Consumer<String> unixSyslogWarn() {
        return unixSyslog(WARN_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static Consumer<String> unixSyslogError() {
        return unixSyslog(ERROR_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static Consumer<String> unixSyslogCritical() {
        return unixSyslog(CRITICAL_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static Consumer<String> unixSyslogAlert() {
        return unixSyslog(ALERT_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix syslog protocol
     */
    public static Consumer<String> unixSyslogEmergency() {
        return unixSyslog(EMERGENCY_LOGGER);
    }

    /**
     * @param sink The sink to use.
     * @return A consumer that uses syslog
     */
    public static Consumer<String> unixSyslog(final BiConsumer<SyslogIF, String> sink) {
        return syslog(UNIX_SYSLOG_PROTOCOL, sink);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static Consumer<String> unixSocketSyslogInfo() {
        return unixSocketSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static Consumer<String> unixSocketSyslogDebug() {
        return unixSocketSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static Consumer<String> unixSocketSyslogNotice() {
        return unixSocketSyslog(INFO_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static Consumer<String> unixSocketSyslogWarn() {
        return unixSocketSyslog(WARN_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static Consumer<String> unixSocketSyslogError() {
        return unixSocketSyslog(ERROR_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static Consumer<String> unixSocketSyslogCritical() {
        return unixSocketSyslog(CRITICAL_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static Consumer<String> unixSocketSyslogAlert() {
        return unixSocketSyslog(ALERT_LOGGER);
    }

    /**
     * @return A consumer that uses syslog over the Unix socket protocol
     */
    public static Consumer<String> unixSocketSyslogEmergency() {
        return unixSocketSyslog(EMERGENCY_LOGGER);
    }

    /**
     * @param sink The sink to use.
     * @return A consumer that uses syslog
     */
    public static Consumer<String> unixSocketSyslog(final BiConsumer<SyslogIF, String> sink) {
        return syslog(UNIX_SOCKET_PROTOCOL, sink);
    }

    /**
     * @param protocol The syslog protocol to use.
     * @param sink     The sink to use.
     * @return A consumer that uses syslog
     */
    public static Consumer<String> syslog(final String protocol, final BiConsumer<SyslogIF, String> sink) {
        return syslog(Syslog.getInstance(protocol), sink);
    }

    /**
     * @param syslog The syslog interface to use.
     * @param sink   The sink to use.
     * @return A consumer that uses syslog
     */
    public static Consumer<String> syslog(final SyslogIF syslog, final BiConsumer<SyslogIF, String> sink) {
        return msg -> sink.accept(syslog, msg);
    }

}
