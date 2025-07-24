# Aplicação de Gerenciamento de Eventos (Java + SQLite)

## 📌 Descrição do Recurso

 O recurso gerenciado é **Evento**, contendo os seguintes campos:

 | Campo          | Tipo       | Obrigatório? |
 |----------------|------------|--------------|
 | id             | Inteiro    | Sim (gerado automaticamente) |
 | nome           | Texto      | Sim |
 | descricao      | Texto      | Não |
 | participantes  | Inteiro    | Sim |
 | data           | Data (ISO) | Sim |

---

## ⚙️ Como Configurar

1. **Requisitos:**
   - Java 17+ ou 20
   - Maven instalado
   - IntelliJ ou terminal

2. **Clonar o projeto**
   ```bash
   git clone <ProcessoSeletivo>
   cd projeto-eventos
3. **Compilar**
   
    ```bash
    mvn clean compile

5. **Rodar a aplicação**
    ```bash
    mvn exec:java -Dexec.mainClass="com.exemplo.eventos.Main"
    
## ▶️ Funcionalidades
 Listar todos os eventos

 Buscar evento por ID

 Criar novo evento

 Atualizar evento existente

 Deletar evento

 Validação de campos (obrigatórios, datas e números)

 ## 💽 Banco de Dados
 SQLite (arquivo: eventos.db)

 Script de criação: create_tabela.sql

