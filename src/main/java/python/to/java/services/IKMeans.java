package python.to.java.services;

import java.util.List;

public interface IKMeans {
  /**
   * Executa o algoritmo KMeans em uma lista contendo a credibilidade dos nós,
   * e retorna uma lista contendo somente as credibilidades que compõem o grupo
   * com as maiores credibilidades.
   *
   * @param nodesCredibilityString List<Float> - Lista com as credibilidades dos nós.
   * @return List<Float>
   */
  public List<Float> execute(List<Float> nodesCredibilityString);
}
