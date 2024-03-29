package python.to.java.models.pythonExecutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import python.to.java.services.IKMeans;

/**
 * Classe responsável por executar o algoritmo KMeans através do Process
 * Builder.
 *
 * @author Allan Capistrano
 * @version 1.0.0
 */
public class ProcessBuilderKMeans implements IKMeans {

  private String filePath;

  private static final Logger logger = Logger.getLogger(
    ProcessBuilderKMeans.class.getName()
  );

  public ProcessBuilderKMeans() {}

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
    ProcessBuilder processBuilder = new ProcessBuilder(
      "python3",
      this.filePath,
      nodesCredibility.toString()
    );
    processBuilder.redirectErrorStream(true);

    try {
      Process process = processBuilder.start();
      String output = this.readInputStream(process.getInputStream());

      /* Sanitizando a lista */
      output = output.substring(1, output.length() - 2);
      System.out.println(output);

      /* Convertendo a saída String em uma lista de Floats*/
      List<String> kMeansResultInString = new ArrayList<String>(
        Arrays.asList(output.split(","))
      );
      List<Float> kMeansResult = kMeansResultInString
        .stream()
        .map(Float::valueOf)
        .collect(Collectors.toList());

      return kMeansResult;
    } catch (IOException ioe) {
      logger.severe("Error trying to execute '" + this.filePath + "'.");
      logger.severe(ioe.getMessage());
    }

    return null;
  }

  /**
   * Lê conteúdo de um InputStream e converte para String
   *
   * @param inputStream InputStream - Conteúdo que deseja ser convertido em
   * String.
   * @return String
   */
  private String readInputStream(InputStream inputStream) {
    StringBuilder content = new StringBuilder();

    try (
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(inputStream)
      )
    ) {
      String line;

      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return content.toString();
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
