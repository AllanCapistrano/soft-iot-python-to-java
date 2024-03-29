package python.to.java.models.pythonExecutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import python.to.java.models.ApacheCommonExecutor;
import python.to.java.services.IKMeans;

/**
 * Classe responsável por executar o algoritmo KMeans através do Apache Common
 * Exec.
 *
 * @author Allan Capistrano
 * @version 1.0.0
 */
public class ApacheCommonKMeans
  extends ApacheCommonExecutor
  implements IKMeans {

  private String filePath;

  private static final Logger logger = Logger.getLogger(
    ApacheCommonKMeans.class.getName()
  );

  public ApacheCommonKMeans() {}

  /**
   * Executa o que foi definido na função quando o bundle for inicializado.
   */
  public void start() {
    this.init();
  }

  /**
   * Executa o que foi definido na função quando o bundle for finalizado.
   */
  public void stop() {}

  /**
   * Executa o algoritmo KMeans em uma lista contendo a credibilidade dos nós,
   * e retorna uma lista contendo somente as credibilidades que compõem o grupo
   * com as maiores credibilidades.
   *
   * @param nodesCredibility List<Float> - Lista com as credibilidades dos nós.
   * @return List<Float>
   */
  @Override
  public List<Float> execute(List<Float> nodesCredibility) {
    String command = String.format(
      "python3 %s '%s'",
      this.filePath,
      nodesCredibility.toString()
    );

    this.setCmdLine(command);

    try {
      int exitCode = this.getExecutor().execute(this.getCmdLine());

      if (exitCode == 0) {
        String outputInString = this.getOutputStream().toString();
        /* Sanitizando a lista */
        outputInString =
          outputInString.substring(1, outputInString.length() - 2);

        /* Convertendo a saída String em uma lista de Floats. */
        List<String> kMeansResultInString = new ArrayList<String>(
          Arrays.asList(outputInString.split(","))
        );

        List<Float> kMeansResult = kMeansResultInString
          .stream()
          .map(Float::valueOf)
          .collect(Collectors.toList());

        return kMeansResult;
      }
    } catch (IOException ioe) {
      logger.severe("Error trying to execute '" + this.filePath + "'.");
      logger.severe(ioe.getMessage());
    }

    return null;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
