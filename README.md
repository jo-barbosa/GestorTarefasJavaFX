# Gestor de Tarefas JavaFX 🗂️

Uma aplicação desktop simples desenvolvida em **Java** com **JavaFX** para gestão de tarefas pessoais.

---

## ✨ Funcionalidades

- ✅ Adicionar tarefas
- 🗑️ Remover tarefas
- ✅ Marcar tarefas como concluídas
- 📅 Ver lista de tarefas ativas e concluídas
- 💾 Dados armazenados em memória (por agora)

---

## 🛠️ Tecnologias usadas

- Java 21 ☕
- JavaFX (UI gráfica)
- IntelliJ IDEA
- Git + GitHub

---

## 🚀 Como correr o projeto

### 1. Requisitos:
- Java JDK 21
- JavaFX SDK 21 (adicionado como biblioteca externa)
- IntelliJ IDEA

### 2. Passos:

1. Clona o repositório:
   ```bash
   git clone https://github.com/jo-barbosa/GestorTarefasJavaFX.git

2. Abre no IntelliJ → Configura a library do JavaFX:
    - Vai a `File → Project Structure → Libraries → +`
    - Adiciona os `.jar` da pasta `lib/` do JavaFX SDK

3. Configura o Run/VM Options:
   --module-path /caminho/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml


4. Corre a aplicação 🚀

---

## 📁 Estrutura do projeto

```
src/
└── com.jobarbosa.gestortarefas/
├── MainApp.java
├── Tarefa.java
└── GestorTarefas.java
```
---

## 🔧 Próximas melhorias

- [ ] Armazenamento em ficheiro ou base de dados
- [ ] Interface com categorias
- [ ] Filtro de tarefas por data
- [ ] Exportação para CSV

---

## 👨‍💻 Autor

Desenvolvido por Jorge Barbosa – estudante de Engenharia Informática no ISEP 💻  
<!-- LinkedIn: [https://www.linkedin.com/in/teu-link/](https://www.linkedin.com/in/teu-link/) -->

