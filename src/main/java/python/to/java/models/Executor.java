package python.to.java.models;

import java.io.ByteArrayOutputStream;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

/**
 * Classe base para a execução dos scripts em Python.
 *
 * @author Allan Capistrano
 * @version 1.0.0
 */
public abstract class Executor {

  private DefaultExecutor executor;
  private ByteArrayOutputStream outputStream;
  private CommandLine cmdLine;

  /**
   * Inicializa os atributos necessários para a execução dos scripts Python no
   * Java.
   */
  public void init() {
    this.outputStream = new ByteArrayOutputStream();
    PumpStreamHandler streamHandler = new PumpStreamHandler(this.outputStream);

    this.executor = new DefaultExecutor();
    this.executor.setStreamHandler(streamHandler);
  }

  public DefaultExecutor getExecutor() {
    return executor;
  }

  public ByteArrayOutputStream getOutputStream() {
    return outputStream;
  }

  public CommandLine getCmdLine() {
    return cmdLine;
  }

  public void setCmdLine(String command) {
    this.cmdLine = CommandLine.parse(command);
  }
}
