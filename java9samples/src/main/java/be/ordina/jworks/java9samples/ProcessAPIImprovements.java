package be.ordina.jworks.java9samples;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Process API improvements.
 *
 * @author Yannick De Turck
 */
public class ProcessAPIImprovements {
    public static void main(String[] args) throws IOException {
        // ls/grep process example
        ProcessBuilder ls = new ProcessBuilder()
                .command("ls")
                .directory(Paths.get("/Users/yannickdeturck/Desktop/foo/").toFile());
        ProcessBuilder grepXml = new ProcessBuilder()
                .command("grep", "xml")
                .redirectOutput(ProcessBuilder.Redirect.INHERIT);
        List<Process> processes = ProcessBuilder
                .startPipeline(Arrays.asList(ls, grepXml));
        processes.forEach(System.out::println);
        // Result of the executed command:
        //   bar.xml
        //   secret-copy.xml
        //   secret.xml

        // Example waiting until processes are done
        CompletableFuture[] lsThenGrepFutures = processes.stream()
                .map(Process::onExit)
                .map(processFuture -> processFuture.thenAccept(
                        process -> System.out.println("PID: " + process.getPid())))
                .toArray(CompletableFuture[]::new);
        CompletableFuture
                .allOf(lsThenGrepFutures)
                .join();

        // Example ProcessInfo
        ProcessHandle processHandle = ProcessHandle.current();
        ProcessHandle.Info processInfo = processHandle.info();
        processHandle.getPid(); // long
        // 12138
        processInfo.arguments(); // Optional<String[]>
        // -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:62727,suspend=y,server=n
        // -Dfile.encoding=UTF-8
        // -classpath
        // /Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar
        processInfo.command(); // Optional<String[]>
        // /Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home/bin/java
        processInfo.startInstant(); // Optional<Instant>
        // 2017-04-11T19:01:10.099Z
        processInfo.totalCpuDuration(); // Optional<Duration>
        // PT0.465663S
        processInfo.user(); // Optional<String[]>
        // yannick

        // Example killing a process
        ProcessHandle.allProcesses()
                .filter(process -> process.info().command().filter(command ->
                        command.toLowerCase().contains("twitter")).isPresent())
                .forEach(process -> process.info().command()
                        .ifPresent(command ->
                                System.out.println((process.destroyForcibly() ? "Successfully killed" : "Failed to kill")
                                        + "process " + command))
                );
        // Successfully killed process /Applications/Twitter.app/Contents/MacOS/Twitter
    }
}
