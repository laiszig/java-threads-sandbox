# 👨‍💻 Java threads: aprenda a criar, gerenciar e aplicar com o Spring

## 🖥️ Utilizando Threads em Java

**Como o Sistema Operacional Gerencia as Threads?**
- O SO precisa decidir qual thread vai trabalhar em qual momento e por quanto tempo.
- Isso é especialmente importante porque, na maioria das vezes, os computadores têm um número limitado de núcleos do processador
onde os funcionários podem trabalhar.
- O gerenciamento eficaz garante que todos os trabalhos sejam feitos sem que ninguém fique esperando muito tempo.

**Escalonadores de Threads**
- Para organizar tudo isso, o SO usa algo chamado escalonador de threads. 
- O escalonador é como um cronograma que diz quem trabalha, quando e por quanto tempo. 
- Existem diferentes tipos de escalonadores, mas todos têm o mesmo objetivo: fazer com que o computador seja justo
com as threads e use o processador da maneira mais eficiente possível.
  - Exemplo de Escalonamento:
    - Vamos supor que temos três tarefas (threads): A, B e C. 
    - O escalonador pode decidir que A vai trabalhar por 20 milissegundos, depois B por 20 milissegundos e, por fim, C por 20 milissegundos. 
    - Depois disso, ele volta para A e repete o ciclo.

**Gerenciadores de Concorrência**
- Às vezes, as threads precisam trabalhar na mesma tarefa ou acessar a mesma informação ao mesmo tempo.
- Isso pode causar problemas sérios. Para evitar isso, o SO usa gerenciadores de concorrência, 
que são como regras de quem pode usar o quê e quando.
  - Exemplo de Gerenciamento de Concorrência:
    - Se a thread A está acessando um arquivo e a thread B também precisa acessar esse mesmo arquivo, 
    o gerenciador de concorrência pode fazer com que a thread B espere até que a thread A termine o seu trabalho. 
    - Isso evita que o arquivo seja corrompido ou que aconteçam outros erros.
- O gerenciamento de threads é fundamental para a performance e a estabilidade do computador.
- Sem ele, teríamos muitos problemas, como programas travando, computadores lentos e até perda de informações.

## 📚 Estudos Recomendados

- Estudar sobre os diferentes algoritmos de escalonamento, como Round-Robin, Prioridades e FIFO (First In, First Out).
- Conceitos como semáforos, mutexes e deadlocks, que são ferramentas e problemas comuns no gerenciamento de concorrência.

**Ferramentas de sincronização em Java**
- Para evitar condições de corrida, podemos usar mecanismos de sincronização, como o synchronized. 
  - Esse termo pode ser usado tanto na declaração de métodos, como vimos em vídeo, quanto em blocos de código.
```java
synchronized (objeto a se sincronizar) {
  parte do código a ser sincronizada
  }
```
- Quando você marca um método ou bloco de código com synchronized, você está criando um "monitor". 
- Esse monitor é como um chaveiro que só tem uma chave para cada equipamento. 
- Se uma thread quer acessar o código sincronizado, ela precisa pegar a chave. 
- Se a chave já estiver com outra thread, ela espera até que a chave seja devolvida.

- Dentro do parênteses, fica o objeto que deve ser “monitorado”.
- Esse objeto pode ser a própria classe, quando usamos this, mas podem ser outros objetos também. 
- Já dentro das chaves, adicionamos qual a parte crítica, que somente uma thread pode acessar.
- Isso garante que apenas uma thread de cada vez possa executar esse código. 
- Quando a thread termina, ela "devolve" a chave, permitindo que outra thread entre na seção crítica.

**Usando locks**
- O synchronized usa um conceito chamado "lock intrínseco" ou "monitor interno". 
- Cada objeto em Java tem um lock intrínseco associado a ele. 
- Quando um método é synchronized, ele usa o lock do objeto. 
- Se for um método estático synchronized, ele usa o lock do objeto que representa a classe (Class object).
- Mas além do synchronized, podemos utilizar locks de uma forma mais explícita, com classes para representar Locks no Java. 
- Eles oferecem mais flexibilidade que os blocos synchronized, pois permitem travar e destravar em momentos diferentes e de formas diferentes.
```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Contador {
    private int contagem = 0;
    private final Lock lock = new ReentrantLock();

    public void incrementar() {
        lock.lock();
        try {
            contagem++;
        } finally {
            lock.unlock();
        }
    }
}
```
- Com o lock, garantimos que apenas uma thread possa entrar na seção crítica do código por vez. 
- A seção crítica é onde ocorre a alteração do estado compartilhado, no caso, contagem.
- Porém, ao trabalhar com locks, devemos nos atentar a um problema: o surgimento de deadlocks. 
  - Um deadlock acontece quando duas ou mais threads ficam esperando eternamente uma pela outra para liberar recursos. 
  - É como se duas pessoas estivessem em um corredor estreito e nenhuma das duas pode passar porque estão bloqueando o caminho uma da outra. 
  - Assim, devemos utilizar o recurso dos locks com cautela!
- Condições de corrida, locks e deadlocks são conceitos bem importantes quando tratamos de programação concorrente. 
- No caso de se deparar com algo parecido no seu desenvolvimento, lembre-se de procurar qual a solução mais se adequa à sua realidade.