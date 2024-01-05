package python.to.java.models.pythonExecutions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import python.to.java.models.Executor;
import python.to.java.services.IKMeans;

/**
 *
 * @author Allan Capistrano
 * @version 1.0.0
 */
public class KMeans extends Executor implements IKMeans {

  private String filePath;

  private static final Logger logger = Logger.getLogger(KMeans.class.getName());

  public KMeans() {}

  /**
   * Executa o que foi definido na função quando o bundle for inicializado.
   */
  public void start() {
    this.init();

    // TODO: Remover linhas abaixo.
    List<Float> nodesCredibility = new ArrayList<>();

    nodesCredibility.add((float) 0.51720076);
    nodesCredibility.add((float) 0.8750231);
    nodesCredibility.add((float) 0.59633187);
    nodesCredibility.add((float) 0.98066132);
    nodesCredibility.add((float) 0.70062445);
    nodesCredibility.add((float) 0.54810414);
    nodesCredibility.add((float) 0.74329332);
    nodesCredibility.add((float) 0.01303828);

    List<Float> temp = this.execute(nodesCredibility.toString());

    logger.info(temp.toString());
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
   * @param nodesCredibilityString String - Lista com as credibilidades dos nós.
   * @return List<Float>
   */
  @Override
  public List<Float> execute(String nodesCredibilityString) {
    String command = String.format(
      "python3 %s '%s'",
      this.filePath,
      nodesCredibilityString
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
