# SOFT-IoT-Python-to-Java

O `soft-iot-python-to-java` é o *bundle* responsável responsável por permitir a execução de scripts em Python na plataforma SOFT-IoT.

É possível executar código Python usando:
- [Apache Commons Exec](https://commons.apache.org/proper/commons-exec/index.html);
- [Process Builder](https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html).

Vale destacar que o `Apache Commons Exec` não lida muito bem com [Pythons lists](https://docs.python.org/3/tutorial/datastructures.html), enquanto o `Process Builder` não lida muito bem com [Numpy Array](https://numpy.org/doc/stable/reference/generated/numpy.array.html).

Este é um *bundle* de propósito geral, porém é necessário implementar cada uma das formas de execução para o seu caso de uso. Para isso, basta seguir os exemplos para execução do Algorimo KMeans, através do [Apache Commons Exec](https://github.com/AllanCapistrano/soft-iot-python-to-java/blob/main/src/main/java/python/to/java/models/pythonExecutions/ApacheCommonKMeans.java) ou do [Process Builder](https://github.com/AllanCapistrano/soft-iot-python-to-java/blob/main/src/main/java/python/to/java/models/pythonExecutions/ProcessBuilderKMeans.java).

## Configurações

As configurações são específicas para cada execução, dessa forma, quando implementá-las, basta criar uma nova variável de configuração para indicar onde que o *script* está localizado para ser executado.

| Propriedade | Descrição | Valor Padrão |
| ----------- | --------- | ------------ |
| kmeansScriptPath | Caminho onde o script Python do KMeans está localizado. | `/opt/karaf/etc/python_scripts/kmeans.py` |

## Licença
[GPL-3.0 License](./LICENSE)
